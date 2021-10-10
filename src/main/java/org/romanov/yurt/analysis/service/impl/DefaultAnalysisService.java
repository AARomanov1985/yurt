package org.romanov.yurt.analysis.service.impl;

import org.romanov.yurt.analysis.dao.AnalysisDao;
import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Override
    public AnalysisModel getAnalysisModelForUid(long uid) {
        Optional<AnalysisModel> byId = analysisDao.findById(uid);
        return byId.orElseThrow(() -> new NoResultException("No AnalysisModel found for uid " + uid));
    }

    @Override
    public void deleteByUid(long uid) {
        analysisDao.deleteById(uid);
    }

    @Override
    public List<AnalysisModel> getAllAnalysis() {
        List<AnalysisModel> result = new ArrayList<>();
        analysisDao.findAll().forEach(result::add);
        return result;
    }
}
