package co.edu.unbosque.proyecto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.proyecto.controller.MovimientoController;
import co.edu.unbosque.proyecto.model.Empresa;
import co.edu.unbosque.proyecto.model.Movimiento;
import co.edu.unbosque.proyecto.model.Tendencia;
import co.edu.unbosque.proyecto.repository.EmpresasRepository;
import co.edu.unbosque.proyecto.repository.MovimientoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;

class MovimientoControllerTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private EmpresasRepository empresasRepository;

    @InjectMocks
    private MovimientoController movimientoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        String nombreEmpresa = "Empresa 1";
        int valor = 500;

        ResponseEntity<Boolean> response = movimientoController.add(nombreEmpresa, valor);

        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(movimientoRepository).save(any(Movimiento.class));
    }

    @Test
    void testMostrarTodo() {
        List<Movimiento> listaMovimientos = new ArrayList<>();
        listaMovimientos.add(new Movimiento("Empresa 1", 500));
        listaMovimientos.add(new Movimiento("Empresa 2", 700));

        when(movimientoRepository.findAll()).thenReturn(listaMovimientos);

        ResponseEntity<List<Movimiento>> response = movimientoController.mostrarTodo();

        assertEquals(listaMovimientos, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(movimientoRepository).findAll();
    }

    @Test
    void testTendencia() {
        List<Movimiento> listaMovimientos = new ArrayList<>();
        listaMovimientos.add(new Movimiento("Empresa 1", 500));
        listaMovimientos.add(new Movimiento("Empresa 1", 600));
        listaMovimientos.add(new Movimiento("Empresa 2", 700));

        List<Empresa> listaEmpresas = new ArrayList<>();
        listaEmpresas.add(new Empresa("Empresa 1"));
        listaEmpresas.add(new Empresa("Empresa 2"));

        when(movimientoRepository.findAll()).thenReturn(listaMovimientos);
        when(empresasRepository.findAll()).thenReturn(listaEmpresas);

        ResponseEntity<List<Tendencia>> response = movimientoController.tendencia();

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(movimientoRepository).findAll();
        verify(empresasRepository).findAll();
    }

    @Test
    void testOrdenarPorPromedioDescendente() {
        List<Tendencia> tendencias = new ArrayList<>();
        tendencias.add(new Tendencia("Empresa 1", 50.0, 5));
        tendencias.add(new Tendencia("Empresa 2", 70.0, 3));
        tendencias.add(new Tendencia("Empresa 3", 60.0, 4));

        movimientoController.ordenarPorPromedioDescendente(tendencias);

        assertEquals(70.0, tendencias.get(0).getPromedio());
        assertEquals(60.0, tendencias.get(1).getPromedio());
        assertEquals(50.0, tendencias.get(2).getPromedio());
    }

}
