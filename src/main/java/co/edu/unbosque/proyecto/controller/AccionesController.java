package co.edu.unbosque.proyecto.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import co.edu.unbosque.proyecto.model.Empresa;
import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.AccionesRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AccionesController {
	@Autowired
	private AccionesRepository usrdao;

	@PostMapping(path = "/acciones")
	public ResponseEntity<Boolean> add(@RequestParam Integer idCliente, @RequestParam Integer acciones,
			@RequestParam String nombreEmpresa, @RequestParam Date fecha, @RequestParam String estado,
			@RequestParam int valor) {

		Acciones uc = new Acciones();
		uc.setIdCliente(idCliente);
		uc.setAcciones(acciones);
		uc.setNombreEmpresa(nombreEmpresa);
		uc.setFecha(fecha);
		uc.setEstado(estado);
		uc.setValor(valor);
		usrdao.save(uc);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/acciones")
	public ResponseEntity<List<Acciones>> mostrarTodo() {
		List<Acciones> lista = usrdao.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/historial")
	public ResponseEntity<List<Acciones>> mostrarHistorial(@RequestParam Integer idCliente) {

		List<Acciones> lista = usrdao.findAll();
		List<Acciones> especifico = new ArrayList<Acciones>();
		String id_cliente = idCliente + "";
		for (int i = 0; i < lista.size(); i++) {

			String idSql = lista.get(i).getIdCliente() + "";
			if (idSql.equals(id_cliente)) {

				especifico.add(lista.get(i));
			}
		}

		if (lista.isEmpty() || especifico.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(especifico);
	}

	@DeleteMapping("/acciones/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Optional<Acciones> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
		}
		usrdao.deleteById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
	}

	@PutMapping("/acciones/{id}")
	public ResponseEntity<Boolean> update(@RequestParam Integer idCliente, @RequestParam Integer acciones,
			@RequestParam String nombreEmpresa, @RequestParam Date fecha, @RequestParam String estado,
			@RequestParam int valor, @PathVariable Integer id) {

		Optional<Acciones> op = usrdao.findById(id);
		if (!op.isPresent()) {
			return ResponseEntity.ok(false);
		}
		return op.map(usr -> {
			usr.setIdCliente(idCliente);
			usr.setAcciones(acciones);
			usr.setNombreEmpresa(nombreEmpresa);
			usr.setFecha(fecha);
			usr.setEstado(estado);
			usr.setValor(valor);

			usrdao.save(usr);
			return ResponseEntity.ok(true);
		}).orElseGet(() -> {
			Acciones uc = new Acciones();
			uc.setIdCliente(idCliente);
			uc.setAcciones(acciones);
			uc.setNombreEmpresa(nombreEmpresa);
			uc.setFecha(fecha);
			uc.setEstado(estado);
			uc.setValor(valor);
			usrdao.save(uc);
			return ResponseEntity.ok(true);
		});
	}
}
