package de.aufgabe.tilgungsplan.controller;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import de.aufgabe.tilgungsplan.service.impl.TilgungsplanServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TilgungsplanController.class)
public class TilgungsplanControllerTest {

  @Autowired private MockMvc mockMvc;

  @SpyBean private TilgungsplanServiceImpl tilgungsplanService;

  @Test
  public void sollteZuTilgungsplanWeiterleiten() throws Exception {
    this.mockMvc
        .perform(get("/"))
        .andExpect(redirectedUrl("/tilgungsplan"))
        .andExpect(status().is3xxRedirection());
  }

  @Test
  public void sollteTilgungsplanAufrufen() throws Exception {
    this.mockMvc.perform(get("/tilgungsplan")).andExpect(status().isOk());
  }

  @Test
  public void sollteTilgungsplanErstellen() throws Exception {
    this.mockMvc
        .perform(
            post("/tilgungsplan")
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("darlehensBetrag", "1000000"))
        .andExpect(status().isOk())
        .andExpect(view().name("tilgungsplan"))
        .andExpect(model().attributeExists("tilgungsplan"));
  }
}
