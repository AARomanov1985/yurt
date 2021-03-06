package org.romanov.yurt.analysis.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.romanov.yurt.analysis.dao.AnalysisDao;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.dao.PostDao;

import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class DefaultAnalysisServiceUnitTests {

    public static final long UID = 123L;
    @InjectMocks
    private DefaultAnalysisService instance;
    @Mock
    private AnalysisDao analysisDao;
    @Mock
    private PostDao postDao;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldCreateAnalysisModel() {
        // given

        // when
        instance.createAnalysisModel();

        // then
        verify(analysisDao).save(any(AnalysisModel.class));
    }

    @Test
    void shouldSaveAnalysisModel() {
        // given
        var analysisModel = mock(AnalysisModel.class);

        // when
        instance.saveAnalysisModel(analysisModel);

        // then
        verify(analysisDao).save(analysisModel);
    }

    @Test
    void shouldGetAnalysisModelForUid() {
        // given
        var uid = UID;
        var analysisModel = mock(AnalysisModel.class);
        given(analysisDao.findById(uid)).willReturn(Optional.of(analysisModel));

        // when
        var result = instance.getAnalysisModelForUid(uid);

        // then
        assertEquals(result, analysisModel);
    }

    @Test
    void shouldThrowExceptionIfAnalysisModelIsNotFound() {
        Assertions.assertThrows(NoResultException.class, () -> {
            // given
            var uid = UID;
            given(analysisDao.findById(uid)).willReturn(Optional.empty());

            // when
            instance.getAnalysisModelForUid(uid);

            // then
            fail();
        });
    }

    @Test
    void shouldDeleteByUid() {
        // given
        var uid = UID;
        var analysisModel = mock(AnalysisModel.class);
        given(analysisDao.findById(uid)).willReturn(Optional.of(analysisModel));
        List<Long> postsUid = Collections.singletonList(1L);
        given(analysisModel.getPosts()).willReturn(postsUid);

        // when
        instance.deleteAnalysis(uid);

        // then
        verify(analysisDao).delete(analysisModel);
        verify(postDao).deleteAllById(postsUid);
    }

    @Test
    void shouldGetAllAnalysis() {
        // given
        var analysisModel1 = mock(AnalysisModel.class);
        var analysisModel2 = mock(AnalysisModel.class);
        given(analysisDao.findAll()).willReturn(Arrays.asList(analysisModel1, analysisModel2));

        // when
        var result = instance.getAllAnalysis();

        // then
        assertEquals(result.get(0), analysisModel1);
        assertEquals(result.get(1), analysisModel2);
    }
}