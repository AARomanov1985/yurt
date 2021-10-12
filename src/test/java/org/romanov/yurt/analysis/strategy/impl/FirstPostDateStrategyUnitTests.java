package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class FirstPostDateStrategyUnitTests {

    private final FirstPostDateStrategy instance = new FirstPostDateStrategy();

    /**
     * Should set firstPostDate in AnalysisModel if firstPostDate is less than
     * AnalysisModel has
     */
    @Test
    void testUpdateFirstPostDateCase1() {
        // given
        var firstPostDate = LocalDate.now().minusDays(3);

        var postData = mock(PostData.class);
        given(postData.getCreationDate()).willReturn(firstPostDate);

        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getFirstPost()).willReturn(LocalDate.now());

        // when
        instance.updateAnalysis(postData, analysisModel);

        // then
        verify(analysisModel).setFirstPost(firstPostDate);
    }

    /**
     * Should not set firstPostDate in AnalysisModel if firstPostDate is not less than
     * AnalysisModel has
     */
    @Test
    void testUpdateFirstPostDateCase2() {
        // given
        var firstPostDateFromAnalysis = LocalDate.now().minusDays(7);
        var firstPostDate = LocalDate.now().minusDays(1);

        var postData = mock(PostData.class);
        given(postData.getCreationDate()).willReturn(firstPostDate);

        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getFirstPost()).willReturn(firstPostDateFromAnalysis);

        // when
        instance.updateAnalysis(postData, analysisModel);

        // then
        verify(analysisModel, never()).setFirstPost(firstPostDateFromAnalysis);
    }
}
