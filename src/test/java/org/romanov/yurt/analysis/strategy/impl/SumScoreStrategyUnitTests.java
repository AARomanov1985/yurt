package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SumScoreStrategyUnitTests {

    private final SumScoreStrategy instance = new SumScoreStrategy();

    @Test
    void test() {
        // given
        var analysisModel = new AnalysisModel();
        var post1 = mock(PostData.class);
        var post2 = mock(PostData.class);
        given(post1.getScore()).willReturn(10L);
        given(post2.getScore()).willReturn(20L);

        // when
        instance.updateAnalysis(post1, analysisModel);
        instance.updateAnalysis(post2, analysisModel);

        // then
        assertEquals(BigDecimal.valueOf(30L), analysisModel.getSumScore());
    }
}
