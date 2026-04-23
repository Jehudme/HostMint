import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';

import RequestLogResolve from './route/request-log-routing-resolve.service';

const requestLogRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/request-log').then(m => m.RequestLog),
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/request-log-detail').then(m => m.RequestLogDetail),
    resolve: {
      requestLog: RequestLogResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default requestLogRoute;
