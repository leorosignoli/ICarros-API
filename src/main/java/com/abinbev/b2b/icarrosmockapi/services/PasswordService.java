package com.abinbev.b2b.icarrosmockapi.services;

import com.abinbev.b2b.icarrosmockapi.controllers.dtos.ValidatePasswordResponseDTO;

public interface PasswordService {

  ValidatePasswordResponseDTO isValidPassword(final String password);
}
