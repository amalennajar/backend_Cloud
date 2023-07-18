package tn.esprit.healthcloud.services;

import java.util.List;
import tn.esprit.healthcloud.entities.Department;

public interface IDepartmentService {
    Department createDepartment(Department department);
    Department updateDepartment(int id, Department department);
    void deleteDepartment(int id);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
    List<Department> searchDepartments(String searchTerm);

}