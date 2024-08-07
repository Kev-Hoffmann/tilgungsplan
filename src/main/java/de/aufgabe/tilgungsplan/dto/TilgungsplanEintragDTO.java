package de.aufgabe.tilgungsplan.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TilgungsplanEintragDTO {

  private int jahr;
  private BigDecimal rate;
  private BigDecimal zinsanteil;
  private BigDecimal tilungsanteil;
  private BigDecimal restschulden;
}
