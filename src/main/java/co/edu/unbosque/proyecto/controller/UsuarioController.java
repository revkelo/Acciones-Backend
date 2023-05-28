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

import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usrdao;

	@PostMapping(path = "/usuario")
	public ResponseEntity<String> add(@RequestParam String nombre, @RequestParam String email,
			@RequestParam String contrasena) {
		Usuario uc = new Usuario();
		uc.setNombre(nombre);
		uc.setEmail(email);
		uc.setContrasena(contrasena);
		usrdao.save(uc);
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED (CODE 201)\n");
	}

	@GetMapping("/usuario")
	public ResponseEntity<Iterable<Usuario>> getAll() {
		List<Usuario> all = (List<Usuario>) usrdao.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(all);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}


	
	@GetMapping("/login")
	public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String contrasena) {
	    List<Usuario> all = (List<Usuario>) usrdao.findAll();

	    Usuario foundUsuario = null;

	    if (all.get(0).getEmail().equals(email) && all.get(0).getContrasena().equals(contrasena)) {
			// admin
	        foundUsuario = all.get(0);
		}
	    
		for (int i = 1; i < all.size(); i++) {
	        if (all.get(i).getEmail().equals(email) && all.get(i).getContrasena().equals(contrasena)) {
	            foundUsuario = all.get(i);
	            break;
	        }
	    }

	    if (foundUsuario != null) {
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body(foundUsuario);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}


	@GetMapping("/usuarioExistentes")
	public ResponseEntity<String> getExists() {
		List<Usuario> all = (List<Usuario>) usrdao.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(all.size() + "Cantidad de marikas");
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getOne(@PathVariable Integer id) {
		Optional<Usuario> op = usrdao.findById(id);
		if (op.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(op.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Optional<Usuario> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		usrdao.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<String> update(@RequestBody Usuario nuevo, @PathVariable Integer id) {

		Optional<Usuario> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		return op.map(usr -> {
			usr.setNombre(nuevo.getNombre());
			usr.setEmail(nuevo.getEmail());
			usr.setContrasena(nuevo.getContrasena());

			usrdao.save(usr);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Data updated");
		}).orElseGet(() -> {
			nuevo.setId(id);
			usrdao.save(nuevo);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data created");
		});
	}

}
