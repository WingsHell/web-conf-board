package fr.sma.webconfboard.controllers;

import fr.sma.webconfboard.entities.Category;
import fr.sma.webconfboard.services.Category.CategoryServiceImpl;
import fr.sma.webconfboard.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoies")
@CrossOrigin(origins = "*") // http://localhost:4200
public class CategoryController {

    private CategoryServiceImpl categoryService;

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) { this.categoryService = categoryService; }

    // ------------------- Retrieve All Categories ---------------------------------------------

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("Fetching all Category");

        List<Category> categoriesList = new ArrayList<Category>();
        categoriesList = categoryService.getAllCategories();
        if(categoriesList.isEmpty()){
            logger.error("Unable to fetch an empty list");
            return new ResponseEntity(new CustomErrorType("Categories not found"), HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        //logger.info(clientList.toString());

        return new ResponseEntity<List<Category>>(categoriesList, HttpStatus.OK);
    }

    // ------------------- Retrieve Single Category ------------------------------------------

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Category> getCategoryById(@PathVariable final Long id) {
        logger.info("Fetching Category with id {}", id);
        Category category = categoryService.getCategoryById(id);

        if(category == null) {
            logger.error("Category with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Category with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
}
