package co.edu.unbosque.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyecto.model.Empresa;


import java.util.List;
import java.util.Optional;

public interface EmpresasRepository extends CrudRepository<Empresa, Integer> {
	public Optional<Empresa> findById(Integer id);

	public List<Empresa> findAll();

}
