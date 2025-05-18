package com.abinbev.b2b.icarrosmockapi.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ValidatePasswordResponseDTO(boolean isValid, Set<String> errors) {

  public static ValidatePasswordResponseDTO validPasswordResponse() {
    return new ValidatePasswordResponseDTO(true, null);
  }

  public static ValidatePasswordResponseDTO invalidPasswordResponse(final Set<String> errors) {
    return new ValidatePasswordResponseDTO(false, errors);
  }
}
