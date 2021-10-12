package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
class DefaultCommonAnalysisStrategyIntegrationTests {

    @Resource
    private DefaultCommonAnalysisStrategy instance;

    @Test
    void shouldUpdateAnalysisFromTwoPosts() {
        // given
        var postData1 = mock(PostData.class);
        given(postData1.getAcceptedAnswerId()).willReturn(3234323L);
        var postCreationDate1 = LocalDate.now().minusDays(30);
        given(postData1.getCreationDate()).willReturn(postCreationDate1);
        var score1 = 10L;
        given(postData1.getScore()).willReturn(score1);
        var viewCount1 = 200L;
        given(postData1.getViewCount()).willReturn(viewCount1);
        var lastActivityDate1 = LocalDate.now().minusDays(10);
        given(postData1.getLastActivityDate()).willReturn(lastActivityDate1);
        var answerCount1 = 50L;
        given(postData1.getAnswerCount()).willReturn(answerCount1);
        var commentCount1 = 40L;
        given(postData1.getCommentCount()).willReturn(commentCount1);
        var favoriteCount1 = 30L;
        given(postData1.getFavoriteCount()).willReturn(favoriteCount1);

        var postData2 = mock(PostData.class);
        // not accepted post
        given(postData2.getAcceptedAnswerId()).willReturn(null);
        var postCreationDate2 = LocalDate.now().minusDays(50);
        given(postData2.getCreationDate()).willReturn(postCreationDate2);
        var score2 = 25L;
        given(postData2.getScore()).willReturn(score2);
        var viewCount2 = 300L;
        given(postData2.getViewCount()).willReturn(viewCount2);
        var lastActivityDate2 = LocalDate.now().minusDays(7);
        given(postData2.getLastActivityDate()).willReturn(lastActivityDate2);
        var answerCount2 = 5L;
        given(postData2.getAnswerCount()).willReturn(answerCount2);
        var commentCount2 = 4L;
        given(postData2.getCommentCount()).willReturn(commentCount2);
        var favoriteCount2 = 3L;
        given(postData2.getFavoriteCount()).willReturn(favoriteCount2);

        var analysisModel = new AnalysisModel();

        // when
        instance.updateAnalysis(postData1, analysisModel);
        instance.updateAnalysis(postData2, analysisModel);
        instance.calculateAvgScore(analysisModel);

        // then
        assertEquals(analysisModel.getFirstPost(), postCreationDate2);
        assertEquals(analysisModel.getSumScore(), BigDecimal.valueOf(35L));
        assertEquals(analysisModel.getLastPost(), postCreationDate1);
        assertEquals(analysisModel.getTotalPosts(), 2L);
        assertEquals(analysisModel.getTotalAcceptedPosts(), 1L);
        assertEquals(analysisModel.getAvgScore(), BigDecimal.valueOf(1750,2));
    }

}