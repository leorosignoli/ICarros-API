package com.abinbev.b2b.icarrosmockapi.controllers;

import com.abinbev.b2b.icarrosmockapi.services.PasswordService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwords")
class PasswordsController {

    private final PasswordService passwordService;

    public PasswordsController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/validate/{password}")
    public Boolean validatePassword(@PathVariable final String password) {

        return passwordService.isValidPassword(password);
    }
}
