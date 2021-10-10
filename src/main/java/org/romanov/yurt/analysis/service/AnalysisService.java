package org.romanov.yurt.analysis.service;

import org.romanov.yurt.analysis.model.AnalysisModel;

import java.util.List;

public interface AnalysisService {
    AnalysisModel createAnalysisModel();
    void saveAnalysisModel(final AnalysisModel analysisModel);
    AnalysisModel getAnalysisModelForUid(long uid);
    void deleteByUid(long uid);
    List<AnalysisModel> getAllAnalysis();
}
