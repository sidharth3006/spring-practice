package net.javaguides.ems_backend.service;

import net.javaguides.ems_backend.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    Page<EmployeeDto> getAllEmployees(Pageable pageable);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);

    void deleteEmployee(Long id);
}
