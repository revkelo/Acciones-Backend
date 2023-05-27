package co.edu.unbosque.proyecto.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
import org.springframework.web.servlet.view.RedirectView;

import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@ServletComponentScan
public class UsuarioController {
	@Autowired
	private UsuarioRepository usrdao;
	private ArrayList<Usuario> list = new ArrayList<Usuario>();



	@PostMapping(path = "/usuario")
	public RedirectView add(@RequestParam String nombre, @RequestParam String email, @RequestParam String contrasena) {
		List<Usuario> all = (List<Usuario>) usrdao.findAll();
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getEmail().equals(email) || all.get(i).getNombre().equals(nombre)) {

				return new RedirectView("/error.html");

			}
		}

		Usuario uc = new Usuario();

		uc.setNombre(nombre);
		uc.setEmail(email);
		uc.setContrasena(contrasena);

		usrdao.save(uc);
		list.add(uc);

		System.out.println(list.get(0).getNombre());

		return new RedirectView("/acciones.html");
	}

	@GetMapping("/cerrar")
	public RedirectView cerrar() {

		list.remove(0);

		return new RedirectView("/index.html");
	}

	@GetMapping("/lista")
	public void subir() {
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId());

			System.out.println(list.get(i).getNombre());
			System.out.println(list.get(i).getEmail());

			System.out.println(list.get(i).getContrasena());

			
		}
		System.out.println("xd");

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
	public RedirectView login(@RequestParam String email, @RequestParam String contrasena) {
		List<Usuario> all = (List<Usuario>) usrdao.findAll();

		for (int i = 1; i < all.size(); i++) {
			if (all.get(0).getEmail().equals(email) && all.get(0).getNombre().equals(contrasena)) {

			

				return new RedirectView("/admin.html");

			}

			else if (all.get(i).getEmail().equals(email) && all.get(i).getNombre().equals(contrasena)) {

				
				Usuario uc = new Usuario();
				uc.setId(all.get(i).getId());
				uc.setNombre(all.get(i).getNombre());
				uc.setEmail(all.get(i).getEmail());
				uc.setContrasena(all.get(i).getContrasena());
				list.add(uc);
				System.out.println(list.get(0).getNombre());
	
				return new RedirectView("/acciones.html");

			}
		}

		return new RedirectView("/error.html");
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

	/**
	 * @return the usrdao
	 */
	public UsuarioRepository getUsrdao() {
		return usrdao;
	}

	/**
	 * @param usrdao the usrdao to set
	 */
	public void setUsrdao(UsuarioRepository usrdao) {
		this.usrdao = usrdao;
	}

	/**
	 * @return the list
	 */
	public ArrayList<Usuario> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<Usuario> list) {
		this.list = list;
	}

}
