package br.com.fabio.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a entidade User.
 * Foco em validar o comportamento dos getters e setters.
 */
@DisplayName("Testes para a Entidade User")
class UserTest {

    @Test
    @DisplayName("Deve definir e obter o username e password corretamente")
    void gettersAndSetters_shouldWorkCorrectly() {
        // Arrange
        User user = new User();
        String expectedUsername = "testuser";
        String expectedPassword = "secretPassword123";

        // Act
        user.setUsername(expectedUsername);
        user.setPassword(expectedPassword);

        // Assert
        assertNotNull(user, "A instância de User não deve ser nula.");

        assertEquals(expectedUsername, user.getUsername(), "O username retornado deve ser o mesmo que foi configurado.");
        assertEquals(expectedPassword, user.getPassword(), "O password retornado deve ser o mesmo que foi configurado.");
    }

    @Test
    @DisplayName("Deve permitir campos nulos, pois não há validação na entidade")
    void gettersAndSetters_shouldAllowNullValues() {
        // Arrange
        User user = new User();

        // Act
        user.setUsername(null);
        user.setPassword(null);

        // Assert
        assertNull(user.getUsername(), "O username deve ser nulo se configurado como nulo.");
        assertNull(user.getPassword(), "O password deve ser nulo se configurado como nulo.");
    }
}