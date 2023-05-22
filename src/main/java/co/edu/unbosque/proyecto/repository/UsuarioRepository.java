package co.edu.unbosque.proyecto.repository;

import org.springframework.data.repository.CrudRepository;


import co.edu.unbosque.proyecto.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
	public Optional<Usuario> findById(Integer id);

	public List<Usuario> findAll();

}
