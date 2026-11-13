package br.com.calorias.dto;

import br.com.calorias.model.TipoRefeicao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record RegistroRequest(
        @NotNull(message = "alimentoId é obrigatório") Long alimentoId,
        @NotNull(message = "gramas é obrigatório") @Positive(message = "gramas deve ser maior que zero") Double gramas,
        TipoRefeicao tipo,
        LocalDate data) {
}
