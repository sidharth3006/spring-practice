package net.javaguides.ems_backend.service;

import lombok.AllArgsConstructor;
import net.javaguides.ems_backend.dto.DepartmentDto;
import net.javaguides.ems_backend.entity.Department;
import net.javaguides.ems_backend.exception.ResourceNotFoundException;
import net.javaguides.ems_backend.mapper.DepartmentMapper;
import net.javaguides.ems_backend.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDescription(departmentDto.getDescription());

        Department updatedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        departmentRepository.deleteById(id);
    }
}
