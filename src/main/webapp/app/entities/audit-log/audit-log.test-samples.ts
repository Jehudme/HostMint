import dayjs from 'dayjs/esm';

import { IAuditLog } from './audit-log.model';

export const sampleWithRequiredData: IAuditLog = {
  id: 'b6cf7b9c-c00f-45a2-8438-7ace097d2777',
  action: 'dès',
  entityName: 'au-dedans de au moyen de',
  level: 'TRACE',
  message: 'crac piquer',
  correlationId: 'du moment que par suite de',
};

export const sampleWithPartialData: IAuditLog = {
  id: '3183a853-fc22-4562-bd3b-ccd63759e36d',
  action: 'porte-parole dans',
  entityName: 'ouille',
  entityId: '19524b56-b9b4-43b7-8cfa-003613ee7629',
  level: 'TRACE',
  message: 'ouch',
  principal: 'sans que',
  correlationId: 'pis disputer',
  ipAddress: 'de sans que',
  userAgent: 'grimper suivant exclure',
  createdAt: dayjs('2026-04-22T11:35'),
};

export const sampleWithFullData: IAuditLog = {
  id: '8e87c99c-58cd-4f22-b167-47ed80d48cda',
  action: 'tic-tac',
  entityName: 'ding depuis',
  entityId: '531cda9d-4bf1-49c5-8f3e-21bebf825a85',
  level: 'TRACE',
  message: 'population du Québec',
  principal: 'hôte',
  correlationId: 'à condition que autant',
  ipAddress: 'au point que un peu planter',
  userAgent: 'sauter au prix de',
  metadata: '../fake-data/blob/hipster.txt',
  createdAt: dayjs('2026-04-22T21:33'),
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
