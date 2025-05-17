package com.abinbev.b2b.icarrosmockapi.constants;

public abstract class ServiceConstants {

  private ServiceConstants() {}

  public static final String NINE_CHARS_LONG = "Password must be at least 9 characters long";
  public static final String AT_LEAST_ONE_DIGIT = "Password must contain at least one digit";
  public static final String AT_LEAST_ONE_LOWERCASE =
      "Password must contain at least one lowercase letter";
  public static final String AT_LEAST_ONE_UPPERCASE =
      "Password must contain at least one uppercase letter";
  public static final String AT_LEAST_ONE_SPECIAL_CHAR =
      "Password must contain at least one special character from the set: !@#$%^&*()-+";
  public static final String NO_REPEATED_CHARACTERS =
      "Password must not contain repeated characters";
}
