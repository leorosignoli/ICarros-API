package com.abinbev.b2b.icarrosmockapi.services;

import static java.util.stream.Collectors.toSet;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import com.abinbev.b2b.icarrosmockapi.enums.PasswordRuleEnum;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
class PasswordServiceImpl implements PasswordService {

  @Override
  public ValidatePasswordResponseDTO isValidPassword(final String password) {

    final Set<String> errors =
        getEnabledValidations().stream()
            .filter(rule -> rule.test(password))
            .map(PasswordRuleEnum::getErrorMessage)
            .collect(toSet());

    return new ValidatePasswordResponseDTO(errors.isEmpty(), errors);
  }

  private static List<PasswordRuleEnum> getEnabledValidations() {
    return Arrays.asList(PasswordRuleEnum.values());
  }
}
