import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SkillProfile } from '../skill-profile';

@Component({
  selector: 'app-employee-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-profile.html',
  styleUrl: './employee-profile.scss',
})
export class EmployeeProfile implements OnInit {

  employeeProfile: any;

  empId = '4fea4c16-b5f5-45c2-88c7-2324e45b82ee';

  constructor(
    private skillProfile: SkillProfile,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.skillProfile.getProfile(this.empId).subscribe({
      next: (data) => {
        this.employeeProfile = data;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Failed to load employee profile:', error);
      }
    });
  }
}
