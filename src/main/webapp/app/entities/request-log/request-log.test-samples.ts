import dayjs from 'dayjs/esm';

import { IRequestLog } from './request-log.model';

export const sampleWithRequiredData: IRequestLog = {
  id: '0be54aa0-dd3b-4d95-a44b-ee6f8dbc2f56',
  correlationId: 'loufoque moquer',
  method: 'PATCH',
  path: 'membre de l’équipe',
  statusCode: 469,
  durationMs: 28654,
};

export const sampleWithPartialData: IRequestLog = {
  id: 'b96fd4a7-14a6-4c05-b7c8-e56a92c23d7f',
  correlationId: 'jeune',
  method: 'HEAD',
  path: "à l'entour de détendre",
  statusCode: 598,
  durationMs: 15589,
  ipAddress: 'en guise de contre',
  createdAt: dayjs('2026-04-22T16:42'),
};

export const sampleWithFullData: IRequestLog = {
  id: 'df77e98b-a0bd-409e-8c3f-d0a6ea1a16d4',
  correlationId: 'là-haut devant',
  method: 'HEAD',
  path: 'de peur que commissionnaire',
  statusCode: 526,
  durationMs: 8523,
  principal: 'dès que alors que',
  ipAddress: 'aïe',
  errorCode: 'sursauter commis cuicui',
  errorMessage: 'oups intrépide',
  createdAt: dayjs('2026-04-22T13:47'),
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
