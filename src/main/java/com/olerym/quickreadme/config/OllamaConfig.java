package com.olerym.quickreadme.config;

import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up OllamaChatModel options.
 *
 * <p>This class is responsible for creating a {@link OllamaOptions} bean, which configures the
 * behavior of the Ollama model during text generation.
 *
 * <p>Configuration values are read from application properties:
 *
 * <ul>
 *   <li>{@code spring.ai.ollama.chat.options.model} – the name of the model to use (e.g.,
 *       "llama2:7b").
 *   <li>{@code spring.ai.ollama.chat.options.temperature} – the temperature parameter controlling
 *       the creativity of the model's responses.
 * </ul>
 *
 * <p>The {@link OllamaOptions} bean can then be injected into services or controllers that perform
 * text generation.
 */
@Configuration
public class OllamaConfig {

  @Value("${spring.ai.ollama.chat.options.model}")
  private String modelName;

  @Value("${spring.ai.ollama.chat.options.temperature}")
  private Double temperature;

  @Bean
  public OllamaOptions OllamaOptions() {
    return OllamaOptions.builder().model(modelName).temperature(temperature).build();
  }
}
