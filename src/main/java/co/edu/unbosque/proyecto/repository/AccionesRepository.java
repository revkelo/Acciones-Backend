package co.edu.unbosque.proyecto.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.proyecto.model.Acciones;

import java.util.List;
import java.util.Optional;

public interface AccionesRepository extends CrudRepository<Acciones, Integer> {



public Optional<Acciones> findById(Integer id);


public List<Acciones> findAll();

//public void deleteByAccionesCompradas(Integer acciones_compradas);
public List<Acciones> findByNombreEmpresa(String nombre_empresa);
}
