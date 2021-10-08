package org.romanov.yurt.post.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.romanov.yurt.post.data.PostData;

public interface PostFacade {
    boolean isPost(String xml);

    PostData getPostData(String xml) throws JsonProcessingException;

    void savePostFromPostData(PostData postData);
}
