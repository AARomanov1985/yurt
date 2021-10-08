package org.romanov.yurt.post.service.impl;

import org.romanov.yurt.post.dao.PostDao;
import org.romanov.yurt.post.model.PostModel;
import org.romanov.yurt.post.service.PostService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultPostService implements PostService {

    @Resource
    private PostDao postDao;

    @Override
    public void savePost(final PostModel postModel) {
        postDao.save(postModel);
    }
}
