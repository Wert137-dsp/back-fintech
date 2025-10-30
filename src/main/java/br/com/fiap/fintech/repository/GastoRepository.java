package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

    List<Gasto> findByUsuarioId(Integer idUsuario);
}
