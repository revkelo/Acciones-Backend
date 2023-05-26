package co.edu.unbosque.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.proyecto.model.Chart;
import co.edu.unbosque.proyecto.repository.ChartRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/charts")
public class ChartController {
	 @Autowired
	private ChartRepository usrdao;
	
	 public ChartController(ChartRepository usrdao) {
	        this.usrdao = usrdao;
	    }

	    @GetMapping
	    public List<Chart> getAllCharts() {
	        return usrdao.findAll();
	    }

	    @PostMapping
	    public ResponseEntity<Chart> createChart(@RequestBody Chart chart) {
	        Chart createdChart = usrdao.save(chart);
	        return new ResponseEntity<>(createdChart, HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Chart> getChartById(@PathVariable Integer id) {
	        Optional<Chart> chart = usrdao.findById(id);
	        return chart.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }
	
	 
}
