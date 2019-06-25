package fr.sma.webconfboard.repository.Category;

import fr.sma.webconfboard.entities.Category;

import java.util.ArrayList;
import java.util.List;

abstract class CategoryRepositoryImpl implements CategoryRepository {
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        List<Category> categoryList = new ArrayList<>();
        categoryRepository.findAll().forEach(e -> categoryList.add(e));
        for(Category c : categoryList) {
            if (c.getId() == id) return c;
        }
        return null;
    }
}
