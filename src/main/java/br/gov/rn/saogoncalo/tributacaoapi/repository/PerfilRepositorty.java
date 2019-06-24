package br.gov.rn.saogoncalo.tributacaoapi.repository;

import br.gov.rn.saogoncalo.tributacaoapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepositorty extends JpaRepository<Usuario, Long> {
}
