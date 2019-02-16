import { TestBed } from '@angular/core/testing';

import { PartTableService } from './part-table.service';

describe('PartTableService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PartTableService = TestBed.get(PartTableService);
    expect(service).toBeTruthy();
  });
});
