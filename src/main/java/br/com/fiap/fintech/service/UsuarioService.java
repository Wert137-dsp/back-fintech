package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dto.UsuarioRequestDTO;
import br.com.fiap.fintech.dto.UsuarioResponseDTO;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO usuarioRequestDTO) {

        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setDescricao(usuarioRequestDTO.getDescricao());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(passEncoder.encode(usuarioRequestDTO.getSenha()));
        usuario.setDataNascimento(usuarioRequestDTO.getDataNascimento());

        usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDescricao(),
                usuario.getEmail(),
                usuario.getDataNascimento(),
                usuario.getDataRegistro(),
                usuario.getDataAtualizacao()
        );
    }

    public UsuarioResponseDTO login(String email, String senha) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findByEmail(email);

        if(usuarioAtual.isPresent()) {

            Usuario usuario = usuarioAtual.get();

            if(passEncoder.matches(senha, usuario.getSenha())) {

                return new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getDescricao(),
                        usuario.getEmail(),
                        usuario.getDataNascimento(),
                        usuario.getDataRegistro(),
                        usuario.getDataAtualizacao()
                );

            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    public UsuarioResponseDTO atualizar(Integer id, UsuarioRequestDTO dto) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isPresent()) {

            Usuario usuario = usuarioAtual.get();
            usuario.setNome(dto.getNome());
            usuario.setDescricao(dto.getDescricao());
            usuario.setEmail(dto.getEmail());
            usuario.setDataNascimento(dto.getDataNascimento());

            if(dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                usuario.setSenha(passEncoder.encode(dto.getSenha()));
            }

            usuarioRepository.save(usuario);

            return new UsuarioResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getDescricao(),
                    usuario.getEmail(),
                    usuario.getDataNascimento(),
                    usuario.getDataRegistro(),
                    usuario.getDataAtualizacao());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    public void excluir(Integer id) {

        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }
}
