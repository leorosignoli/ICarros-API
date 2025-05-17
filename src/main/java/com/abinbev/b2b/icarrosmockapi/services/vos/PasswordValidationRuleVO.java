package com.abinbev.b2b.icarrosmockapi.services.vos;

import java.util.function.Predicate;

public record PasswordValidationRuleVO(String errorMessage, Predicate<String> predicate) {}
