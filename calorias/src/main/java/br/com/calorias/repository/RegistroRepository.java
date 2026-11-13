package br.com.calorias.repository;

import br.com.calorias.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findByDataOrderByIdAsc(LocalDate data);

    @Query("select coalesce(sum(r.calorias), 0) from Registro r where r.data = :data")
    Double somaCalorias(@Param("data") LocalDate data);
}
