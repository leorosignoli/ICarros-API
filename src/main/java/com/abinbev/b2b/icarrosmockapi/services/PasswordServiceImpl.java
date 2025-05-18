package com.abinbev.b2b.icarrosmockapi.services;

import static java.util.stream.Collectors.toSet;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import com.abinbev.b2b.icarrosmockapi.enums.PasswordRuleEnum;
import com.abinbev.b2b.icarrosmockapi.properties.PasswordValidationProperties;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordServiceImpl.class);
  private final List<PasswordRuleEnum> enabledValidations;

  PasswordServiceImpl(final PasswordValidationProperties passwordValidationProperties) {

    LOGGER.debug("Enabled password validations are: {}", passwordValidationProperties.getEnableValidations());
    enabledValidations =
        passwordValidationProperties.getEnableValidations().stream()
            .map(PasswordRuleEnum::valueOf)
            .toList();
  }

  @Override
  public ValidatePasswordResponseDTO isValidPassword(final String password) {

    final Set<String> errors =
        enabledValidations.stream()
            .filter(rule -> rule.test(password))
            .map(PasswordRuleEnum::getErrorMessage)
            .collect(toSet());

    return new ValidatePasswordResponseDTO(errors.isEmpty(), errors);
  }
}
