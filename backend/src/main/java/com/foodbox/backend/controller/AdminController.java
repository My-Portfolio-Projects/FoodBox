package com.foodbox.backend.controller;

import com.foodbox.backend.entity.Admin;
import com.foodbox.backend.exception.NotFoundException;
import com.foodbox.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping(value="/api")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/admins/{adminId}")
    public Admin getAdminById(@PathVariable int adminId) {
        return adminService.getAdminById(adminId);
    }
    @PostMapping("/admins/login")
    public int getCustomerByEmail(@RequestParam String email, @RequestParam String password) {
        Admin admin = adminService.getAdminByEmail(email);
        if(admin.getPassword().equals(password))return admin.getAdminId();
        else throw new NotFoundException("Admin Not Found!");
    }

    @PostMapping("/admin/register")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/admins/{adminId}")
    public Admin updateAdmin(@PathVariable int adminId, @RequestBody Admin admin) {
        return adminService.updateAdmin(adminId, admin);
    }

    @DeleteMapping("/admins/{adminId}")
    public void deleteAdmin(@PathVariable int adminId) {
        adminService.deleteAdmin(adminId);
    }
}
