package org.romanov.yurt.analysis.strategy.impl;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class SumScoreStrategy implements AnalysisStrategy<PostData, AnalysisModel> {
    @Override
    public void updateAnalysis(final PostData source, final AnalysisModel target) {
        var currentSumScore = getCurrentSumScore(target);

        var score = source.getScore();
        if (nonNull(score)) {
            currentSumScore = currentSumScore.add(BigDecimal.valueOf(score));
        }
        target.setSumScore(currentSumScore);
    }

    private BigDecimal getCurrentSumScore(final AnalysisModel analysisModel) {
        var sumScore = analysisModel.getSumScore();
        return isNull(sumScore) ? BigDecimal.ZERO : sumScore;
    }
}
