package de.aufgabe.tilgungsplan.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DarlehenDTO {

  private BigDecimal darlehensBetrag;
}
