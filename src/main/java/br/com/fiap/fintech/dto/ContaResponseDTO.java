package br.com.fiap.fintech.dto;

import java.time.LocalDate;

public record ContaResponseDTO(Integer id, String numeroAgencia, String numeroConta, String tipoConta, Double saldoAtual, LocalDate dataAbertura) {
}
