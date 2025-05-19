package com.abinbev.b2b.icarrosmockapi.services;

import static java.util.stream.Collectors.toSet;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import com.abinbev.b2b.icarrosmockapi.properties.PasswordValidationProperties;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

  private final List<PasswordRules> enabledValidations;

  public PasswordServiceImpl(final PasswordValidationProperties passwordValidationProperties) {

    enabledValidations =
        passwordValidationProperties.getEnableValidations().stream()
            .map(PasswordRules::valueOf)
            .toList();
  }

  @Override
  public ValidatePasswordResponseDTO validatePassword(final String password) {

    final Set<String> errors =
        enabledValidations.stream()
            .filter(rule -> rule.test(password))
            .map(PasswordRules::getErrorMessage)
            .collect(toSet());

    return new ValidatePasswordResponseDTO(errors.isEmpty(), errors);
  }
}
