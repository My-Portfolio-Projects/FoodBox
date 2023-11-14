package com.foodbox.backend.service;

import com.foodbox.backend.entity.Admin;
import com.foodbox.backend.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService  {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

     
    public Admin getAdminById(int adminId) {
        return adminRepository.findAdminByAdminId(adminId);
    }

     
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

     
    public Admin updateAdmin(int adminId, Admin admin) {
        admin.setAdminId(adminId);
        return adminRepository.save(admin);
    }

     
    public void deleteAdmin(int adminId) {
        adminRepository.deleteById(adminId);
    }

    public Admin getAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

}

