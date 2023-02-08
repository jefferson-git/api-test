package br.com.test.api.controller.exception;

import br.com.test.api.service.exception.IntegrityConstraintViolationException;
import br.com.test.api.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Retorna um ResponseEntity<StandardError> ao não encontrar o objeto em busca")
    void objectNotFoundException() {
        var response = exceptionHandler.ObjectNotFoundException(
                new ObjectNotFoundException("Usuário não encontrado"),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Usuário não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    @DisplayName("Retorna um ResponseEntity<StandardError> ao não encontrar o objeto em busca")
    void objectNotFoundExceptionThrow() {
        var response = exceptionHandler.ObjectNotFoundException(
                new ObjectNotFoundException("Usuário não encontrado", new Exception()),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Usuário não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void methodArgumentTypeMismatchException() {
    }

    @Test
    void notFoundException() {
    }

    @Test
    void dataIntegrityViolationException() {
    }

    @Test
    void integrityConstraintViolationException() {
        var response = exceptionHandler.IntegrityConstraintViolationException(
                new IntegrityConstraintViolationException("Email já cadastrado!"),
                new MockHttpServletRequest()
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Email já cadastrado!", response.getBody().getError());
    }
}