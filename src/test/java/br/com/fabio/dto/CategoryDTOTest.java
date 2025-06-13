package br.com.fabio.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe CategoryDTO.
 * Foco em garantir a imutabilidade, o contrato de equals/hashCode e a corretude dos dados.
 */
@DisplayName("Testes para CategoryDTO")
class CategoryDTOTest {

    @Test
    @DisplayName("Deve criar DTO e retornar valores corretos com getters")
    void constructorAndGetters_shouldSetValuesCorrectly() {
        // Arrange
        String expectedName = "Eletrônicos";
        String expectedImageUrl = "https://example.com/electronics.png";

        // Act
        CategoryDTO dto = new CategoryDTO(expectedName, expectedImageUrl);

        // Assert
        assertNotNull(dto, "O DTO não deve ser nulo.");
        assertEquals(expectedName, dto.getName(), "O nome da categoria deve ser o mesmo fornecido no construtor.");
        assertEquals(expectedImageUrl, dto.getImageUrl(), "A URL da imagem deve ser a mesma fornecida no construtor.");
    }

    @Test
    @DisplayName("Deve ser igual a si mesmo (reflexividade)")
    void equals_shouldBeReflexive() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Livros", "https://example.com/books.png");

        // Act & Assert
        assertEquals(dto1, dto1, "Um objeto deve ser igual a si mesmo.");
    }

    @Test
    @DisplayName("Deve ser igual a outro objeto com os mesmos valores (simetria)")
    void equals_shouldBeSymmetric() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Livros", "https://example.com/books.png");
        CategoryDTO dto2 = new CategoryDTO("Livros", "https://example.com/books.png");

        // Act & Assert
        assertTrue(dto1.equals(dto2) && dto2.equals(dto1), "Dois objetos com os mesmos dados devem ser iguais em ambas as direções.");
    }

    @Test
    @DisplayName("Não deve ser igual a um objeto nulo")
    void equals_shouldReturnFalseForNull() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Livros", "https://example.com/books.png");

        // Act & Assert
        assertNotEquals(null, dto1, "O objeto não deve ser igual a nulo.");
    }

    @Test
    @DisplayName("Não deve ser igual a um objeto de tipo diferente")
    void equals_shouldReturnFalseForDifferentType() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Livros", "https://example.com/books.png");
        Object otherObject = new Object();

        // Act & Assert
        assertNotEquals(dto1, otherObject, "O objeto não deve ser igual a um objeto de classe diferente.");
    }

    @Test
    @DisplayName("Não deve ser igual a um DTO com nome diferente")
    void equals_shouldReturnFalseForDifferentName() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Livros", "https://example.com/books.png");
        CategoryDTO dto2 = new CategoryDTO("Música", "https://example.com/books.png");

        // Act & Assert
        assertNotEquals(dto1, dto2, "DTOs com nomes diferentes não devem ser iguais.");
    }

    @Test
    @DisplayName("Não deve ser igual a um DTO com imageUrl diferente")
    void equals_shouldReturnFalseForDifferentImageUrl() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Livros", "https://example.com/books.png");
        CategoryDTO dto2 = new CategoryDTO("Livros", "https://example.com/music.png");

        // Act & Assert
        assertNotEquals(dto1, dto2, "DTOs com imageUrls diferentes não devem ser iguais.");
    }

    @Test
    @DisplayName("Deve lidar corretamente com campos nulos no equals")
    void equals_shouldHandleNullFields() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO(null, null);
        CategoryDTO dto2 = new CategoryDTO(null, null);
        CategoryDTO dto3 = new CategoryDTO("Nome", null);
        CategoryDTO dto4 = new CategoryDTO(null, "url");

        // Assert
        assertEquals(dto1, dto2, "Dois DTOs com todos os campos nulos devem ser iguais.");
        assertNotEquals(dto1, dto3, "Um DTO nulo não deve ser igual a um DTO com nome preenchido.");
        assertNotEquals(dto3, dto4, "DTOs com campos nulos diferentes não devem ser iguais.");
    }

    @Test
    @DisplayName("Deve retornar o mesmo hashCode para objetos iguais")
    void hashCode_shouldBeConsistentForEqualObjects() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Roupas", "https://example.com/clothes.png");
        CategoryDTO dto2 = new CategoryDTO("Roupas", "https://example.com/clothes.png");

        // Act & Assert
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Objetos iguais devem ter o mesmo hashCode.");
    }

    @Test
    @DisplayName("Deve retornar hashCodes diferentes para objetos diferentes (geralmente)")
    void hashCode_shouldBeDifferentForUnequalObjects() {
        // Arrange
        CategoryDTO dto1 = new CategoryDTO("Roupas", "https://example.com/clothes.png");
        CategoryDTO dto2 = new CategoryDTO("Sapatos", "https://example.com/shoes.png");

        // Act & Assert
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Objetos diferentes geralmente devem ter hashCodes diferentes.");
    }

    @Test
    @DisplayName("Deve gerar uma representação em String correta")
    void toString_shouldReturnCorrectFormat() {
        // Arrange
        CategoryDTO dto = new CategoryDTO("Esportes", "https://example.com/sports.png");
        String expectedString = "CategoryDTO{name='Esportes', imageUrl='https://example.com/sports.png'}";

        // Act
        String actualString = dto.toString();

        // Assert
        assertEquals(expectedString, actualString, "O método toString() deve retornar a string no formato esperado.");
    }
}