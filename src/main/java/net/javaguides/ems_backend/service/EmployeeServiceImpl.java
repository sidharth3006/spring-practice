package net.javaguides.ems_backend.service;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Department;
import net.javaguides.ems_backend.entity.Employee;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;
import net.javaguides.ems_backend.mapper.EmployeeMapper;
import net.javaguides.ems_backend.repository.DepartmentRepository;
import net.javaguides.ems_backend.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDto.getDepartmentId()));

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee  = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"+id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(EmployeeMapper::mapToEmployeeDto);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"+id));

        if (employeeDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + employeeDto.getDepartmentId()));
            employee.setDepartment(department);
        }

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
       Employee employee = employeeRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Employee not exists with given id:"+id)
       );

       employeeRepository.deleteById(id);
    }


}
