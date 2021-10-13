package org.romanov.yurt.analysis.strategy.impl;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.analysis.strategy.CommonAnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component(value = "commonAnalysisStrategy")
public class DefaultCommonAnalysisStrategy implements CommonAnalysisStrategy<PostData, AnalysisModel> {

    @Resource
    private List<AnalysisStrategy> analysisStrategies;

    @Override
    public void updateAnalysis(final PostData postData, final AnalysisModel analysisModel) {
        analysisStrategies.forEach(s -> s.updateAnalysis(postData, analysisModel));
    }

    @Override
    public void calculateAvgScore(final AnalysisModel analysisModel) {
        var sumScore = analysisModel.getSumScore();
        var totalPosts = analysisModel.getTotalPosts();
        var avgScore = sumScore.divide(BigDecimal.valueOf(totalPosts), 2, RoundingMode.HALF_UP);
        analysisModel.setAvgScore(avgScore);
    }

    @Override
    public void calculateAnalyseTime(final AnalysisModel analysisModel) {
        var start = analysisModel.getStart();
        var finish = Instant.now();
        var timeElapsed = Duration.between(start, finish).toSeconds();
        analysisModel.setAnalyseTimeInSeconds(timeElapsed);
    }
}


