package br.com.fabio.controller;

import br.com.fabio.dto.CategoryDTO;
import br.com.fabio.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private")
public class PrivateController {

    private final CategoryService service;

    public PrivateController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public String getMessage() {
        return "Está no acesso privado";
    }

    @GetMapping("/list-category/{isFeature}")
    public ResponseEntity<List<CategoryDTO>> listCategory(@PathVariable("isFeature") Boolean isFeature) {
        return ResponseEntity.ok(this.service.getFeaturedCategories(isFeature));
    }
}
