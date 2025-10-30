package br.com.fiap.fintech.dto;

import java.time.LocalDate;

public record GastoResponseDTO(Integer id, String nome, String descricao, String tipo, Double valor, LocalDate dataGasto) {
}
