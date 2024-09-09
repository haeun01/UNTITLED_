package PTR.PTR.service;

import PTR.PTR.model.Category;
import PTR.PTR.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
}
