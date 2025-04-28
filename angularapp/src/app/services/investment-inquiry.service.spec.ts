import { TestBed } from '@angular/core/testing';

import { InvestmentInquiryService } from './investment-inquiry.service';

describe('InvestmentInquiryService', () => {
  let service: InvestmentInquiryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InvestmentInquiryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
