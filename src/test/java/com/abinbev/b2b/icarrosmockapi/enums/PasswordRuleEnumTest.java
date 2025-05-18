package com.abinbev.b2b.icarrosmockapi.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PasswordRuleEnumTest {

  @Test
  void shouldReturnTrueWhenPasswordIsShorterThanMinLength() {
    String shortPwd = "Ab1!xy";
    assertThat(PasswordRuleEnum.MIN_LENGTH.test(shortPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordIsAtLeastMinLength() {
    String validLengthPwd = "Ab1!xyz89";
    assertThat(PasswordRuleEnum.MIN_LENGTH.test(validLengthPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoDigit() {
    String noDigitPwd = "Abcdef!gh";
    assertThat(PasswordRuleEnum.ONE_DIGIT.test(noDigitPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneDigit() {
    String digitPwd = "Abc1def!g";
    assertThat(PasswordRuleEnum.ONE_DIGIT.test(digitPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoLowercase() {
    String noLowercasePwd = "ABC123!@#";
    assertThat(PasswordRuleEnum.ONE_LOWERCASE.test(noLowercasePwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneLowercase() {
    String lowercasePwd = "ABCd123!@";
    assertThat(PasswordRuleEnum.ONE_LOWERCASE.test(lowercasePwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoUppercase() {
    String noUppercasePwd = "abc123!@#";
    assertThat(PasswordRuleEnum.ONE_UPPERCASE.test(noUppercasePwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneUppercase() {
    String uppercasePwd = "abcD123!@";
    assertThat(PasswordRuleEnum.ONE_UPPERCASE.test(uppercasePwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasNoSpecialChar() {
    String noSpecialCharPwd = "Abc123def";
    assertThat(PasswordRuleEnum.ONE_SPECIAL_CHAR.test(noSpecialCharPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasAtLeastOneSpecialChar() {
    String specialCharPwd = "Abc123!def";
    assertThat(PasswordRuleEnum.ONE_SPECIAL_CHAR.test(specialCharPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordHasRepeatedCharacters() {
    String repeatedCharsPwd = "Aabc123!a";
    assertThat(PasswordRuleEnum.REPEATED_CHARS.test(repeatedCharsPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordHasNoRepeatedCharacters() {
    String uniqueCharsPwd = "Abc123!@";
    assertThat(PasswordRuleEnum.REPEATED_CHARS.test(uniqueCharsPwd)).isFalse();
  }

  @Test
  void shouldReturnTrueWhenPasswordContainsExtraordinaryCharacters() {
    String extraordinaryCharPwd = "Abc123!@😊";
    assertThat(PasswordRuleEnum.NO_EXTRAORDINARY_CHARS.test(extraordinaryCharPwd)).isTrue();
  }

  @Test
  void shouldReturnFalseWhenPasswordContainsOnlyAllowedCharacters() {
    String allowedCharsPwd = "Abc123!@";
    assertThat(PasswordRuleEnum.NO_EXTRAORDINARY_CHARS.test(allowedCharsPwd)).isFalse();
  }
}
