package service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import entity.Category;
import repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. GET all categories with pagination
    @Override
    public Page<Category> getAllCategories(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    // 2. POST - Create a new category
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // 3. GET - Get category by ID
    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // 4. PUT - Update category by ID
    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    // 5. DELETE - Delete category by ID
    @Override
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }
}
