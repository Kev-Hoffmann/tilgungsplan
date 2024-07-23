package de.aufgabe.tilgungsplan.service.impl;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.*;

import de.aufgabe.tilgungsplan.dto.TilgungsplanDTO;
import de.aufgabe.tilgungsplan.dto.TilgungsplanEintragDTO;
import de.aufgabe.tilgungsplan.service.TilgungsplanService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TilgungsplanServiceImpl implements TilgungsplanService {

  public static final BigDecimal SOLLZINS = BigDecimal.valueOf(0.03);
  public static final BigDecimal TILGUNG = BigDecimal.valueOf(0.02);

  @Override
  public TilgungsplanDTO berechneTilgungsplan(BigDecimal darlehensbetrag) {
    return berechneTilgungsplan(darlehensbetrag, SOLLZINS, TILGUNG, LocalDate.now());
  }

  public TilgungsplanDTO berechneTilgungsplan(
      BigDecimal darlehensbetrag,
      BigDecimal zinssatz,
      BigDecimal tilgungssatz,
      LocalDate startDatum) {

    List<TilgungsplanEintragDTO> tilgungsplanEintragList = new ArrayList<>();

    BigDecimal restschuld = darlehensbetrag;

    BigDecimal monatlicherZinssatz = zinssatz.divide(BigDecimal.valueOf(12), 10, HALF_UP);
    BigDecimal monatlicherTilgungssatz = tilgungssatz.divide(BigDecimal.valueOf(12), 10, HALF_UP);
    BigDecimal monatlicheRate =
        restschuld.multiply(monatlicherZinssatz.add(monatlicherTilgungssatz)).setScale(2, UP);

    int monat = startDatum.getMonthValue();
    int jahr = startDatum.getYear();

    while (restschuld.compareTo(ZERO) > 0) {
      BigDecimal jahresZinsanteil = ZERO;
      BigDecimal jahresTilgungsanteil = ZERO;
      BigDecimal jahresRate = ZERO;

      while (monat <= 12 && restschuld.compareTo(ZERO) > 0) {

        BigDecimal zinsanteil = restschuld.multiply(monatlicherZinssatz).setScale(2, HALF_UP);
        BigDecimal tilgungsanteil = monatlicheRate.subtract(zinsanteil);

        if (monatlicheRate.compareTo(restschuld.add(zinsanteil)) > 0) {
          monatlicheRate = restschuld.add(zinsanteil);
          tilgungsanteil = monatlicheRate.subtract(zinsanteil);
        }

        restschuld = restschuld.subtract(tilgungsanteil);

        jahresZinsanteil = jahresZinsanteil.add(zinsanteil);
        jahresTilgungsanteil = jahresTilgungsanteil.add(tilgungsanteil);
        jahresRate = jahresRate.add(monatlicheRate);

        monat++;
      }

      tilgungsplanEintragList.add(
          new TilgungsplanEintragDTO(
              jahr, jahresRate, jahresZinsanteil, jahresTilgungsanteil, restschuld));

      jahr++;
      monat = 1;
    }

    return TilgungsplanDTO.builder().tilgungsplanEintragList(tilgungsplanEintragList).build();
  }
}
