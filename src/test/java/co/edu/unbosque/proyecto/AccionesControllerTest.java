package co.edu.unbosque.proyecto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.edu.unbosque.proyecto.controller.AccionesController;
import co.edu.unbosque.proyecto.model.Acciones;
import co.edu.unbosque.proyecto.repository.AccionesRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

class AccionesControllerTest {

    @Mock
    private AccionesRepository accionesRepository;

    @InjectMocks
    private AccionesController accionesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        int idCliente = 1;
        int acciones = 100;
        String nombreEmpresa = "Empresa 1";
        Date fecha = new Date(System.currentTimeMillis());
        String estado = "Activo";
        int valor = 500;

        ResponseEntity<Boolean> response = accionesController.add(idCliente, acciones, nombreEmpresa, fecha, estado, valor);

        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(accionesRepository).save(any(Acciones.class));
    }

    @Test
    void testMostrarTodo() {
        List<Acciones> listaAcciones = new ArrayList<>();
        listaAcciones.add(new Acciones(1, 100, null, "Empresa 1", new Date(System.currentTimeMillis()), "Activo", 500));
        listaAcciones.add(new Acciones(2, 200, null, "Empresa 2", new Date(System.currentTimeMillis()), "Inactivo", 700));

        when(accionesRepository.findAll()).thenReturn(listaAcciones);

        ResponseEntity<List<Acciones>> response = accionesController.mostrarTodo();

        assertEquals(listaAcciones, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(accionesRepository).findAll();
    }

    @Test
    void testMostrarHistorial() {
        int idCliente = 1;
        List<Acciones> listaAcciones = new ArrayList<>();
        listaAcciones.add(new Acciones(1, 100, idCliente, "Empresa 1", new Date(System.currentTimeMillis()), "Activo", 500));
        listaAcciones.add(new Acciones(1, 200, idCliente, "Empresa 2", new Date(System.currentTimeMillis()), "Inactivo", 700));

        when(accionesRepository.findAll()).thenReturn(listaAcciones);

        ResponseEntity<List<Acciones>> response = accionesController.mostrarHistorial(idCliente);

        assertEquals(listaAcciones, response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        verify(accionesRepository).findAll();
    }

    @Test
    void testDelete() {
        int accionesId = 1;
        Optional<Acciones> accionesOptional = Optional.of(new Acciones(1, 100, accionesId, "Empresa 1", new Date(System.currentTimeMillis()), "Activo", 500));

        when(accionesRepository.findById(accionesId)).thenReturn(accionesOptional);

        ResponseEntity<String> response = accionesController.delete(accionesId);

        assertEquals("Deleted", response.getBody());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        verify(accionesRepository).findById(accionesId);
        verify(accionesRepository).deleteById(accionesId);
    }

    @Test
    void testUpdate() {
        int idCliente = 1;
        int acciones = 100;
        String nombreEmpresa = "Empresa 1";
        Date fecha = new Date(System.currentTimeMillis());
        String estado = "Activo";
        int valor = 500;
        int accionesId = 1;
        Optional<Acciones> accionesOptional = Optional.of(new Acciones(accionesId, 200, accionesId, "Empresa 2", new Date(System.currentTimeMillis()), "Inactivo", 700));

        when(accionesRepository.findById(accionesId)).thenReturn(accionesOptional);

        ResponseEntity<Boolean> response = accionesController.update(idCliente, acciones, nombreEmpresa, fecha, estado, valor, accionesId);

        assertTrue(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(accionesRepository).findById(accionesId);
        verify(accionesRepository).save(any(Acciones.class));
    }

}
