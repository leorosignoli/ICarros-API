package com.abinbev.b2b.icarrosmockapi.services;

import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.AT_LEAST_ONE_DIGIT;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.AT_LEAST_ONE_SPECIAL_CHAR;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.NINE_CHARS_LONG;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.NO_REPEATED_CHARACTERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import com.abinbev.b2b.icarrosmockapi.properties.PasswordValidationProperties;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PasswordServiceImplTest {

  @Mock private PasswordValidationProperties passwordValidationProperties;
  private PasswordServiceImpl passwordService;

  @Test
  void shouldPassValidationWhenPasswordMeetsAllCriteria() {
    when(passwordValidationProperties.getEnableValidations())
        .thenReturn(
            List.of(
                "MIN_LENGTH", "ONE_DIGIT", "ONE_UPPERCASE", "ONE_LOWERCASE", "ONE_SPECIAL_CHAR"));

    passwordService = new PasswordServiceImpl(passwordValidationProperties);

    String validPassword = "Aa1@abcde";
    ValidatePasswordResponseDTO response = passwordService.isValidPassword(validPassword);

    assertThat(response.isValid()).isTrue();
    assertThat(response.errors()).isEmpty();
  }

  @Test
  void shouldFailValidationWhenPasswordIsTooShort() {
    when(passwordValidationProperties.getEnableValidations()).thenReturn(List.of("MIN_LENGTH"));

    passwordService = new PasswordServiceImpl(passwordValidationProperties);

    String shortPassword = "A1@a";
    ValidatePasswordResponseDTO response = passwordService.isValidPassword(shortPassword);

    assertThat(response.isValid()).isFalse();
    assertThat(response.errors()).containsExactly(NINE_CHARS_LONG);
  }

  @Test
  void shouldFailValidationWhenPasswordLacksDigit() {
    when(passwordValidationProperties.getEnableValidations()).thenReturn(List.of("ONE_DIGIT"));

    passwordService = new PasswordServiceImpl(passwordValidationProperties);

    String noDigitPassword = "Password@";
    ValidatePasswordResponseDTO response = passwordService.isValidPassword(noDigitPassword);

    assertThat(response.isValid()).isFalse();
    assertThat(response.errors()).containsExactly(AT_LEAST_ONE_DIGIT);
  }

  @Test
  void shouldFailMultipleValidationsWhenPasswordViolatesSeveralRules() {
    when(passwordValidationProperties.getEnableValidations())
        .thenReturn(List.of("MIN_LENGTH", "ONE_DIGIT", "ONE_SPECIAL_CHAR"));

    passwordService = new PasswordServiceImpl(passwordValidationProperties);

    String badPassword = "abc";
    ValidatePasswordResponseDTO response = passwordService.isValidPassword(badPassword);

    assertThat(response.isValid()).isFalse();
    assertThat(response.errors())
        .contains(NINE_CHARS_LONG, AT_LEAST_ONE_DIGIT, AT_LEAST_ONE_SPECIAL_CHAR);
  }

  @Test
  void shouldPassValidationWhenOnlyNoExtraordinaryCharsEnabledAndPasswordIsSimple() {
    when(passwordValidationProperties.getEnableValidations())
        .thenReturn(List.of("NO_EXTRAORDINARY_CHARS"));

    passwordService = new PasswordServiceImpl(passwordValidationProperties);

    String simplePassword = "Simple123!";
    ValidatePasswordResponseDTO response = passwordService.isValidPassword(simplePassword);

    assertThat(response.isValid()).isTrue();
    assertThat(response.errors()).isEmpty();
  }

  @Test
  void shouldFailValidationWhenPasswordHasRepeatedCharacters() {
    when(passwordValidationProperties.getEnableValidations()).thenReturn(List.of("REPEATED_CHARS"));

    passwordService = new PasswordServiceImpl(passwordValidationProperties);

    String repeatedCharsPassword = "aaaBBB123!";
    ValidatePasswordResponseDTO response = passwordService.isValidPassword(repeatedCharsPassword);

    assertThat(response.isValid()).isFalse();
    assertThat(response.errors()).containsExactly(NO_REPEATED_CHARACTERS);
  }
}
