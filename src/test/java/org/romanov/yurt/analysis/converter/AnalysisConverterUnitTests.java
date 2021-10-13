package org.romanov.yurt.analysis.converter;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AnalysisConverterUnitTests {

    private final AnalysisConverter instance = new AnalysisConverter();

    @Test
    void shouldConvert() {
        // given
        var uid = 123L;
        var analyseDate = LocalDate.now();
        var state = State.FINISHED;
        var analyseTimeInSeconds = 3L;
        var firstPost = LocalDate.now().minusMonths(6);
        var lastPost = LocalDate.now().minusMonths(1);
        var totalPosts = 3334L;
        var totalAcceptedPosts = 1222L;
        var avgScore = BigDecimal.valueOf(2.34);

        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getUid()).willReturn(uid);
        given(analysisModel.getAnalyseDate()).willReturn(analyseDate);
        given(analysisModel.getState()).willReturn(state);
        given(analysisModel.getFailedSummary()).willReturn(null);
        given(analysisModel.getAnalyseTimeInSeconds()).willReturn(analyseTimeInSeconds);
        given(analysisModel.getFirstPost()).willReturn(firstPost);
        given(analysisModel.getLastPost()).willReturn(lastPost);
        given(analysisModel.getTotalPosts()).willReturn(totalPosts);
        given(analysisModel.getTotalAcceptedPosts()).willReturn(totalAcceptedPosts);
        given(analysisModel.getAvgScore()).willReturn(avgScore);

        // when
        var dto = instance.convert(analysisModel);

        // then
        assertEquals(uid, dto.getUid());
        assertEquals(analyseDate, dto.getAnalyseDate());
        assertEquals(state, dto.getState());
        assertEquals("", dto.getFailedSummary());
        assertEquals(analyseTimeInSeconds, dto.getAnalyseTimeInSeconds());
        assertEquals(firstPost, dto.getDetails().getFirstPost());
        assertEquals(lastPost, dto.getDetails().getLastPost());
        assertEquals(totalPosts, dto.getDetails().getTotalPosts());
        assertEquals(totalAcceptedPosts, dto.getDetails().getTotalAcceptedPosts());
        assertEquals(avgScore, dto.getDetails().getAvgScore());
    }

    @Test
    void shouldConvertWithFailedSummary() {
        // given
        var failedSummary = "fail";
        var state = State.FAILED;
        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getFailedSummary()).willReturn(failedSummary);
        given(analysisModel.getState()).willReturn(state);

        // when
        var dto = instance.convert(analysisModel);

        // then
        assertEquals(state, dto.getState());
        assertEquals(failedSummary, dto.getFailedSummary());
    }
}
