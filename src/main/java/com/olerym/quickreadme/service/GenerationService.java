package com.olerym.quickreadme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service responsible for generating GitHub README content using OllamaChatModel.
 *
 * <p>This service handles the business logic of creating a README based on a prompt template
 * and user-provided keywords. The prompt template is injected from the application properties.
 *
 * <p>Dependencies:
 * <ul>
 *   <li>{@link OllamaChatModel} – used to interact with the Ollama model.</li>
 *   <li>{@link OllamaOptions} – configuration options for the model (e.g., model name, temperature).</li>
 * </ul>
 *
 */
@Service
@RequiredArgsConstructor
public class GenerationService {
  private final OllamaChatModel chatClient;
  private final OllamaOptions ollamaOptions;
  @Value("${app.promptTemplate}")
  private String promptTemplate;

    /**
     * Generates a README text using the prompt template and provided keyword(s).
     *
     * @param keyword The keywords to include in the prompt for generating README content.
     * @return The generated README as a String.
     */
  public String generate(String keyword) {
    String promptText = promptTemplate.replace("{{keywords}}", keyword);
    ChatResponse jsonResponse = chatClient.call(new Prompt(promptText, ollamaOptions));
    return jsonResponse.getResult().getOutput().getText();
  }
}
