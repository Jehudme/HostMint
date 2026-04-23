package com.hostmint.app.web.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.hostmint.app.domain.RequestLog;
import com.hostmint.app.domain.enumeration.HttpMethod;
import com.hostmint.app.repository.RequestLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
class RequestLoggingFilterTest {

    @Mock
    private RequestLogRepository requestLogRepository;

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldPersistStructuredRequestLogForApiErrors() throws ServletException, IOException {
        RequestLoggingFilter filter = new RequestLoggingFilter(requestLogRepository);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("alice", "n/a"));

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/projects");
        request.setRemoteAddr("10.10.10.10");
        request.addHeader("User-Agent", "JUnit");
        MockHttpServletResponse response = new MockHttpServletResponse();

        FilterChain filterChain = (servletRequest, servletResponse) -> {
            servletRequest.setAttribute("audit_error_message", "Validation failed");
            ((MockHttpServletResponse) servletResponse).setStatus(400);
        };

        filter.doFilter(request, response, filterChain);

        ArgumentCaptor<RequestLog> captor = ArgumentCaptor.forClass(RequestLog.class);
        verify(requestLogRepository).save(captor.capture());

        RequestLog saved = captor.getValue();
        assertThat(saved.getMethod()).isEqualTo(HttpMethod.GET);
        assertThat(saved.getPath()).isEqualTo("/api/projects");
        assertThat(saved.getStatusCode()).isEqualTo(400);
        assertThat(saved.getErrorCode()).isEqualTo("HTTP_400");
        assertThat(saved.getErrorMessage()).isEqualTo("Validation failed");
        assertThat(saved.getPrincipal()).isEqualTo("alice");
        assertThat(saved.getIpAddress()).isEqualTo("10.10.10.10");
        assertThat(saved.getCorrelationId()).isNotBlank();
        assertThat(saved.getDurationMs()).isGreaterThanOrEqualTo(0L);
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldNotPersistForNonApiPaths() throws ServletException, IOException {
        RequestLoggingFilter filter = new RequestLoggingFilter(requestLogRepository);

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/management/health");
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, (servletRequest, servletResponse) -> ((MockHttpServletResponse) servletResponse).setStatus(200));

        verifyNoInteractions(requestLogRepository);
    }
}
