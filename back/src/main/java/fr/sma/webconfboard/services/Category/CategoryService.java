package fr.sma.webconfboard.services.Category;

import fr.sma.webconfboard.entities.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);

    List<Category> getAllCategories();

    Category save(Category categorie);

}
