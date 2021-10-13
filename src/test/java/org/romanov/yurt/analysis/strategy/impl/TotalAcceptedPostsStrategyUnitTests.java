package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TotalAcceptedPostsStrategyUnitTests {

    private final TotalAcceptedPostsStrategy instance = new TotalAcceptedPostsStrategy();

    @Test
    void shouldUpdateTotalAcceptedPosts() {
        // given
        var postData1 = mock(PostData.class);
        given(postData1.getAcceptedAnswerId()).willReturn(1L);
        var postData2 = mock(PostData.class);
        given(postData2.getAcceptedAnswerId()).willReturn(null);
        var analysisModel = new AnalysisModel();

        // when
        instance.updateAnalysis(postData1, analysisModel);
        instance.updateAnalysis(postData2, analysisModel);

        // then
        assertTrue(1 == analysisModel.getTotalAcceptedPosts());
    }
}
