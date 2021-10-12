package org.romanov.yurt.analysis.facade.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.romanov.yurt.analysis.converter.AnalysisConverter;
import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.service.AnalysisService;

import javax.persistence.NoResultException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class DefaultAnalysisFacadeUnitTests {

    @InjectMocks
    private DefaultAnalysisFacade instance;

    @Mock
    private AnalysisService analysisService;
    @Mock
    private AnalysisConverter analysisConverter;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldGetAnalysisDtoForUid() {
        // given
        long uid = 123;
        var analysisModel = mock(AnalysisModel.class);
        given(analysisService.getAnalysisModelForUid(uid)).willReturn(analysisModel);
        var analysisDto = mock(AnalysisDto.class);
        given(analysisConverter.convert(analysisModel)).willReturn(analysisDto);

        // when
        var result = instance.getAnalysisDtoForUid(uid);

        // then
        assertEquals(analysisDto, result);
    }

    @Test
    void shouldGetAllAnalysis() {
        // given
        var model1 = mock(AnalysisModel.class);
        var model2 = mock(AnalysisModel.class);
        given(analysisService.getAllAnalysis()).willReturn(Arrays.asList(model1, model2));
        var dto1 = mock(AnalysisDto.class);
        var dto2 = mock(AnalysisDto.class);
        given(analysisConverter.convert(model1)).willReturn(dto1);
        given(analysisConverter.convert(model2)).willReturn(dto2);

        // when
        var result = instance.getAllAnalysis();

        // then
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    void shouldDeleteAnalysis() {
        // given
        long uid = 123;

        // when
        var result = instance.deleteAnalysis(uid);

        // then
        verify(analysisService).deleteAnalysis(uid);
        assertEquals(result.getMessage(), "success");
    }

    @Test
    void shouldReturnErrorMessageIfUnableToDeleteAnalysis() {
        // given
        long uid = 123;
        doThrow(NoResultException.class)
                .when(analysisService).deleteAnalysis(uid);

        // when
        var result = instance.deleteAnalysis(uid);

        // then
        assertTrue(result.getMessage().startsWith("failed"));
    }
}