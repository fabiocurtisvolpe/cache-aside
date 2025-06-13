package br.com.fabio.repository;

import br.com.fabio.model.Category; // Certifique-se que o import do seu modelo Category está correto
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para a interface CategoryRepository.
 * Utiliza @DataJpaTest para configurar um ambiente de teste focado na camada de persistência
 * com um banco de dados em memória (H2) e transações com rollback automático.
 */
@DataJpaTest
@DisplayName("Testes para CategoryRepository")
class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    // Dados de teste que podem ser reutilizados
    private Category categoryFeature;
    private Category categoryNotFeature;

    @BeforeEach
    void setUp() {
        // Arrange: Criar e persistir dados de teste antes de cada teste
        // Isso garante um estado inicial consistente para os testes que precisam de dados.

        categoryFeature = new Category();
        categoryFeature.setName("Eletrônicos em Destaque");
        categoryFeature.setImageUrl("https://example.com/feature.png");
        categoryFeature.setIsFeature(true);

        categoryNotFeature = new Category();
        categoryNotFeature.setName("Livros Comuns");
        categoryNotFeature.setImageUrl("https://example.com/books.png");
        categoryNotFeature.setIsFeature(false);

        // Persistir no banco de dados de teste
        entityManager.persist(categoryFeature);
        entityManager.persist(categoryNotFeature);
        entityManager.flush(); // Garante que os dados estão no BD antes do teste rodar
    }

    @Test
    @DisplayName("Deve encontrar apenas categorias que são 'feature' (em destaque)")
    void findByIsFeature_whenSearchingForTrue_shouldReturnOnlyFeaturedCategories() {
        // Act: Executa a consulta para encontrar categorias em destaque
        List<Category> foundCategories = categoryRepository.findByIsFeature(true);

        // Assert: Verifica o resultado
        assertNotNull(foundCategories, "A lista de categorias não deve ser nula.");
        assertEquals(1, foundCategories.size(), "Deve ser encontrada exatamente uma categoria em destaque.");

        Category found = foundCategories.get(0);
        assertEquals("Eletrônicos em Destaque", found.getName());
        assertTrue(found.getIsFeature());
    }

    @Test
    @DisplayName("Deve encontrar apenas categorias que NÃO são 'feature' (em destaque)")
    void findByIsFeature_whenSearchingForFalse_shouldReturnOnlyNonFeaturedCategories() {
        // Act: Executa a consulta para encontrar categorias que não estão em destaque
        List<Category> foundCategories = categoryRepository.findByIsFeature(false);

        // Assert: Verifica o resultado
        assertNotNull(foundCategories, "A lista de categorias não deve ser nula.");
        assertEquals(1, foundCategories.size(), "Deve ser encontrada exatamente uma categoria que não está em destaque.");

        Category found = foundCategories.get(0);
        assertEquals("Livros Comuns", found.getName());
        assertFalse(found.getIsFeature());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia se nenhuma categoria corresponder ao critério 'isFeature'")
    void findByIsFeature_whenNoCategoriesMatch_shouldReturnEmptyList() {
        // Arrange: Primeiro, limpa os dados inseridos pelo @BeforeEach para este teste específico
        entityManager.remove(categoryFeature);
        entityManager.remove(categoryNotFeature);
        entityManager.flush();

        // Cria um novo cenário onde todas as categorias são 'feature'
        Category newFeatureCategory = new Category();
        newFeatureCategory.setName("Nova Categoria Destaque");
        newFeatureCategory.setIsFeature(true);
        entityManager.persistAndFlush(newFeatureCategory);

        // Act: Procura por categorias que NÃO são 'feature'
        List<Category> foundCategories = categoryRepository.findByIsFeature(false);

        // Assert: A lista deve estar vazia
        assertNotNull(foundCategories);
        assertTrue(foundCategories.isEmpty(), "A lista deveria estar vazia, pois não há categorias com isFeature=false.");
    }
}