package org.romanov.yurt.analysis.facade.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.romanov.yurt.analysis.strategy.CommonAnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.romanov.yurt.post.facade.PostFacade;
import org.romanov.yurt.post.model.PostModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class DefaultFileAnalyzerFacadeUnitTests {

    public static final String TEST_DATA = "example-data/3dprinting-posts.xml";
    @InjectMocks
    private DefaultFileAnalyzerFacade instance;
    @Mock
    private AnalysisService analysisService;
    @Mock
    private CommonAnalysisStrategy analyzerStrategy;
    @Mock
    private PostFacade postFacade;
    public static final long UID = 123L;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldAnalyzeAndSavePost() {
        // given
        var postData = mock(PostData.class);
        var analysisModel = mock(AnalysisModel.class);
        var postModel = mock(PostModel.class);
        given(postModel.getUid()).willReturn(UID);
        given(postFacade.savePostFromPostData(postData)).willReturn(postModel);

        // when
        long result = instance.analyzeAndSavePost(postData, analysisModel);

        // then
        verify(analyzerStrategy).updateAnalysis(postData, analysisModel);
        assertTrue(result == UID);
    }

    @Test
    void finishAnalysis() throws FileNotFoundException {
        // given
        var scanner = new Scanner(new File(TEST_DATA));
        var analysisModel = mock(AnalysisModel.class);
        var uids = new ArrayList<>(Arrays.asList(UID));

        // when
        instance.finishAnalysis(scanner, analysisModel, uids);

        // then
        verify(analysisModel).setPosts(uids);
        verify(analysisModel).setState(State.FINISHED);
        verify(analyzerStrategy).calculateAvgScore(analysisModel);
        verify(analyzerStrategy).calculateAnalyseTime(analysisModel);
        verify(analysisService).saveAnalysisModel(analysisModel);
    }
}