import { TestBed } from '@angular/core/testing';

import { AlienTimeService } from './alien-time.service';

describe('AlienTimeService', () => {
  let service: AlienTimeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlienTimeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
