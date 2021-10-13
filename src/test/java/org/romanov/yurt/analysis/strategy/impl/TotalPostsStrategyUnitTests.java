package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class TotalPostsStrategyUnitTests {

    private final TotalPostsStrategy instance = new TotalPostsStrategy();

    @Test
    void shouldUpdateTotalPosts() {
        // given
        var analysisModel = new AnalysisModel();

        // when
        instance.updateAnalysis(mock(PostData.class), analysisModel);
        instance.updateAnalysis(mock(PostData.class), analysisModel);
        instance.updateAnalysis(mock(PostData.class), analysisModel);

        // then
        assertEquals(3L, analysisModel.getTotalPosts());
    }
}