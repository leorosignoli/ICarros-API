package com.abinbev.b2b.icarrosmockapi.controllers;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import com.abinbev.b2b.icarrosmockapi.services.PasswordService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwords")
class PasswordsControllerImpl implements PasswordsController {
  
  private final PasswordService passwordService;

  public PasswordsControllerImpl(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  @PostMapping("/validate/{password}")
  public ResponseEntity<ValidatePasswordResponseDTO> validatePassword(
      @PathVariable @NotBlank final String password) {

    final ValidatePasswordResponseDTO response = passwordService.isValidPassword(password);

    return response.isValid()
        ? ResponseEntity.ok(response)
        : ResponseEntity.badRequest().body(response);
  }
}
