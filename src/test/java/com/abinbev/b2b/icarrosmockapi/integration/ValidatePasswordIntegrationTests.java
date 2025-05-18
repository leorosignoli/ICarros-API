package com.abinbev.b2b.icarrosmockapi.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ValidatePasswordIntegrationTests {

  @Autowired private TestRestTemplate restTemplate;

  private static final String VALIDATE_PASSWORD_URL = "/passwords/validate/{password}";

  @ParameterizedTest
  @ValueSource(strings = {"1234Abcdef@", "Abcdef1@9", "f1@9Abcde"})
  @DisplayName("should return valid true when password is valid")
  void shouldReturnValidTrueWhenPasswordIsValid(String validPassword) {

    ResponseEntity<ValidatePasswordResponseDTO> response =
        restTemplate.postForEntity(
            VALIDATE_PASSWORD_URL, null, ValidatePasswordResponseDTO.class, validPassword);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody())
        .isNotNull()
        .satisfies(
            body -> {
              assertThat(body.isValid()).isTrue();
              assertThat(body.errors()).isNullOrEmpty();
            });
  }

  @DisplayName("Should return list containign all errors When passowrd is invalid")
  @ParameterizedTest
  @ValueSource(
      strings = {
        "\uD83D\uDE01",
        "OoXasd.!,lwe",
        "123456789",
        "Abcdefghij",
        "Abcdef1",
        "Abcdef1@",
        "12345678",
        "Abc 1234!."
      })
  void shouldReturnInvalidWhenPasswordIsIncorrect(String invalidPassword) {

    ResponseEntity<ValidatePasswordResponseDTO> response =
        restTemplate.postForEntity(
            VALIDATE_PASSWORD_URL, null, ValidatePasswordResponseDTO.class, invalidPassword);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody())
        .isNotNull()
        .satisfies(
            body -> {
              assertThat(body.isValid()).isFalse();
              assertThat(body.errors()).isNotEmpty();
            });
  }
}
