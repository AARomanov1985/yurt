package org.romanov.yurt.post.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "posts")
public class PostsData {
    @JacksonXmlProperty(localName = "row")
    private List<PostData> posts;
}

