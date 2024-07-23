package de.aufgabe.tilgungsplan.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.aufgabe.tilgungsplan.dto.TilgungsplanDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TilgungsplanServiceImplTest {

  @Test
  public void berechneTilgungsplanFuer100000Darlehen() {
    TilgungsplanServiceImpl tilgungsplanService = new TilgungsplanServiceImpl();

    TilgungsplanDTO tilgungsplanDTO =
        tilgungsplanService.berechneTilgungsplan(
            BigDecimal.valueOf(100000),
            BigDecimal.valueOf(0.03),
            BigDecimal.valueOf(0.02),
            LocalDate.of(2024, 1, 1));

    assertNotNull(tilgungsplanDTO);
    assertEquals(31, tilgungsplanDTO.getTilgungsplanEintragList().size());
  }
}
