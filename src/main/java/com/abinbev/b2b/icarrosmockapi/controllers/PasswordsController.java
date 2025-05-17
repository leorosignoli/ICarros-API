package com.abinbev.b2b.icarrosmockapi.controllers;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;

public interface PasswordsController {

  @Operation(
      summary = "Validate a password",
      description =
          """
            Returns `true` if the password is valid according to the following business rules:
                - At least 9 characters
                - At least 1 digit
                - At least 1 lowercase letter
                - At least 1 uppercase letter
                - At least 1 special character from the set: !@#$%^&*()-+
                - No repeated characters
            """)
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Password validation result",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ValidatePasswordResponseDTO.class),
                    examples = {
                      @ExampleObject(name = "ValidPassword", value = "{\"isValid\": true}"),
                      @ExampleObject(
                          name = "InvalidPassword",
                          value =
                              "{\"isValid\": false, \"errors\": [\"Password must contain at least one digit\", \"Password must not contain repeated characters\"]}")
                    })),
        @ApiResponse(responseCode = "400", description = "Invalid password format")
      })
  ResponseEntity<ValidatePasswordResponseDTO> validatePassword(
      @Parameter(description = "The password to be validated.", required = true) @NotBlank
          final String password);
}
