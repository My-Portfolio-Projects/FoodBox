package com.foodbox.backend.repository;

import com.foodbox.backend.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    @Override
    List<Admin> findAll();

    Admin findAdminByAdminId(int adminId);

    @Override
    Admin save(Admin admin);

    @Override
    void deleteById(Integer adminId);

    Admin findAdminByEmail(String email);
}