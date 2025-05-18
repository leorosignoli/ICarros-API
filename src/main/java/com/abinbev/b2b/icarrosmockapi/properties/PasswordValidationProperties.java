package com.abinbev.b2b.icarrosmockapi.properties;

import static com.abinbev.b2b.icarrosmockapi.constants.LogConstants.ENABLED_PASSWORD_VALIDATIONS;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "password.validation")
public record PasswordValidationProperties(Map<String, Boolean> enabledValidations) {

  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordValidationProperties.class);

  public List<String> getEnableValidations() {

    var enabled =
        enabledValidations.entrySet().stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .toList();

    LOGGER.info(ENABLED_PASSWORD_VALIDATIONS, enabled);
    return enabled;
  }
}
