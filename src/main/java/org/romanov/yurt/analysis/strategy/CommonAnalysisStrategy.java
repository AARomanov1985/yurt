package org.romanov.yurt.analysis.strategy;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.post.data.PostData;

public interface CommonAnalysisStrategy<SOURCE extends PostData, TARGET extends AnalysisModel>
        extends AnalysisStrategy {

    void calculateAvgScore(final TARGET analysisModel);

    void calculateAnalyseTime(final TARGET analysisModel);
}
