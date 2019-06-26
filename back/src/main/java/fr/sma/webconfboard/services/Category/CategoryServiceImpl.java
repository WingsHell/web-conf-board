package fr.sma.webconfboard.services.Category;


import fr.sma.webconfboard.entities.Category;
import fr.sma.webconfboard.repository.Category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    @Override
    public Category getCategoryById(Long id) { return this.categoryRepository.getCategoryById(id); }

    @Override
    public List<Category> getAllCategories(){
        List<Category> categoriesList = new ArrayList<>();

        categoryRepository.findAll().forEach(e -> categoriesList.add(e));

        return categoriesList;
    }

    @Override
    public Category save(Category categorie) { return categoryRepository.save(categorie); }
}
