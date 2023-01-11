package br.com.test.api.service.implement;

import br.com.test.api.config.ModelMapperConfig;
import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.model.Usuario;
import br.com.test.api.repository.UsuarioRepository;
import br.com.test.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NOME     = "jefferson";
    public static final String PASSWORD = "123";
    public static final String EMAIL    = "jefin@gmail";
    
    @InjectMocks
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
    void whenFindByIdThenReturnAndObjectNotFoundExceprion(){
        when(repository.findById(anyInt())).thenThrow( new ObjectNotFoundException("Usuário não encontrado com id:" + ID));

        try {
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Usuário não encontrado com id:" + ID, ex.getMessage());
        }
    }
    @Test
    void findAll() {
    }

    @Test
    void create() {
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