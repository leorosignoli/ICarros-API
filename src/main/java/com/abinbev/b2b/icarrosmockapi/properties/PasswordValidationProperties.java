package com.abinbev.b2b.icarrosmockapi.properties;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "password.validation")
public record PasswordValidationProperties(Map<String, Boolean> enabledValidations) {

  public List<String> getEnableValidations() {
    return enabledValidations.entrySet().stream()
        .filter(Map.Entry::getValue)
        .map(Map.Entry::getKey)
        .toList();
  }
}
