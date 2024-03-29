package com.project.project.controller;
import com.project.project.model.Admin;
import com.project.project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.Set;


@RestController
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/admins")
    public Set<Admin> getAllAdmins() {
        return (Set<Admin>) adminRepository.findAll();
    }

    @PostMapping(value = "/admins", consumes =  "application/json", produces = "application/json")
    public Admin createPost(@Valid @RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/admins/{id_admin}")
    public Admin updateAdmin (@PathVariable long id_admin,@Valid  @RequestBody Admin postRequest) {
        return adminRepository.findById(id_admin).map(admin -> {
            return adminRepository.save(admin);
        }).orElseThrow(() -> new ResourceAccessException("id_admin " + " not found"));
    }



    @DeleteMapping("/admin/{admin_id}")
    public ResponseEntity<?> deletPost(@PathVariable long admin_id) {
        return adminRepository.findById(admin_id).map(post -> {
            adminRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceAccessException("admin_id " + " not found"));
    }


}
