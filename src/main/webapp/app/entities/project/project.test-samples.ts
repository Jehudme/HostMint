import dayjs from 'dayjs/esm';

import { IProject, NewProject } from './project.model';

export const sampleWithRequiredData: IProject = {
  id: 'b4e8eb40-0b03-4aed-a566-952c1dc66c2d',
  name: 'plouf antagoniste',
  projectKey: '2WF6U',
};

export const sampleWithPartialData: IProject = {
  id: '8ca63f3e-f286-413e-a521-ad09f4f94d38',
  name: 'craquer infime',
  projectKey: 'S4SP',
  updatedAt: dayjs('2026-04-22T14:41'),
};

export const sampleWithFullData: IProject = {
  id: '0b1e2b78-e2b4-45ab-89c4-80867430b41c',
  name: 'avex',
  projectKey: '5AQY',
  createdAt: dayjs('2026-04-22T22:55'),
  updatedAt: dayjs('2026-04-22T06:59'),
};

export const sampleWithNewData: NewProject = {
  name: 'toc quant à',
  projectKey: 'VOS1',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
