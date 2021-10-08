package org.romanov.yurt.analysis.strategy.impl;

import org.junit.jupiter.api.Test;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultAnalysisStrategyTests {

    private DefaultAnalysisStrategy instance = new DefaultAnalysisStrategy();

    /**
     * Should set firstPostDate in AnalysisModel if firstPostDate is less than
     * AnalysisModel has
     */
    @Test
    public void testAnalyzeFirstPostCase1() {
        System.out.println("teststststst");
        // given
        LocalDate firstPostDate = LocalDate.now().minusDays(3);

        PostData postData = new PostData();
        postData.setCreationDate(firstPostDate);

        AnalysisModel analysisModel = new AnalysisModel();
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
        LocalDate firstPostDateFromAnalysis = LocalDate.now().minusDays(7);
        LocalDate firstPostDate = LocalDate.now().minusDays(1);

        PostData postData = new PostData();
        postData.setCreationDate(firstPostDate);

        AnalysisModel analysisModel = new AnalysisModel();
        analysisModel.setFirstPost(firstPostDateFromAnalysis);

        // when
        instance.analyzeFirstPost(postData, analysisModel);

        // then
        assertEquals(analysisModel.getFirstPost(), firstPostDateFromAnalysis);
    }

    // TODO add more tests
}