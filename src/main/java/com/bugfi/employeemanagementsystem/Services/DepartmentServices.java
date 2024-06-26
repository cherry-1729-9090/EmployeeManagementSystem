package com.bugfi.employeemanagementsystem.Services;

import com.bugfi.employeemanagementsystem.exceptions.AdminNotFoundException;
import com.bugfi.employeemanagementsystem.models.Admin;
import com.bugfi.employeemanagementsystem.models.Department;
import com.bugfi.employeemanagementsystem.repository.DepartmentRepository;

import java.util.List;

public class DepartmentServices implements  IDepartmentServices{
    private DepartmentRepository departmentRepository;
    AdminServices adminServices;
    @Override
    public List<Department> getAllDepartments(Admin admin) {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        return departments;
    }

    @Override
    public Department getDepartmentById(Long id) {
        Department department = (Department) departmentRepository.findById(id);
        return department;
    }

    @Override
    public void addDepartment(Department d, Admin admin) {
        verifyAdmin(admin);
        departmentRepository.save(d);
    }

    @Override
    public void updateDepartment(Department d, Long id, Admin admin) {
        verifyAdmin(admin);
        d.setId(id);
        departmentRepository.save(d);
    }

    @Override
    public void deleteAllDepartments(Admin admin) {
        verifyAdmin(admin);
        departmentRepository.deleteAll();
    }

    @Override
    public void deleteDepartmentByID(Long id, Admin admin) {
        verifyAdmin(admin);
        departmentRepository.delete(id);
    }

    public void verifyAdmin(Admin admin) {
        String password = admin.getPassword();
        try {
            Admin verif = adminServices.getAdmin(admin);
            if (!password.equals(verif.getPassword())) {
                throw new AdminNotFoundException("Admin not found", "Invalid password");
            }
        } catch (AdminNotFoundException e) {
            System.out.println("Admin not found: " + e.getMessage());
        }
    }

}
