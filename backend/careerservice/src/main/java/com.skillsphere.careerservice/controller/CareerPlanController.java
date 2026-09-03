package com.skillsphere.careerservice.controller;

import com.skillsphere.careerservice.dto.CareerPlanDTO;
import com.skillsphere.careerservice.service.CareerPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/career/plans")
@CrossOrigin(origins = "http://localhost:4200")
public class CareerPlanController {

    private final CareerPlanService careerPlanService;

    public CareerPlanController(CareerPlanService careerPlanService) {
        this.careerPlanService = careerPlanService;
    }

    @PostMapping
    public ResponseEntity<CareerPlanDTO> create(
            @RequestBody CareerPlanDTO dto) {

        return ResponseEntity.ok(careerPlanService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CareerPlanDTO>> getAll() {

        return ResponseEntity.ok(careerPlanService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareerPlanDTO> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(careerPlanService.getById(id));
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<CareerPlanDTO>> getByEmployee(
            @PathVariable UUID empId) {

        return ResponseEntity.ok(
                careerPlanService.getByEmployee(empId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareerPlanDTO> update(
            @PathVariable UUID id,
            @RequestBody CareerPlanDTO dto) {

        return ResponseEntity.ok(
                careerPlanService.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id) {

        careerPlanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}