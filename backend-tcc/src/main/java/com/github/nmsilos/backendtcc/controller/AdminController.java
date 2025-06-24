package com.github.nmsilos.backendtcc.controller;

import com.github.nmsilos.backendtcc.model.Admin;
import com.github.nmsilos.backendtcc.repository.AdminRepository;
import com.github.nmsilos.backendtcc.security.Cripter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    /*
    @Autowired
    AdminRepository repository;

    @PostMapping("cadastrar")
    public void cadastrar(@RequestParam ("nome") String nome,
                          @RequestParam ("username") String username,
                          @RequestParam ("password") String password,
                          @RequestParam ("email") String email) {
        Admin admin = new Admin(nome, username, email, Cripter.criptografar(password));
        repository.save(admin);
    }
    */

}
