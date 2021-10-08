package org.romanov.yurt.analysis.strategy;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

public interface AnalysisStrategy {
    /**
     * Fill data in DetailsModel except avgScore.
     * avgScore should be calculated after completion of processing for the entire xml
     *
     * @param postData      current postData
     * @param analysisModel analysisModel
     */
    void fillDetailsFromPost(final PostData postData, final AnalysisModel analysisModel);

    void calculateAvgScore(final AnalysisModel analysisModel);

    void calculateAnalyseTime(final AnalysisModel analysisModel);
}
