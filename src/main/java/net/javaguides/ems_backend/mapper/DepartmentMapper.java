package net.javaguides.ems_backend.mapper;

import net.javaguides.ems_backend.dto.DepartmentDto;
import net.javaguides.ems_backend.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDescription()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDescription()
        );
    }
}
