package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dto.ContaRequestDTO;
import br.com.fiap.fintech.dto.ContaResponseDTO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.ContaRepository;
import br.com.fiap.fintech.repository.GastoRepository;
import br.com.fiap.fintech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ContaResponseDTO cadastrar(Integer idUsuario, ContaRequestDTO contaRequestDTO) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Conta conta = new Conta();

        conta.setNumeroConta(contaRequestDTO.getNumeroConta());
        conta.setNumeroAgencia(contaRequestDTO.getNumeroAgencia());
        conta.setTipoConta(contaRequestDTO.getTipoConta());
        conta.setSaldoAtual(contaRequestDTO.getSaldoAtual());
        conta.setDataAbertura(contaRequestDTO.getDataAbertura());
        conta.setUsuario(usuario);

         contaRepository.save(conta);

        return new ContaResponseDTO(
                conta.getId(),
                conta.getNumeroAgencia(),
                conta.getNumeroConta(),
                conta.getTipoConta(),
                conta.getSaldoAtual(),
                conta.getDataAbertura()
        );
    }

    public ContaResponseDTO atualizar(Integer idUsuario, Integer idConta,  ContaRequestDTO contaRequestDTO) {


        Conta conta =  contaRepository.findById(idConta).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta inexistente"));
        if(!conta.getUsuario().getId().equals(idUsuario)) {

            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Conta não vinculada a este usuário");
        }

        conta.setNumeroConta(contaRequestDTO.getNumeroConta());
        conta.setNumeroAgencia(contaRequestDTO.getNumeroAgencia());
        conta.setTipoConta(contaRequestDTO.getTipoConta());
        conta.setSaldoAtual(contaRequestDTO.getSaldoAtual());
        conta.setDataAbertura(contaRequestDTO.getDataAbertura());

       contaRepository.save(conta);

        return new ContaResponseDTO(
                conta.getId(),
                conta.getNumeroAgencia(),
                conta.getNumeroConta(),
                conta.getTipoConta(),
                conta.getSaldoAtual(),
                conta.getDataAbertura()
        );
    }

    public void remover(Integer idUsuario, Integer idConta) {

        Conta conta = contaRepository.findById(idConta).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta inexistente"));

        if(!conta.getUsuario().getId().equals(idUsuario)) {

            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Conta não associada a usuário");
        }

        contaRepository.delete(conta);
    }

    public List<ContaResponseDTO> buscarTodas(Integer idUsuario) {

      Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
              new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        List<ContaResponseDTO> listaResponseDTO = new ArrayList<>();
        for(Conta conta : contaRepository.findByUsuarioId(usuario.getId())){

            ContaResponseDTO contaResponseDTO = new ContaResponseDTO(
                    conta.getId(),
                    conta.getNumeroAgencia(),
                    conta.getNumeroConta(),
                    conta.getTipoConta(),
                    conta.getSaldoAtual(),
                    conta.getDataAbertura()
            );
            listaResponseDTO.add(contaResponseDTO);
        }

        return listaResponseDTO;


    }

    public ContaResponseDTO buscarPorId(Integer idUsuario, Integer idConta) {

      Conta conta = contaRepository.findById(idConta).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta inexistente"));

      if (!conta.getUsuario().getId().equals(idUsuario)) {

          throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Conta não associada a usuário");
      }
        return new ContaResponseDTO(
                conta.getId(),
                conta.getNumeroAgencia(),
                conta.getNumeroConta(),
                conta.getTipoConta(),
                conta.getSaldoAtual(),
                conta.getDataAbertura()
        );

    }
}
