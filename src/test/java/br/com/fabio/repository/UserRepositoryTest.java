package br.com.fabio.repository;

import br.com.fabio.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para a interface UserRepository.
 * Utiliza @DataJpaTest para configurar um ambiente de teste focado na camada de persistência
 * com um banco de dados em memória (H2) e transações com rollback automático.
 */
@DataJpaTest
@DisplayName("Testes para UserRepository")
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager; // Utilitário para manipular entidades no teste

    @Autowired
    private UserRepository userRepository; // O repositório que queremos testar

    @Test
    @DisplayName("Deve encontrar um usuário pelo username quando ele existe")
    void findByUsername_whenUserExists_shouldReturnUser() {
        // Arrange: Criar e persistir um usuário de teste
        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        entityManager.persistAndFlush(testUser); // Salva no BD de teste

        // Act: Chamar o método do repositório
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        // Assert: Verificar se o usuário foi encontrado e se os dados estão corretos
        assertTrue(foundUser.isPresent(), "O Optional não deveria estar vazio.");
        assertEquals("testuser", foundUser.get().getUsername(), "O username encontrado deve ser o mesmo do usuário de teste.");
    }

    @Test
    @DisplayName("Deve retornar um Optional vazio ao buscar por um username que não existe")
    void findByUsername_whenUserDoesNotExist_shouldReturnEmptyOptional() {
        // Arrange: Não há necessidade de criar dados, pois o BD está limpo

        // Act: Chamar o método com um username inexistente
        Optional<User> foundUser = userRepository.findByUsername("nonexistentuser");

        // Assert: Verificar se o resultado é um Optional vazio
        assertFalse(foundUser.isPresent(), "O Optional deveria estar vazio para um usuário inexistente.");
    }

    @Test
    @DisplayName("Deve retornar true para existsByUsername quando o usuário existe")
    void existsByUsername_whenUserExists_shouldReturnTrue() {
        // Arrange
        User testUser = new User();
        testUser.setUsername("existinguser");
        testUser.setPassword("password123");
        entityManager.persistAndFlush(testUser);

        // Act
        boolean exists = userRepository.existsByUsername("existinguser");

        // Assert
        assertTrue(exists, "Deveria retornar true para um usuário existente.");
    }

    @Test
    @DisplayName("Deve retornar false para existsByUsername quando o usuário não existe")
    void existsByUsername_whenUserDoesNotExist_shouldReturnFalse() {
        // Arrange
        // Banco de dados limpo

        // Act
        boolean exists = userRepository.existsByUsername("nonexistentuser");

        // Assert
        assertFalse(exists, "Deveria retornar false para um usuário inexistente.");
    }
}