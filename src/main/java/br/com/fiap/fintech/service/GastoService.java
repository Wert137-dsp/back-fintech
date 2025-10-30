package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dto.GastoRequestDTO;
import br.com.fiap.fintech.dto.GastoResponseDTO;
import br.com.fiap.fintech.model.Gasto;
import br.com.fiap.fintech.model.Usuario;
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
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public GastoResponseDTO adicionar(Integer idUsuario, GastoRequestDTO gastoRequestDTO) {


        Usuario usuario =  usuarioRepository.findById(idUsuario).orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Gasto gasto = new Gasto();

        gasto.setNome(gastoRequestDTO.getNome());
        gasto.setDescricao(gastoRequestDTO.getDescricao());
        gasto.setTipo(gastoRequestDTO.getTipo());
        gasto.setValor(gastoRequestDTO.getValor());
        gasto.setDataGasto(gastoRequestDTO.getDataGasto());
        gasto.setUsuario(usuario);

       gastoRepository.save(gasto);

        return new GastoResponseDTO(
                gasto.getId(),
                gasto.getNome(),
                gasto.getDescricao(),
                gasto.getTipo(),
                gasto.getValor(),
                gasto.getDataGasto()
        );

    }

    public List<GastoResponseDTO> buscarTodos(Integer idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow( () ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        List<GastoResponseDTO> listaResponseDTO = new ArrayList<>();
        for(Gasto gasto : gastoRepository.findByUsuarioId(usuario.getId())) {

            GastoResponseDTO gastoResponseDTO = new GastoResponseDTO(
                    gasto.getId(),
                    gasto.getNome(),
                    gasto.getDescricao(),
                    gasto.getTipo(),
                    gasto.getValor(),
                    gasto.getDataGasto()
            );

            listaResponseDTO.add(gastoResponseDTO);
        }

        return listaResponseDTO;

    }

    public GastoResponseDTO buscarPorId(Integer idGasto, Integer idUsuario) {

        Gasto gasto = gastoRepository.findById(idGasto).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Gasto não encontrado"));;

        if (!gasto.getUsuario().getId().equals(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Gasto não associado a usuário");
        }

        return new GastoResponseDTO(
                gasto.getId(),
                gasto.getNome(),
                gasto.getDescricao(),
                gasto.getTipo(),
                gasto.getValor(),
                gasto.getDataGasto()
        );
    }

    public GastoResponseDTO atualizar(Integer idUsuario, Integer idGasto, GastoRequestDTO gastoRequestDTO) {

        Gasto gasto = gastoRepository.findById(idGasto).orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Gasto não encontrado"));

        if (!gasto.getUsuario().getId().equals(idUsuario)) {

            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Gasto não vinculado a usuário especificado");
        }

            gasto.setNome(gastoRequestDTO.getNome());
            gasto.setDescricao(gastoRequestDTO.getDescricao());
            gasto.setTipo(gastoRequestDTO.getTipo());
            gasto.setValor(gastoRequestDTO.getValor());
            gasto.setDataGasto(gastoRequestDTO.getDataGasto());


            gastoRepository.save(gasto);

            return new GastoResponseDTO(
                    gasto.getId(),
                    gasto.getNome(),
                    gasto.getDescricao(),
                    gasto.getTipo(),
                    gasto.getValor(),
                    gasto.getDataGasto()
            );
    }


    public void remover(Integer idGasto, Integer idUsuario) {

       Gasto gasto = gastoRepository.findById(idGasto).orElseThrow(() ->
               new ResponseStatusException(HttpStatus.NOT_FOUND, "Gasto não encontrado"));

       if(!gasto.getUsuario().getId().equals(idUsuario)){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "gasto não pertence a este usuário");
       }

       gastoRepository.delete(gasto);

    }
}
