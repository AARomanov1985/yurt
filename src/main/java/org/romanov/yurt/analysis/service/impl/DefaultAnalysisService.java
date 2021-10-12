package org.romanov.yurt.analysis.service.impl;

import org.romanov.yurt.analysis.dao.AnalysisDao;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.romanov.yurt.post.dao.PostDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DefaultAnalysisService implements AnalysisService {

    @Resource
    private AnalysisDao analysisDao;
    @Resource
    private PostDao postDao;

    @Override
    public AnalysisModel createAnalysisModel() {
        var analysis = new AnalysisModel();
        analysis.setAnalyseDate(LocalDate.now());
        analysis.setStart(Instant.now());
        analysis.setState(State.ANALYZING);
        return analysisDao.save(analysis);
    }

    @Override
    public void saveAnalysisModel(final AnalysisModel analysisModel) {
        analysisDao.save(analysisModel);
    }

    @Override
    public AnalysisModel getAnalysisModelForUid(long uid) {
        var byId = analysisDao.findById(uid);
        return byId.orElseThrow(() -> new NoResultException("No AnalysisModel found for uid " + uid));
    }

    @Override
    public void deleteAnalysis(long uid) {
        var model = getAnalysisModelForUid(uid);
        postDao.deleteAllById(model.getPosts());
        analysisDao.delete(model);
    }

    @Override
    public List<AnalysisModel> getAllAnalysis() {
        var iterable = analysisDao.findAll();
        var result = new ArrayList<AnalysisModel>(((Collection<?>) iterable).size());
        iterable.forEach(result::add);
        return result;
    }
}
