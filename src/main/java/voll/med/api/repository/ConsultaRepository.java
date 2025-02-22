package voll.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import voll.med.api.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
