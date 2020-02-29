package org.wcci.blog.Storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Tag;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByName(String name);
}
