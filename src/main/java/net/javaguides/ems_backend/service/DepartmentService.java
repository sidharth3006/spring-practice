package net.javaguides.ems_backend.service;

import net.javaguides.ems_backend.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long id);

    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);

    void deleteDepartment(Long id);
}
