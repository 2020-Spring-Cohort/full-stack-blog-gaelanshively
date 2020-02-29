package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;

@Controller
public class CategoryController {
    private CategoryStorage categoryStorage;
    private BlogStorage blogStorage;

    public CategoryController(CategoryStorage categoryStorage, BlogStorage blogStorage){
        this.categoryStorage = categoryStorage;
        this.blogStorage = blogStorage;
    }

    @RequestMapping("/categories")
    public String displayCategories(Model model){
        model.addAttribute("categories", categoryStorage.findAllCategories());
        return "ListOfCategories";
    }

    @GetMapping("/categories/{categoryName}")
    public String displayChosenCategory(@PathVariable String categoryName, Model model){
        Category chosenCategory = categoryStorage.findCategoryByName(categoryName);
        model.addAttribute("categories", chosenCategory);
        return "category-view";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam String categoryName){
        categoryStorage.store(new Category(categoryName));
        return "redirect:/categories";
    }
}
