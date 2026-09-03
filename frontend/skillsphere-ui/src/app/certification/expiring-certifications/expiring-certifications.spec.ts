import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpiringCertifications } from './expiring-certifications';

describe('ExpiringCertifications', () => {
  let component: ExpiringCertifications;
  let fixture: ComponentFixture<ExpiringCertifications>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpiringCertifications],
    }).compileComponents();

    fixture = TestBed.createComponent(ExpiringCertifications);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
