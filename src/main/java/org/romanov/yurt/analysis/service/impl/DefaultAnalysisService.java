package org.romanov.yurt.analysis.service.impl;

import org.romanov.yurt.analysis.dao.AnalysisDao;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDate;

@Component
public class DefaultAnalysisService implements AnalysisService {

    @Resource
    private AnalysisDao analysisDao;

    @Override
    public AnalysisModel createAnalysisModel() {
        AnalysisModel analysis = new AnalysisModel();
        analysis.setAnalyseDate(LocalDate.now());
        analysis.setStart(Instant.now());
        analysis.setState(State.Analyzing);
        return analysisDao.save(analysis);
    }

    @Override
    public void saveAnalysisModel(final AnalysisModel analysisModel) {
        analysisDao.save(analysisModel);
    }
}
