import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { CertificationService } from './certification.service';

interface CertificationData {
  certificationId: string;
  certificationName: string;
  organization: string;
  issueDate: string;
  expiryDate: string;
  status: string;
  employeeId: string;
  credentialId: string | null;
}

interface RenewalData {
  renewalId: string;
  certificationId: string;
  oldExpiry: string;
  newExpiry: string | null;
  status: string;
  requestedBy: string;
  approvedBy: string | null;
}

interface AuditData {
  auditId: string;
  certificationId: string;
  action: string;
  performedBy: string;
  performedAt: string;
  details: string | null;
}
interface ComplianceData {
  employeeId: string;
  employeeName: string | null;
  totalCertifications: number;
  validCertifications: number;
  expiredCertifications: number;
  expiringSoon: number;
  compliant: boolean;
}
interface ReportData {
  totalCertifications: number;
  validCertifications: number;
  expiredCertifications: number;
  expiringSoon: number;
  renewalRate: number;
}

@Component({
  selector: 'app-certification',
  imports: [CommonModule],
  templateUrl: './certification.html',
  styleUrl: './certification.scss',
})
export class Certification implements OnInit {

  certifications: CertificationData[] = [];

  renewal: RenewalData | null = null;

  audits: AuditData[] = [];

  compliance: ComplianceData | null = null;

  report: ReportData | null = null;

  private employeeId =
    '4fea4c16-b5f5-45c2-88c7-2324e45b82ee';

  private renewalId =
    '60eebc78-1758-47c2-b635-c5c0825a7a39';

  private certificationId =
    '55555555-5555-5555-5555-555555555555';

  constructor(
    private certificationService: CertificationService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCertifications();
    this.loadRenewal();
    this.loadAudit();
    this.loadCompliance();
    this.loadReport();
  }
  loadCertifications(): void {

    this.certificationService
      .getEmployeeCertifications(this.employeeId)
      .subscribe({

        next: (data: any) => {

          console.log('API DATA:', data);

          this.certifications = data;

          console.log(
            'CERTIFICATIONS AFTER ASSIGNMENT:',
            this.certifications,
            'COUNT:',
            this.certifications.length
          );

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.error(
            'Error loading certifications:',
            error
          );

        }

      });
  }

  requestRenewal(
    certificationId: string
  ): void {

    this.certificationService
      .requestRenewal(
        certificationId,
        'HR'
      )
      .subscribe({

        next: (response) => {

          console.log(
            'RENEWAL REQUESTED:',
            response
          );

          alert(
            'Renewal request submitted successfully!'
          );

          this.loadCertifications();
        },

        error: (error) => {

          console.error(
            'Error requesting renewal:',
            error
          );

          alert(
            'Failed to submit renewal request.'
          );

        }

      });
  }

  loadRenewal(): void {

    this.certificationService
      .getRenewal(this.renewalId)
      .subscribe({

        next: (data: any) => {

          console.log(
            'RENEWAL:',
            data
          );

          this.renewal = data;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.error(
            'Error loading renewal:',
            error
          );

        }

      });
  }

  loadAudit(): void {

    this.certificationService
      .getAudit(this.certificationId)
      .subscribe({

        next: (data: any) => {

          console.log(
            'AUDIT:',
            data
          );

          this.audits = data;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.error(
            'Error loading audit:',
            error
          );

        }

      });
  }
  loadCompliance(): void {

    this.certificationService
      .getCompliance(this.employeeId)
      .subscribe({

        next: (data: any) => {

          console.log(
            'COMPLIANCE:',
            data
          );

          this.compliance = data;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.error(
            'Error loading compliance:',
            error
          );

        }

      });
  }
  loadReport(): void {

    this.certificationService
      .getReport()
      .subscribe({

        next: (data: any) => {

          console.log(
            'REPORT:',
            data
          );

          this.report = data;

          this.cdr.detectChanges();
        },

        error: (error) => {

          console.error(
            'Error loading report:',
            error
          );

        }

      });
  }
}
