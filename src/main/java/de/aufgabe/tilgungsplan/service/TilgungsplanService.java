package de.aufgabe.tilgungsplan.service;

import de.aufgabe.tilgungsplan.dto.TilgungsplanDTO;
import java.math.BigDecimal;

public interface TilgungsplanService {

  TilgungsplanDTO berechneTilgungsplan(BigDecimal darlehensbetrag);
}
