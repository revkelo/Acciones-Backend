package co.edu.unbosque.proyecto.repository;

import org.springframework.data.repository.CrudRepository;


import co.edu.unbosque.proyecto.model.Acciones;
import co.edu.unbosque.proyecto.model.Movimiento;

import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {

	public Optional<Movimiento> findById(Integer id);


	public List<Movimiento> findAll();

}
