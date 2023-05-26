package co.edu.unbosque.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyecto.model.Chart;


public interface ChartRepository extends CrudRepository<Chart, Integer> {
	public Optional<Chart> findById(Integer id);

	public List<Chart> findAll();
}
