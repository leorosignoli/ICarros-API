package com.abinbev.b2b.icarrosmockapi.enums;

import static org.assertj.core.api.Assertions.assertThat;

import com.abinbev.b2b.icarrosmockapi.services.PasswordRules;
import org.junit.jupiter.api.Test;

class PasswordRulesTest {

  @Test
  void shouldReturnTrueWhenPasswordIsShorterThanMinLength() {
    String shortPwd = "Ab1!xy";
    assertThat(PasswordRules.MIN_LENGTH.test(shortPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordIsAtLeastMinLength() {
    String validLengthPwd = "Ab1!xyz89";
    assertThat(PasswordRules.MIN_LENGTH.test(validLengthPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoDigit() {
    String noDigitPwd = "Abcdef!gh";
    assertThat(PasswordRules.ONE_DIGIT.test(noDigitPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneDigit() {
    String digitPwd = "Abc1def!g";
    assertThat(PasswordRules.ONE_DIGIT.test(digitPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoLowercase() {
    String noLowercasePwd = "ABC123!@#";
    assertThat(PasswordRules.ONE_LOWERCASE.test(noLowercasePwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneLowercase() {
    String lowercasePwd = "ABCd123!@";
    assertThat(PasswordRules.ONE_LOWERCASE.test(lowercasePwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoUppercase() {
    String noUppercasePwd = "abc123!@#";
    assertThat(PasswordRules.ONE_UPPERCASE.test(noUppercasePwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneUppercase() {
    String uppercasePwd = "abcD123!@";
    assertThat(PasswordRules.ONE_UPPERCASE.test(uppercasePwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoSpecialChar() {
    String noSpecialCharPwd = "Abc123def";
    assertThat(PasswordRules.ONE_SPECIAL_CHAR.test(noSpecialCharPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneSpecialChar() {
    String specialCharPwd = "Abc123!def";
    assertThat(PasswordRules.ONE_SPECIAL_CHAR.test(specialCharPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasRepeatedCharacters() {
    String repeatedCharsPwd = "Aabc123!a";
    assertThat(PasswordRules.REPEATED_CHARS.test(repeatedCharsPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasNoRepeatedCharacters() {
    String uniqueCharsPwd = "Abc123!@";
    assertThat(PasswordRules.REPEATED_CHARS.test(uniqueCharsPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordContainsExtraordinaryCharacters() {
    String extraordinaryCharPwd = "Abc123!@😊";
    assertThat(PasswordRules.NO_EXTRAORDINARY_CHARS.test(extraordinaryCharPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordContainsOnlyAllowedCharacters() {
    String allowedCharsPwd = "Abc123!@";
    assertThat(PasswordRules.NO_EXTRAORDINARY_CHARS.test(allowedCharsPwd)).isFalse();
  }
}
