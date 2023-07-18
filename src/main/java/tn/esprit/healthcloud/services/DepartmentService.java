package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Department;
import tn.esprit.healthcloud.exceptions.DepartmentNotFoundException;
import tn.esprit.healthcloud.repositories.DepartmentRepository;

import java.util.List;


@Service
@AllArgsConstructor

public class DepartmentService implements IDepartmentService{

    DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(int i, Department department) {
        Department existingDepartment = departmentRepository.findById(department.getId());
        if (existingDepartment == null) {
            throw new DepartmentNotFoundException("Department with id " + department.getId() + " not found");
        }
        existingDepartment.setDepName(department.getDepName());
        existingDepartment.setDepDesc(department.getDepDesc());
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = departmentRepository.findById(id);
        if (department == null) {
            throw new DepartmentNotFoundException("Department with id " + id + " not found");
        }
        departmentRepository.delete(department);
    }

    @Override
    public Department getDepartmentById(int id) {
        Department department = departmentRepository.findById(id);
        if (department == null) {
            throw new DepartmentNotFoundException("Department with id " + id + " not found");
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> searchDepartments(String searchTerm) {
        return departmentRepository.findByDepNameContainingOrDepDescContaining(searchTerm, searchTerm);
    }

}
