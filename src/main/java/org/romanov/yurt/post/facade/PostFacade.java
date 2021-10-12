package org.romanov.yurt.post.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.romanov.yurt.post.data.PostData;
import org.romanov.yurt.post.model.PostModel;

public interface PostFacade {
    boolean isPost(String xml);
    PostData getPostData(String xml) throws JsonProcessingException;
    PostModel savePostFromPostData(PostData postData);
}
