package controller;


import entity.Category;
import repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. GET all Categories with pagination
    @GetMapping
    public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    // 2. POST - Create a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // 3. GET - Get category by ID
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id);
    }

    // 4. PUT - Update category by ID
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    // 5. DELETE - Delete category by ID
    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "Category deleted successfully";
    }
}
