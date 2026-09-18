import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CareerService } from '../services/career';

@Component({
  selector: 'app-jobs',
  standalone: true,
    imports: [CommonModule, FormsModule],
  templateUrl: './jobs.html',
  styleUrl: './jobs.scss'
})
export class Jobs implements OnInit {

  jobs: any[] = [];

  selectedJob: any = null;
  showApplicationForm = false;

  application = {
    employeeName: '',
    employeeId: '',
    currentRole: '',
    yearsOfExperience: null as number | null,
    relevantSkills: '',
    interestMessage: '',
    resumeFileName: ''
  };

  constructor(
    private careerService: CareerService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadJobs();
  }

  loadJobs(): void {
    this.careerService.getJobs().subscribe({
      next: (data) => {
        console.log('JOBS RECEIVED:', data);

        this.jobs = Array.isArray(data) ? data : [];

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Failed to load jobs', err);
      }
    });
  }

  openApplicationForm(job: any): void {
    this.selectedJob = job;
    this.showApplicationForm = true;
    this.cdr.detectChanges();
  }

  closeApplicationForm(): void {
    this.showApplicationForm = false;
    this.selectedJob = null;
  }

  onResumeSelected(event: any): void {
    const file = event.target.files?.[0];

    if (file) {
      this.application.resumeFileName = file.name;
    }
  }

  submitApplication(): void {

    if (!this.selectedJob) {
      return;
    }

    // Validate required fields
    if (
      !this.application.employeeName.trim() ||
      !this.application.employeeId.trim() ||
      !this.application.currentRole.trim() ||
      this.application.yearsOfExperience === null ||
      this.application.yearsOfExperience < 0 ||
      !this.application.relevantSkills.trim() ||
      !this.application.interestMessage.trim() ||
      !this.application.resumeFileName.trim()
    ) {
      alert('Please fill in all the required fields before submitting.');
      return;
    }

    const applicationData = {
      jobId: this.selectedJob.jobId,
      jobTitle: this.selectedJob.title,

      employeeName: this.application.employeeName,
      employeeId: this.application.employeeId,
      currentRole: this.application.currentRole,
      yearsOfExperience: this.application.yearsOfExperience,
      relevantSkills: this.application.relevantSkills,
      interestMessage: this.application.interestMessage,
      resumeFileName: this.application.resumeFileName
    };

    console.log('SUBMITTING APPLICATION:', applicationData);

    this.careerService.submitApplication(applicationData).subscribe({
      next: (response) => {

        console.log('APPLICATION SUBMITTED:', response);

        alert('Application submitted successfully!');

        this.closeApplicationForm();

        this.application = {
          employeeName: '',
          employeeId: '',
          currentRole: '',
          yearsOfExperience: null,
          relevantSkills: '',
          interestMessage: '',
          resumeFileName: ''
        };

        this.cdr.detectChanges();
      },

      error: (err) => {
        console.error('Application submission failed:', err);

        alert('Failed to submit application. Please try again.');
      }
    });
  }
}
