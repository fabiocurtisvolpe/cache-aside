package br.com.fabio.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a entidade Category.
 * Foco em validar o comportamento dos getters e setters.
 */
@DisplayName("Testes para a Entidade Category")
class CategoryTest {

    @Test
    @DisplayName("Deve definir e obter todos os campos corretamente")
    void gettersAndSetters_shouldAssignAndReturnCorrectValues() {
        // Arrange
        Category category = new Category();
        Integer expectedId = 1;
        String expectedName = "Eletrônicos";
        String expectedImageUrl = "https://example.com/electronics.png";
        Boolean expectedIsFeature = true;

        // Act
        category.setId(expectedId);
        category.setName(expectedName);
        category.setImageUrl(expectedImageUrl);
        category.setIsFeature(expectedIsFeature);

        // Assert
        assertNotNull(category, "A instância de Category não deve ser nula.");

        assertEquals(expectedId, category.getId(), "O ID retornado deve ser o mesmo que foi configurado.");
        assertEquals(expectedName, category.getName(), "O nome retornado deve ser o mesmo que foi configurado.");
        assertEquals(expectedImageUrl, category.getImageUrl(), "A URL da imagem retornada deve ser a mesma que foi configurada.");
        assertEquals(expectedIsFeature, category.getIsFeature(), "O valor de 'isFeature' retornado deve ser o mesmo que foi configurado.");
    }

    @Test
    @DisplayName("Deve ter o valor de 'isFeature' como false quando definido")
    void isFeature_shouldHandleFalseValue() {
        // Arrange
        Category category = new Category();

        // Act
        category.setIsFeature(false);

        // Assert
        assertFalse(category.getIsFeature(), "O valor de 'isFeature' deve ser false quando configurado como false.");
    }
}