package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dto.GastoRequestDTO;
import br.com.fiap.fintech.dto.GastoResponseDTO;
import br.com.fiap.fintech.dto.UsuarioRequestDTO;
import br.com.fiap.fintech.model.Gasto;
import br.com.fiap.fintech.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario/{id_usuario}/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GastoResponseDTO adicionar(@PathVariable("id_usuario") Integer idUsuario, @RequestBody GastoRequestDTO gastoRequestDTO) {

        return gastoService.adicionar(idUsuario, gastoRequestDTO);
    }

    @DeleteMapping("/{id_gasto}/excluir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("id_gasto") Integer idGasto) {
        gastoService.remover(idGasto, idUsuario);
    }

    @PutMapping("/{id_gasto}/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public GastoResponseDTO atualizar(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("id_gasto") Integer idGasto, @RequestBody GastoRequestDTO gastoRequestDTO) {

        return gastoService.atualizar(idUsuario, idGasto, gastoRequestDTO);
    }

    @GetMapping("/{id_gasto}")
    @ResponseStatus(HttpStatus.OK)
    public GastoResponseDTO buscarPorId(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("id_gasto") Integer idGasto) {

        return gastoService.buscarPorId(idGasto, idUsuario);
    }

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public List<GastoResponseDTO> buscarTodos(@PathVariable("id_usuario") Integer idUsuario) {

        return gastoService.buscarTodos(idUsuario);
    }

}
