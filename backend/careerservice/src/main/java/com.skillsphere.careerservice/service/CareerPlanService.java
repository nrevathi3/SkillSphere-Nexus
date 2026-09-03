package com.skillsphere.careerservice.service;

import com.skillsphere.careerservice.dto.CareerPlanDTO;
import com.skillsphere.careerservice.entity.CareerPlan;
import com.skillsphere.careerservice.repository.CareerPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CareerPlanService {

    private final CareerPlanRepository careerPlanRepository;

    public CareerPlanService(CareerPlanRepository careerPlanRepository) {
        this.careerPlanRepository = careerPlanRepository;
    }

    public CareerPlanDTO create(CareerPlanDTO dto) {

        int score = calculatePromotionScore(dto);

        CareerPlan plan = CareerPlan.builder()
                .empId(dto.getEmpId())
                .employeeName(dto.getEmployeeName())
                .currentRole(dto.getCurrentRole())
                .targetRole(dto.getTargetRole())
                .progress(dto.getProgress())
                .mentor(dto.getMentor())
                .skillGaps(dto.getSkillGaps())
                .trainingPlan(dto.getTrainingPlan())
                .promotionScore(score)
                .promotionEligible(score >= 80)
                .status(CareerPlan.PlanStatus.ACTIVE)
                .build();

        return toDTO(careerPlanRepository.save(plan));
    }

    public List<CareerPlanDTO> getAll() {
        return careerPlanRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CareerPlanDTO getById(UUID id) {
        return careerPlanRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Career plan not found"));
    }

    public List<CareerPlanDTO> getByEmployee(UUID empId) {
        return careerPlanRepository.findByEmpId(empId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CareerPlanDTO update(UUID id, CareerPlanDTO dto) {

        CareerPlan plan = careerPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career plan not found"));

        plan.setEmployeeName(dto.getEmployeeName());
        plan.setCurrentRole(dto.getCurrentRole());
        plan.setTargetRole(dto.getTargetRole());
        plan.setProgress(dto.getProgress());
        plan.setMentor(dto.getMentor());
        plan.setSkillGaps(dto.getSkillGaps());
        plan.setTrainingPlan(dto.getTrainingPlan());

        int score = calculatePromotionScore(dto);

        plan.setPromotionScore(score);
        plan.setPromotionEligible(score >= 80);

        if (dto.getStatus() != null) {
            plan.setStatus(CareerPlan.PlanStatus.valueOf(dto.getStatus()));
        }

        return toDTO(careerPlanRepository.save(plan));
    }

    public void delete(UUID id) {
        careerPlanRepository.deleteById(id);
    }

    private int calculatePromotionScore(CareerPlanDTO dto) {

        int progress = dto.getProgress() == null ? 0 : dto.getProgress();

        int score = (int) (progress * 0.60);

        if (dto.getSkillGaps() == null ||
                dto.getSkillGaps().trim().isEmpty()) {
            score += 20;
        }

        if (dto.getTrainingPlan() != null &&
                !dto.getTrainingPlan().trim().isEmpty()) {
            score += 20;
        }

        return Math.min(score, 100);
    }

    private CareerPlanDTO toDTO(CareerPlan plan) {

        return CareerPlanDTO.builder()
                .planId(plan.getPlanId())
                .empId(plan.getEmpId())
                .employeeName(plan.getEmployeeName())
                .currentRole(plan.getCurrentRole())
                .targetRole(plan.getTargetRole())
                .progress(plan.getProgress())
                .mentor(plan.getMentor())
                .skillGaps(plan.getSkillGaps())
                .trainingPlan(plan.getTrainingPlan())
                .promotionScore(plan.getPromotionScore())
                .promotionEligible(plan.getPromotionEligible())
                .status(plan.getStatus() != null ? plan.getStatus().name() : null)
                .build();
    }
}