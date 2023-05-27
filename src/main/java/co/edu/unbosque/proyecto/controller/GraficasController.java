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

import co.edu.unbosque.proyecto.model.Graficas;
import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.GraficasRepository;
import co.edu.unbosque.proyecto.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class GraficasController {
	@Autowired
	private GraficasRepository usrdao;

	@PostMapping(path = "/grafica")
	public ResponseEntity<String> add(@RequestParam String nombre) {
		Graficas uc = new Graficas();
		uc.setNombre(nombre);
		
		usrdao.save(uc);
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED (CODE 201)\n");
	}

	@GetMapping("/grafica")
	public ResponseEntity<List<Graficas>> mostrarTodo() {
		List<Graficas> lista = usrdao.findAll();
		System.out.println("VIDA HPTA");
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}



}
