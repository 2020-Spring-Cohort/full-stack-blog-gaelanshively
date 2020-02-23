package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;

@Component
public class Populator implements CommandLineRunner {

    private final CategoryStorage categoryStorage;
    private final BlogStorage blogStorage;

    public Populator(CategoryStorage categoryStorage, BlogStorage blogStorage){
        this.categoryStorage = categoryStorage;
        this.blogStorage = blogStorage;
    }

    @Override
    public void run(String... args){
        Category tech = new Category("Technology");
        categoryStorage.store(tech);
        Category games = new Category("Games");
        categoryStorage.store(games);
        Category film = new Category("Film");
        categoryStorage.store(film);
        Category books = new Category("Books");
        categoryStorage.store(books);
        Blog techBlog = new Blog("HP Probook a winner", "I was pleasantly surprised when I took my new laptop for a spin.  A must-buy for sure!",
                "HP4Days", tech);
        blogStorage.store(techBlog);
        Blog gamesBlog = new Blog("Still Waiting", "I really wish Games Workshop would release new Necron models soon!  Been a long time.  Not impressed.",
                "Nemesor Eversor", games);
        blogStorage.store(gamesBlog);
        Blog filmBlog = new Blog("Old but Good", "1992's 'Bram Stoker's Dracula' has aged badly in some ways, but overall is still very enjoyable.  Check it out!",
                "NotAVampire", film);
        blogStorage.store(filmBlog);
        Blog booksBlog = new Blog("Still the best", "Roger Zelazny has been dead for 25 years, but it's still tough to find an author as enjoyable.  Lord of Light and Donnerjack remain among the best science fiction ever written.",
                "CorwinOfAmber", books);
        blogStorage.store(booksBlog);
    }

}
