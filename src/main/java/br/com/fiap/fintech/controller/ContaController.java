package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dto.ContaRequestDTO;
import br.com.fiap.fintech.dto.ContaResponseDTO;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario/{id_usuario}/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO cadastrar(@PathVariable("id_usuario") Integer idUsuario, @RequestBody ContaRequestDTO contaRequestDTO){
        return contaService.cadastrar(idUsuario, contaRequestDTO);
    }

    @PutMapping("/{id_conta}/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponseDTO atualizar(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("id_conta") Integer idConta, @RequestBody ContaRequestDTO contaRequestDTO){

        return contaService.atualizar(idUsuario, idConta, contaRequestDTO);
    }

    @DeleteMapping("/{id_conta}/excluir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("id_conta") Integer idConta){
        contaService.remover(idUsuario, idConta);
    }

    @GetMapping("/{id_conta}")
    @ResponseStatus(HttpStatus.OK)
    public ContaResponseDTO buscarConta(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("id_conta") Integer idConta){

        return contaService.buscarPorId(idUsuario, idConta);
    }

    @GetMapping("/todas")
    @ResponseStatus(HttpStatus.OK)
    public List<ContaResponseDTO> buscarTodas(@PathVariable Integer id_usuario){

        return contaService.buscarTodas(id_usuario);
    }
}
