package com.abinbev.b2b.icarrosmockapi.controllers;

import com.abinbev.b2b.icarrosmockapi.constants.LogConstants;
import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import com.abinbev.b2b.icarrosmockapi.services.PasswordService;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passwords")
class PasswordsControllerImpl implements PasswordsController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordsControllerImpl.class);
  private final PasswordService passwordService;

  public PasswordsControllerImpl(PasswordService passwordService) {
    this.passwordService = passwordService;
  }

  @PostMapping("/validate/{password}")
  public ResponseEntity<ValidatePasswordResponseDTO> validatePassword(
      @PathVariable @NotBlank final String password) {

    LOGGER.info(LogConstants.RECEIVED_REQUEST);
    final ValidatePasswordResponseDTO response = passwordService.validatePassword(password);

    if (response.isValid()) {
      LOGGER.info(LogConstants.PASSWORD_VALIDATED_SUCCESSFULLY);
      return ResponseEntity.ok(response);
    } else {
      LOGGER.warn(LogConstants.PASSWORD_VALIDATION_FAILED);
      return ResponseEntity.badRequest().body(response);
    }
  }
}
