package org.romanov.yurt.post.facade.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.romanov.yurt.post.converter.ReversePostConverter;
import org.romanov.yurt.post.model.PostModel;
import org.romanov.yurt.post.service.PostService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class DefaultPostFacadeUnitTests {

    @InjectMocks
    private DefaultPostFacade instance;
    @Mock
    private PostService postService;
    @Mock
    private ReversePostConverter reversePostConverter;
    public static final String POST =
            "<row Id=\"1\" PostTypeId=\"1\" AcceptedAnswerId=\"5\" CreationDate=\"2015-07-14T18:39:27.757\" " +
                    "Score=\"4\" ViewCount=\"123\" Body=\"&lt;p&gt;The proposal for this site only mentions &quot;" +
                    "Arabic" +
                    ".&quot; Which Arabic?&lt;/p&gt;&#xA;&#xA;&lt;p&gt;There is a common type of Arabic called &quot;" +
                    "classical Arabic&quot; (fusha) which stretches back at least 1000 years back. It's still used " +
                    "today " +
                    "for most media (eg. newspapers, textbooks) and in universities.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;" +
                    "Beyond " +
                    "this, each country has its own dialect (or slang) version of Arabic. This includes both use of " +
                    "vocabulary, and also actual grammar (or ignoring the classical grammar).&lt;/p&gt;&#xA;&#xA;&lt;" +
                    "p&gt;While some of the sample questions on Area 51 talk about Arabic in a specific area, the " +
                    "question is really how we should plan and organize questions, or whether we should focus " +
                    "exclusively" +
                    " on classical Arabic.&lt;/p&gt;&#xA;\" OwnerUserId=\"20\" LastEditorUserId=\"20\" " +
                    "LastEditDate=\"2015-07-14T22:27:41.087\" LastActivityDate=\"2015-07-15T16:43:23.787\" " +
                    "Title=\"Which " +
                    "Arabic are we talking about?\" Tags=\"&lt;discussion&gt;\" AnswerCount=\"5\" CommentCount=\"4\"/>";

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldReturnTrueIfStringIsPost() {
        // given

        // when
        var result  = instance.isPost(POST);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStringIsNotPost() {
        // given
        var post = "<h1>Hello</h1>";

        // when
        var result  = instance.isPost(post);

        // then
        assertFalse(result);
    }

    @Test
    void getPostData() throws JsonProcessingException {
        // given
        var post = "<h1>Hello</h1>";

        // when
        var postData = instance.getPostData(POST);

        // then
        assertTrue(postData.getAnswerCount()==5L);
        assertTrue(postData.getViewCount()==123L);
        assertTrue(postData.getScore()==4L);
        assertTrue(postData.getAcceptedAnswerId()==5L);
        assertNull(postData.getFavoriteCount());

    }

    @Test
    void savePostFromPostData() throws JsonProcessingException {
        // given
        var postData = instance.getPostData(POST);
        var postModel = mock(PostModel.class);
        given(reversePostConverter.convert(postData)).willReturn(postModel);
        given(postService.savePost(postModel)).willReturn(postModel);

        // when
        var result = instance.savePostFromPostData(postData);

        // then
        verify(postService).savePost(postModel);
        assertEquals(postModel, result);
    }
}