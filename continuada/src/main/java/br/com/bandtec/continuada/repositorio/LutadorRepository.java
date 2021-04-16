package br.com.bandtec.continuada.repositorio;

import br.com.bandtec.continuada.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    List<Lutador> OrderByForcaGolpeDesc();

    List<Lutador> findByVivoTrue();

    List<Lutador> findById
}
