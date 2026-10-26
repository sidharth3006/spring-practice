package net.javaguides.ems_backend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.DepartmentDto;
import net.javaguides.ems_backend.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartment = departmentService.updateDepartment(departmentDto, id);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
    }
}
