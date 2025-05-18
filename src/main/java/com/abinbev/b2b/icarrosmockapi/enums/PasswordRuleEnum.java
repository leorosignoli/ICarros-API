package com.abinbev.b2b.icarrosmockapi.enums;

import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.AT_LEAST_ONE_DIGIT;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.AT_LEAST_ONE_LOWERCASE;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.AT_LEAST_ONE_SPECIAL_CHAR;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.AT_LEAST_ONE_UPPERCASE;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.NINE_CHARS_LONG;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.NO_EXTRAORDINARY_CHARACTERS;
import static com.abinbev.b2b.icarrosmockapi.constants.ServiceConstants.NO_REPEATED_CHARACTERS;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public enum PasswordRuleEnum {
  MIN_LENGTH(NINE_CHARS_LONG, minLength()),

  ONE_DIGIT(AT_LEAST_ONE_DIGIT, (String pwd) -> !Patterns.DIGIT.matcher(pwd).find()),

  ONE_LOWERCASE(AT_LEAST_ONE_LOWERCASE, pwd -> !Patterns.LOWER.matcher(pwd).find()),

  ONE_UPPERCASE(AT_LEAST_ONE_UPPERCASE, pwd -> !Patterns.UPPER.matcher(pwd).find()),

  ONE_SPECIAL_CHAR(AT_LEAST_ONE_SPECIAL_CHAR, pwd -> !Patterns.SPECIAL.matcher(pwd).find()),

  REPEATED_CHARS(NO_REPEATED_CHARACTERS, validateRepeatedCharacters()),

  NO_EXTRAORDINARY_CHARS(NO_EXTRAORDINARY_CHARACTERS, pwd -> Patterns.ANY.matcher(pwd).find());

  public static final int PASSWORD_MIN_LENGTH = 9;

  private final String errorMessage;
  private final Predicate<String> invalidPredicate;

  PasswordRuleEnum(String errorMessage, Predicate<String> invalidPredicate) {
    this.errorMessage = errorMessage;
    this.invalidPredicate = invalidPredicate;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public boolean test(final String pwd) {
    return invalidPredicate.test(pwd);
  }

  public static Predicate<String> minLength() {
    return password -> password.length() < PASSWORD_MIN_LENGTH;
  }

  private static Predicate<String> validateRepeatedCharacters() {
    return password -> {
      final Set<Character> seen = new HashSet<>();
      for (char c : password.toCharArray()) {
        if (seen.contains(c)) {
          return true;
        }
        seen.add(c);
      }
      return false;
    };
  }

  static final class Patterns {

    public static final Pattern DIGIT = Pattern.compile("\\p{Nd}");
    public static final Pattern LOWER = Pattern.compile("\\p{Ll}");
    public static final Pattern UPPER = Pattern.compile("\\p{Lu}");
    public static final Pattern SPECIAL = Pattern.compile("[!@#$%^&*()\\-+]");
    public static final Pattern ANY =
        Pattern.compile("[^\\p{Nd}\\p{Ll}\\p{Lu}!@#$%^&*()\\-+]", Pattern.UNICODE_CHARACTER_CLASS);

    private Patterns() {}
  }
}
