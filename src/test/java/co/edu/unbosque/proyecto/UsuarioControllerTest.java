package co.edu.unbosque.proyecto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.unbosque.proyecto.controller.UsuarioController;
import co.edu.unbosque.proyecto.model.Usuario;
import co.edu.unbosque.proyecto.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        String nombre = "Usuario 1";
        String email = "usuario1@example.com";
        String contrasena = "password";

        List<Usuario> all = new ArrayList<>();
        all.add(new Usuario("Usuario 2", "usuario2@example.com", "password2"));

        when(usuarioRepository.findAll()).thenReturn(all);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        ResponseEntity<Usuario> response = usuarioController.add(nombre, email, contrasena);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(usuarioRepository).findAll();
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void testMostrarTodo() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("Usuario 1", "usuario1@example.com", "password1"));
        listaUsuarios.add(new Usuario("Usuario 2", "usuario2@example.com", "password2"));

        when(usuarioRepository.findAll()).thenReturn(listaUsuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.mostrarTodo();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(listaUsuarios, response.getBody());

        verify(usuarioRepository).findAll();
    }

    @Test
    void testLogin() {
        List<Usuario> all = new ArrayList<>();
        all.add(new Usuario("Usuario 1", "usuario1@example.com", "password1"));
        all.add(new Usuario("Usuario 2", "usuario2@example.com", "password2"));

        when(usuarioRepository.findAll()).thenReturn(all);

        ResponseEntity<Usuario> response = usuarioController.login("usuario1@example.com", "password1");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(usuarioRepository).findAll();
    }

    @Test
    void testInicio() {
        Usuario usuario = new Usuario("Usuario 1", "usuario1@example.com", "password1");

        usuarioController.iniciar.add(usuario);

        ResponseEntity<Usuario> response = usuarioController.inicio();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testCerrar() {
        RedirectView redirectView = usuarioController.cerrar();

        assertEquals("http://localhost:8080/Frontend/login.html", redirectView.getUrl());
        assertTrue(usuarioController.iniciar.isEmpty());
        assertEquals(0, usuarioController.variante);
    }

    @Test
    void testGetExists() {
        List<Usuario> all = new ArrayList<>();
        when(usuarioRepository.findAll()).thenReturn(all);

        ResponseEntity<String> response = usuarioController.getExists();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertNull(response.getBody());

        all.add(new Usuario("Usuario 1", "usuario1@example.com", "password1"));
        when(usuarioRepository.findAll()).thenReturn(all);

        response = usuarioController.getExists();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("1Cantidad de marikas", response.getBody());

        verify(usuarioRepository, times(2)).findAll();
    }

    @Test
    void testGetOne() {
        Optional<Usuario> optionalUsuario = Optional.of(new Usuario("Usuario 1", "usuario1@example.com", "password1"));

        when(usuarioRepository.findById(1)).thenReturn(optionalUsuario);

        ResponseEntity<Usuario> response = usuarioController.getOne(1);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(optionalUsuario.get(), response.getBody());

        optionalUsuario = Optional.empty();
        when(usuarioRepository.findById(2)).thenReturn(optionalUsuario);

        response = usuarioController.getOne(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(usuarioRepository, times(2)).findById(any(Integer.class));
    }

    @Test
    void testDelete() {
        Optional<Usuario> optionalUsuario = Optional.of(new Usuario("Usuario 1", "usuario1@example.com", "password1"));

        when(usuarioRepository.findById(1)).thenReturn(optionalUsuario);

        ResponseEntity<String> response = usuarioController.delete(1);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals("Deleted", response.getBody());

        optionalUsuario = Optional.empty();
        when(usuarioRepository.findById(2)).thenReturn(optionalUsuario);

        response = usuarioController.delete(2);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("", response.getBody());

        verify(usuarioRepository, times(2)).findById(any(Integer.class));
        verify(usuarioRepository).deleteById(1);
    }

    @Test
    void testUpdate() {
        String nombre = "Usuario 1";
        String email = "usuario1@example.com";
        String contrasena = "password1";
        Integer id = 1;

        Optional<Usuario> optionalUsuario = Optional.of(new Usuario("Usuario 1", "usuario1@example.com", "password1"));

        when(usuarioRepository.findById(id)).thenReturn(optionalUsuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        ResponseEntity<Boolean> response = usuarioController.update(nombre, email, contrasena, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).save(any(Usuario.class));

        optionalUsuario = Optional.empty();

        when(usuarioRepository.findById(id)).thenReturn(optionalUsuario);
        response = usuarioController.update(nombre, email, contrasena, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        verify(usuarioRepository, times(2)).findById(id);
        verify(usuarioRepository, times(2)).save(any(Usuario.class));
    }

}

