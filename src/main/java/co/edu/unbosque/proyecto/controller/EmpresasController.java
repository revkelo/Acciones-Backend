package co.edu.unbosque.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.model.Empresa;
import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.EmpresasRepository;
import co.edu.unbosque.proyecto.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmpresasController {
	@Autowired
	private EmpresasRepository usrdao;

	@PostMapping(path = "/grafica")
	public ResponseEntity<Boolean> add(@RequestParam String nombre, @RequestParam int precioAccion) {
		List<Empresa> lista = usrdao.findAll();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getNombre().equals(nombre)) {
				return ResponseEntity.ok(false);
			}
		}

		Empresa uc = new Empresa();
		uc.setNombre(nombre);
		uc.setPrecioAccion(precioAccion);
		usrdao.save(uc);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/grafica")
	public ResponseEntity<List<Empresa>> mostrarTodo() {
		List<Empresa> lista = usrdao.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@DeleteMapping("/grafica/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Optional<Empresa> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		usrdao.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

	@PutMapping("/grafica")
	public ResponseEntity<Boolean> update(@RequestParam String nombre, @RequestParam int precioAccion,
			@RequestParam Integer id) {

		Optional<Empresa> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.ok(false);
		}
		return op.map(usr -> {
			usr.setNombre(nombre);
			usr.setPrecioAccion(precioAccion);

			usrdao.save(usr);
			return ResponseEntity.ok(true);
		}).orElseGet(() -> {
			Empresa nuevo = new Empresa();
			nuevo.setId(id);
			nuevo.setNombre(nombre);
			nuevo.setPrecioAccion(precioAccion);
			usrdao.save(nuevo);
			return ResponseEntity.ok(true);
		});
	}

}
