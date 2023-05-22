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

import co.edu.unbosque.proyecto.model.Acciones;
import co.edu.unbosque.proyecto.repository.AccionesRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AccionesController {
	@Autowired
	private AccionesRepository usrdao;

	@PostMapping(path = "/acciones")
	public ResponseEntity<String> add(@RequestParam Integer id_cliente, Integer acciones_compradas, String nombre_empresa) {
		Acciones uc = new Acciones();
		uc.setId_cliente(id_cliente);
		uc.setAcciones_compradas(acciones_compradas);
		uc.setNombre_empresa(nombre_empresa);
		usrdao.save(uc);
		return ResponseEntity.status(HttpStatus.CREATED).body("CREATED (CODE 201)\n");
	}

	@GetMapping("/acciones")
	public ResponseEntity<Iterable<Acciones>> getAll() {
		List<Acciones> all = (List<Acciones>) usrdao.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(all);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}


	
	@GetMapping("/acciones/{id}")
	public ResponseEntity<Acciones> getOne(@PathVariable Integer id) {
		Optional<Acciones> op = usrdao.findById(id);
		if (op.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(op.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	




}
