package br.com.fiap.fintech.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponseDTO(Integer id, String nome, String descricao, String email, LocalDate dataNascimento, LocalDateTime dataCadastro, LocalDateTime dataAtualizacao) {
}
