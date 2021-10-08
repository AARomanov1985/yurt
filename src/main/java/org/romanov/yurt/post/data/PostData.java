package org.romanov.yurt.post.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.time.LocalDate;

@Data
@JacksonXmlRootElement(localName = "row")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostData {
    @JacksonXmlProperty(localName = "Id")
    private Long id;
    @JacksonXmlProperty(localName = "PostTypeId")
    private Integer postTypeId;
    @JacksonXmlProperty(localName = "AcceptedAnswerId")
    private Long acceptedAnswerId;
    @JacksonXmlProperty(localName = "CreationDate")
    private LocalDate creationDate;
    @JacksonXmlProperty(localName = "Score")
    private Long score;
    @JacksonXmlProperty(localName = "ViewCount")
    private Long viewCount;
    @JacksonXmlProperty(localName = "Body")
    private String body;
    @JacksonXmlProperty(localName = "OwnerUserId")
    private Long ownerUserId;
    @JacksonXmlProperty(localName = "LastActivityDate")
    private LocalDate lastActivityDate;
    @JacksonXmlProperty(localName = "Title")
    private String title;
    @JacksonXmlProperty(localName = "Tags")
    private String tags;
    @JacksonXmlProperty(localName = "AnswerCount")
    private Long answerCount;
    @JacksonXmlProperty(localName = "CommentCount")
    private Long commentCount;
    @JacksonXmlProperty(localName = "FavoriteCount")
    private Long favoriteCount;
    @JacksonXmlProperty(localName = "ContentLicense")
    private String contentLicense;

    private Long analysisUid;
}
