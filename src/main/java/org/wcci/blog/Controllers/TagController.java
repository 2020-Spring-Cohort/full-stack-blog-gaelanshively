package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.blog.Models.Tag;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;
import org.wcci.blog.Storage.TagStorage;

@Controller
public class TagController {
    private TagStorage tagStorage;
    private CategoryStorage categoryStorage;
    private BlogStorage blogStorage;

    public TagController(TagStorage tagStorage, CategoryStorage categoryStorage, BlogStorage blogStorage){
        this.tagStorage = tagStorage;
        this.categoryStorage = categoryStorage;
        this.blogStorage = blogStorage;
    }

    @RequestMapping("/tags")
    public String displayTags(Model model){
        model.addAttribute("tags", tagStorage.findAllTags());
        return "ListOfTags";
    }

    @RequestMapping("/tags/{name}")
    public String displaySingleTag(@PathVariable String name, Model model){
        Tag chosenTag = tagStorage.findTagByName(name);
        model.addAttribute("tag", chosenTag);
        return "tag-view";
    }
}
