import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [CommonModule, RouterLink],
    templateUrl: './dashboard.html',
    styleUrl: './dashboard.scss'
})
export class Dashboard {

    // M1 - Skills
    totalSkills = 4;
    skillCoverage = 100;

    // M2 - Learning
    totalCourses = 1;
    activeEnrollments = 1;
    completionRate = 100;

    // M3 - Certifications
    totalCertifications = 1;
    activeCertifications = 1;
    expiringCertifications = 0;

    // M4 - Career
    careerPlans = 1;
    promotionEligible = 1;
    averageProgress = 70;

    // Jobs
    availableJobs = 1;
    applications = 1;
}
