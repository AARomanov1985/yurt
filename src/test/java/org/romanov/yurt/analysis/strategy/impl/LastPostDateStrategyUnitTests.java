package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class LastPostDateStrategyUnitTests {

    private final LastPostDateStrategy instance = new LastPostDateStrategy();

    /**
     * Should set lastPostDate in AnalysisModel if lastPostDate is later than
     * AnalysisModel has
     */
    @Test
    void testUpdateLastPostDateCase1() {
        // given
        var lastPostDateFromAnalysis = LocalDate.now().minusDays(30);
        var lastPostDate = LocalDate.now().minusDays(3);

        var postData = mock(PostData.class);
        given(postData.getCreationDate()).willReturn(lastPostDate);

        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getLastPost()).willReturn(lastPostDateFromAnalysis);

        // when
        instance.updateAnalysis(postData, analysisModel);

        // then
        verify(analysisModel).setLastPost(lastPostDate);
    }

    /**
     * Should not set lastPostDate in AnalysisModel if lastPostDate is not later than
     * AnalysisModel has
     */
    @Test
    void testUpdateLastPostDateCase2() {
        // given
        var lastPostDateFromAnalysis = LocalDate.now().minusDays(30);
        var lastPostDate = LocalDate.now().minusDays(33);

        var postData = mock(PostData.class);
        given(postData.getCreationDate()).willReturn(lastPostDate);

        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getLastPost()).willReturn(lastPostDateFromAnalysis);

        // when
        instance.updateAnalysis(postData, analysisModel);

        // then
        verify(analysisModel, never()).setLastPost(lastPostDateFromAnalysis);
    }

}
