package net.javaguides.ems_backend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<EmployeeDto> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeDto, id);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }



}
