package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dto.LoginRequestDTO;
import br.com.fiap.fintech.dto.UsuarioRequestDTO;
import br.com.fiap.fintech.dto.UsuarioResponseDTO;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO cadastrar(@RequestBody UsuarioRequestDTO usuarioRequestDTO){

        return usuarioService.cadastrar(usuarioRequestDTO);
    }

    @PutMapping("/{id_usuario}/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO atualizar(@PathVariable("id_usuario") Integer idUsuario, @RequestBody UsuarioRequestDTO usuario){

        return usuarioService.atualizar(idUsuario, usuario);
    }

    @DeleteMapping("/{id_usuario}/excluir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("id_usuario") Integer idUsuario){

        usuarioService.excluir(idUsuario);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){

        return usuarioService.login(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
    }
}
