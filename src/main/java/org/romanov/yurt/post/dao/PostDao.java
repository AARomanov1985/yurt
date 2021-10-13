package org.romanov.yurt.post.dao;

import org.romanov.yurt.post.model.PostModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends CrudRepository<PostModel, Long> {
}
