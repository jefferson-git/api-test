package br.com.test.api.service.implement;

import br.com.test.api.config.ModelMapperConfig;
import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.model.Usuario;
import br.com.test.api.repository.UsuarioRepository;
import br.com.test.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NOME     = "jefferson";
    public static final String PASSWORD = "123";
    public static final String EMAIL    = "jefin@gmail";
    
    @Autowired
    private UsuarioServiceImpl service;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private ModelMapperConfig model;

    private Usuario usuario;
    private UsuarioDto dto;
    private Optional<Usuario> optional;

    @BeforeEach
    void setUp() {
        startUsuario();
    }

    @Test
    void whenfindByIdThenReturnAndUsuarioInstance() {
        when(repository.findById(anyInt())).thenReturn(optional);
        Usuario response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAndObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Usuário não encontrado com id:" + ID));

        try {
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Usuário não encontrado com id:" + ID, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThemReturnAnListOfUsuario() {
        when(repository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Usuario.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(EMAIL, response.get(0).getEmail());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(usuario);
        var response = service.create(dto);

        assertNotNull(response);
        assertEquals(Usuario.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void verificaEmail() {
    }

    private void startUsuario(){
        usuario = new Usuario(ID, NOME, PASSWORD, EMAIL);
        dto = new UsuarioDto(1, NOME, PASSWORD, EMAIL);
        optional = Optional.of(new Usuario(1, NOME, PASSWORD, EMAIL));
    }
}