package co.edu.unbosque.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyecto.model.Graficas;


import java.util.List;
import java.util.Optional;

public interface GraficasRepository extends CrudRepository<Graficas, Integer> {
	public Optional<Graficas> findById(Integer id);

	public List<Graficas> findAll();

}
