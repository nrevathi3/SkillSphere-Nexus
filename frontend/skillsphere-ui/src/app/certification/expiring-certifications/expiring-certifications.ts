import {
  Component,
  OnInit,
  ChangeDetectorRef
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { CertificationService } from '../certification.service';

@Component({
  selector: 'app-expiring-certifications',
  imports: [CommonModule],
  templateUrl: './expiring-certifications.html',
  styleUrl: './expiring-certifications.scss'
})
export class ExpiringCertifications implements OnInit {

  certifications: any[] = [];

  loading = true;

  error = '';

  constructor(
    private certificationService: CertificationService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    console.log('Loading expiring certifications...');

    this.certificationService.getExpiring().subscribe({

      next: (data: any) => {

        console.log(
          'EXPIRING API RESPONSE:',
          data
        );

        this.certifications =
          Array.isArray(data) ? data : [];

        this.loading = false;

        console.log(
          'EXPIRING COUNT:',
          this.certifications.length
        );

        console.log(
          'LOADING AFTER RESPONSE:',
          this.loading
        );

        // Force Angular to update the screen
        this.cdr.detectChanges();
      },

      error: (error) => {

        console.error(
          'Error loading expiring certifications:',
          error
        );

        this.error =
          'Unable to load expiring certifications.';

        this.loading = false;

        this.cdr.detectChanges();
      }

    });
  }
}
