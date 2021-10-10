package org.romanov.yurt.analysis.service;

import org.romanov.yurt.analysis.model.AnalysisModel;

public interface AnalysisService {
    AnalysisModel createAnalysisModel();
    void saveAnalysisModel(final AnalysisModel analysisModel);
    AnalysisModel getAnalysisModelForUid(long uid);
}
