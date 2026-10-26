package net.javaguides.ems_backend.mapper;

import net.javaguides.ems_backend.dto.DepartmentDto;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        DepartmentDto departmentDto = null;
        if (employee.getDepartment() != null) {
            departmentDto = DepartmentMapper.mapToDepartmentDto(employee.getDepartment());
        }
        return new EmployeeDto(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
            employee.getDepartment() != null ? employee.getDepartment().getId() : null,
            departmentDto
        );
    }


    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                null
        );
    }

}
