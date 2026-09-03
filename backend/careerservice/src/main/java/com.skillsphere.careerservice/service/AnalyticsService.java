package com.skillsphere.careerservice.service;

import com.skillsphere.careerservice.dto.AnalyticsDTO;
import com.skillsphere.careerservice.entity.CareerPlan;
import com.skillsphere.careerservice.repository.CareerPlanRepository;
import com.skillsphere.careerservice.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final CareerPlanRepository careerPlanRepository;
    private final JobRepository jobRepository;

    public AnalyticsService(
            CareerPlanRepository careerPlanRepository,
            JobRepository jobRepository) {

        this.careerPlanRepository = careerPlanRepository;
        this.jobRepository = jobRepository;
    }

    public AnalyticsDTO getAnalytics() {

        List<CareerPlan> plans = careerPlanRepository.findAll();

        long totalPlans = plans.size();

        long activePlans = careerPlanRepository.countByStatus(
                CareerPlan.PlanStatus.ACTIVE
        );

        long completedPlans = careerPlanRepository.countByStatus(
                CareerPlan.PlanStatus.COMPLETED
        );

        long promotionEligible =
                careerPlanRepository.countByPromotionEligibleTrue();

        double averageProgress = plans.stream()
                .filter(plan -> plan.getProgress() != null)
                .mapToInt(CareerPlan::getProgress)
                .average()
                .orElse(0.0);

        long plansWithoutGaps = plans.stream()
                .filter(plan ->
                        plan.getSkillGaps() == null ||
                                plan.getSkillGaps().trim().isEmpty()
                )
                .count();

        double skillCoverage = totalPlans == 0
                ? 0.0
                : (plansWithoutGaps * 100.0) / totalPlans;

        long activeJobs = jobRepository.findByActiveTrue().size();

        return AnalyticsDTO.builder()
                .totalCareerPlans(totalPlans)
                .activeCareerPlans(activePlans)
                .completedPlans(completedPlans)
                .promotionEligible(promotionEligible)
                .averageProgress(averageProgress)
                .skillCoverage(skillCoverage)
                .activeJobs(activeJobs)
                .build();
    }
}