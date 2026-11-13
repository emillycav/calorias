package br.com.calorias.dto;

import java.time.LocalDate;

public record ResumoDia(LocalDate data, double totalCalorias, int meta, double restante, boolean ultrapassou) {
}
