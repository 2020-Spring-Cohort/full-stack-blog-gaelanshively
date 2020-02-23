package org.wcci.blog.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private Collection<Blog> blogs;

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public Category(){
    }

    public String getCategoryName(){
        return categoryName;
    }

    public Long getId(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(categoryName);
    }

    public Collection<Blog> getBlogs(){
        return blogs;
    }

    @Override
    public String toString(){
        return "Category ID " +
                id + ": " +
                categoryName + ".";
    }
}
