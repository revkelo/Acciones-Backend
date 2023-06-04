package co.edu.unbosque.proyecto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.proyecto.controller.EmpresasController;
import co.edu.unbosque.proyecto.model.Empresa;
import co.edu.unbosque.proyecto.repository.EmpresasRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

class EmpresasControllerTest {

    @Mock
    private EmpresasRepository empresasRepository;

    @InjectMocks
    private EmpresasController empresasController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        String nombre = "Empresa 1";
        int precioAccion = 100;

        List<Empresa> listaVacia = new ArrayList<>();
        List<Empresa> listaConElemento = new ArrayList<>();
        listaConElemento.add(new Empresa("Empresa 2", 200));

        when(empresasRepository.findAll()).thenReturn(listaVacia, listaConElemento);

        ResponseEntity<Boolean> response = empresasController.add(nombre, precioAccion);

        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        response = empresasController.add(nombre, precioAccion);

        assertFalse(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(empresasRepository, times(2)).findAll();
        verify(empresasRepository).save(any(Empresa.class));
    }

    @Test
    void testMostrarTodo() {
        List<Empresa> listaEmpresas = new ArrayList<>();
        listaEmpresas.add(new Empresa("Empresa 1", 100));
        listaEmpresas.add(new Empresa("Empresa 2", 200));

        when(empresasRepository.findAll()).thenReturn(listaEmpresas);

        ResponseEntity<List<Empresa>> response = empresasController.mostrarTodo();

        assertEquals(listaEmpresas, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(empresasRepository).findAll();
    }

    @Test
    void testDelete() {
        int empresaId = 1;
        Optional<Empresa> empresaOptional = Optional.of(new Empresa("Empresa 1", 100));

        when(empresasRepository.findById(empresaId)).thenReturn(empresaOptional);

        ResponseEntity<String> response = empresasController.delete(empresaId);

        assertEquals("Deleted", response.getBody());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        verify(empresasRepository).findById(empresaId);
        verify(empresasRepository).deleteById(empresaId);
    }

    @Test
    void testUpdate() {
        String nombre = "Empresa 1";
        int precioAccion = 100;
        int empresaId = 1;
        Optional<Empresa> empresaOptional = Optional.of(new Empresa("Empresa 2", 200));

        when(empresasRepository.findById(empresaId)).thenReturn(empresaOptional);

        ResponseEntity<Boolean> response = empresasController.update(nombre, precioAccion, empresaId);

        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(empresasRepository).findById(empresaId);
        verify(empresasRepository).save(any(Empresa.class));

        empresaOptional = Optional.empty();
        when(empresasRepository.findById(empresaId)).thenReturn(empresaOptional);

        response = empresasController.update(nombre, precioAccion, empresaId);

        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(empresasRepository, times(2)).findById(empresaId);
        verify(empresasRepository, times(2)).save(any(Empresa.class));
    }
}
