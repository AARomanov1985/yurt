package org.romanov.yurt.analysis.converter.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DefaultAnalysisConverterUnitTests {

    private DefaultAnalysisConverter instance = new DefaultAnalysisConverter();

    @Test
    void shouldConvert() {
        // given
        Long uid = 123L;
        LocalDate analyseDate = LocalDate.now();
        State state = State.FINISHED;
        String failedSummary = null;
        Long analyseTimeInSeconds = 3L;
        LocalDate firstPost = LocalDate.now().minusMonths(6);
        LocalDate lastPost = LocalDate.now().minusMonths(1);
        Long totalPosts = 3334L;
        Long totalAcceptedPosts = 1222L;
        BigDecimal avgScore = BigDecimal.valueOf(2.34);

        var analysisModel = mock(AnalysisModel.class);
        given(analysisModel.getUid()).willReturn(uid);
        given(analysisModel.getAnalyseDate()).willReturn(analyseDate);
        given(analysisModel.getState()).willReturn(state);
        given(analysisModel.getFailedSummary()).willReturn(failedSummary);
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
}
