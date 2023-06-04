package co.edu.unbosque.proyecto.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.model.Acciones;
import co.edu.unbosque.proyecto.model.Empresa;
import co.edu.unbosque.proyecto.model.Movimiento;
import co.edu.unbosque.proyecto.model.Tendencia;
import co.edu.unbosque.proyecto.repository.AccionesRepository;
import co.edu.unbosque.proyecto.repository.EmpresasRepository;
import co.edu.unbosque.proyecto.repository.MovimientoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MovimientoController {
	@Autowired
	private MovimientoRepository usrdao;
	@Autowired
	private EmpresasRepository emprdao;

	@PostMapping(path = "/tendencia")
	public ResponseEntity<Boolean> add(@RequestParam String nombreEmpresa, @RequestParam int valor) {
		Movimiento uc = new Movimiento();
		uc.setNombreEmpresa(nombreEmpresa);
		uc.setValor(valor);
		usrdao.save(uc);
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/movimientos")
	public ResponseEntity<List<Movimiento>> mostrarTodo() {
		List<Movimiento> lista = usrdao.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@GetMapping("/tendencia")
	public ResponseEntity<List<Tendencia>> tendencia() {
		List<Movimiento> lista = usrdao.findAll();
		List<Empresa> lista1 = emprdao.findAll();

		Map<String, List<Movimiento>> movimientosPorEmpresa = new HashMap<>();

		for (Movimiento movimiento : lista) {
			String nombreEmpresa = movimiento.getNombreEmpresa();


			List<Movimiento> movimientos = movimientosPorEmpresa.getOrDefault(nombreEmpresa, new ArrayList<>());

			
			movimientos.add(movimiento);

	
			movimientosPorEmpresa.put(nombreEmpresa, movimientos);
		}


		List<Tendencia> tendencias = new ArrayList<>();


		for (Empresa empresa : lista1) {
			String nombreEmpresa = empresa.getNombre();

	
			List<Movimiento> movimientos = movimientosPorEmpresa.getOrDefault(nombreEmpresa, new ArrayList<>());

		
			double promedio = 0.0;
			int cantidadMovimientos = movimientos.size();
			if (cantidadMovimientos > 0) {
				double suma = 0.0;
				for (Movimiento movimiento : movimientos) {
					suma += movimiento.getValor();
				}
				promedio = suma / cantidadMovimientos;
			}

	
			Tendencia tendencia = new Tendencia(nombreEmpresa, promedio, cantidadMovimientos);
			tendencias.add(tendencia);
		}

		ordenarPorPromedioDescendente(tendencias);

		if (lista.isEmpty() || tendencias.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tendencias);
	}

	public void ordenarPorPromedioDescendente(List<Tendencia> tendencias) {
		int n = tendencias.size();
		boolean intercambio;

		for (int i = 0; i < n - 1; i++) {
			intercambio = false;

			for (int j = 0; j < n - i - 1; j++) {

				if (tendencias.get(j).getPromedio() < tendencias.get(j + 1).getPromedio()) {

					Tendencia temp = tendencias.get(j);
					tendencias.set(j, tendencias.get(j + 1));
					tendencias.set(j + 1, temp);
					intercambio = true;
				}
			}

			if (!intercambio) {
				break;
			}
		}
	}
}
