package org.romanov.yurt.post.converter;

import org.modelmapper.ModelMapper;
import org.romanov.yurt.post.data.PostData;
import org.romanov.yurt.post.model.PostModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReversePostConverter implements Converter<PostData, PostModel> {

    @Resource
    private ModelMapper modelMapper;

    public PostModel convert(final PostData postData) {
        return modelMapper.map(postData, PostModel.class);
    }
}
