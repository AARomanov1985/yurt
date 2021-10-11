package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultAnalysisStrategyUnitTests {

    private final DefaultAnalysisStrategy instance = new DefaultAnalysisStrategy();

    /**
     * Should set firstPostDate in AnalysisModel if firstPostDate is less than
     * AnalysisModel has
     */
    @Test
    public void testAnalyzeFirstPostCase1() {
        // given
        var firstPostDate = LocalDate.now().minusDays(3);

        var postData = new PostData();
        postData.setCreationDate(firstPostDate);

        var analysisModel = new AnalysisModel();
        analysisModel.setFirstPost(LocalDate.now());

        // when
        instance.analyzeFirstPost(postData, analysisModel);

        // then
        assertEquals(analysisModel.getFirstPost(), firstPostDate);
    }

    /**
     * Should not set firstPostDate in AnalysisModel if firstPostDate is not less than
     * AnalysisModel has
     */
    @Test
    public void testAnalyzeFirstPostCase2() {
        // given
        var firstPostDateFromAnalysis = LocalDate.now().minusDays(7);
        var firstPostDate = LocalDate.now().minusDays(1);

        var postData = new PostData();
        postData.setCreationDate(firstPostDate);

        var analysisModel = new AnalysisModel();
        analysisModel.setFirstPost(firstPostDateFromAnalysis);

        // when
        instance.analyzeFirstPost(postData, analysisModel);

        // then
        assertEquals(analysisModel.getFirstPost(), firstPostDateFromAnalysis);
    }

    // TODO add more tests
}