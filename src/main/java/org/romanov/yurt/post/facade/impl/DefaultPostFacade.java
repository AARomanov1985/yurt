package org.romanov.yurt.post.facade.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.romanov.yurt.post.converter.ReversePostConverter;
import org.romanov.yurt.post.data.PostData;
import org.romanov.yurt.post.facade.PostFacade;
import org.romanov.yurt.post.model.PostModel;
import org.romanov.yurt.post.service.PostService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultPostFacade implements PostFacade {

    public static final String POST_STRING = "<row Id=";
    private final ObjectMapper objectMapper;

    @Resource
    private PostService postService;
    @Resource
    private ReversePostConverter reversePostConverter;

    public DefaultPostFacade() {
        var xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        objectMapper = new XmlMapper(xmlModule);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public boolean isPost(String xml) {
        return xml.contains(POST_STRING);
    }

    @Override
    public PostData getPostData(String xml) throws JsonProcessingException {
        return objectMapper.readValue(xml, PostData.class);
    }

    @Override
    public PostModel savePostFromPostData(final PostData postData) {
        var postModel = reversePostConverter.convert(postData);
        return postService.savePost(postModel);
    }
}
