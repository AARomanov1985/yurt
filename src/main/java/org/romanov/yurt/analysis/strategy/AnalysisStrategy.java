package org.romanov.yurt.analysis.strategy;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

public interface AnalysisStrategy<SOURCE extends PostData, TARGET extends AnalysisModel> {
    /**
     * Fill data in AnalysisModel except avgScore.
     * avgScore should be calculated after completion of processing for the entire xml
     *
     * @param postData      current postData
     * @param analysisModel analysisModel
     */
    void updateAnalysis(final SOURCE postData, final TARGET analysisModel);
}
