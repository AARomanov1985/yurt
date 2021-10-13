package org.romanov.yurt.post.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.romanov.yurt.post.dao.PostDao;
import org.romanov.yurt.post.model.PostModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class DefaultPostServiceUnitTests {

    @InjectMocks
    private DefaultPostService instance;
    @Mock
    private PostDao postDao;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void savePost() {
        // given
        var postModel = mock(PostModel.class);

        // when
        instance.savePost(postModel);

        // then
        verify(postDao).save(postModel);
    }
}