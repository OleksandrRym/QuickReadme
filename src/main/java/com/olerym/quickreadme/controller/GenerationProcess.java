package com.olerym.quickreadme.controller;

import com.olerym.quickreadme.service.GenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for handling README generation requests.
 *
 * <p>Endpoints:
 * <ul>
 *   <li>"/"       : Displays the home page with a keyword input form.</li>
 *   <li>"/result" : Generates README content using the provided keyword and displays the result page.</li>
 * </ul>
 *
 * <p>Uses {@link GenerationService} to handle the business logic of generating the README.
 */
@Controller
@RequiredArgsConstructor
public class GenerationProcess {
  private final GenerationService generationService;

  @GetMapping("/")
  public String home() {
    return "home-page";
  }

  @GetMapping("/result")
  String generation(@RequestParam String keyword, Model model) {
    String result = generationService.generate(keyword);
    model.addAttribute("keyword", keyword);
    model.addAttribute("result", result);
    return "generate";
  }
}
