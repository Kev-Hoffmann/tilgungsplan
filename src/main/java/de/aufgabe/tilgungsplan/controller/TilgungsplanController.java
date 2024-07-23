package de.aufgabe.tilgungsplan.controller;

import de.aufgabe.tilgungsplan.dto.DarlehenDTO;
import de.aufgabe.tilgungsplan.service.TilgungsplanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TilgungsplanController {

  private final TilgungsplanService tilgungsplanService;

  @GetMapping("/")
  public String index() {
    return "redirect:/tilgungsplan";
  }

  @GetMapping("/tilgungsplan")
  public String getTilgungsplan() {
    return "tilgungsplan";
  }

  @PostMapping("/tilgungsplan")
  public String postTilgungsplan(Model model, DarlehenDTO darlehen) {
    model.addAttribute(
        "tilgungsplan", tilgungsplanService.berechneTilgungsplan(darlehen.getDarlehensBetrag()));
    model.addAttribute("darlehensBetrag", darlehen.getDarlehensBetrag());
    return "tilgungsplan";
  }
}
