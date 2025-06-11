package br.com.fabio.service;

import br.com.fabio.dto.CategoryDTO;
import br.com.fabio.model.Category;
import br.com.fabio.repository.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "categoriesCache", key = "#isFeature")
    public List<CategoryDTO> getFeaturedCategories(Boolean isFeature) {
        List<Category> list = repository.findByIsFeature(isFeature);

        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * @CacheEvict: Usado para invalidar (remover) entradas do cache.
     */
    @CacheEvict(value = "categoriesCache", allEntries = true)
    public Category saveOrUpdateCategory(Category category) {
        System.out.println("Salvando categoria e LIMPANDO o cache de categorias...");
        return repository.save(category);
    }

    /**
     * A anotação @CacheEvict também é usada aqui para garantir que, ao deletar
     * uma categoria, o cache que contém a lista de todas as categorias seja invalidado.
     */
    @CacheEvict(value = "categoriesCache", allEntries = true)
    public void deleteCategory(Integer id) {
        System.out.println("Deletando categoria e LIMPANDO o cache de categorias...");
        repository.deleteById(id);
    }

    protected CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getName(), category.getImageUrl());
    }
}
