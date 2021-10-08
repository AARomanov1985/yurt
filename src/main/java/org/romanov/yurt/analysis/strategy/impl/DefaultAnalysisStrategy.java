package org.romanov.yurt.analysis.strategy.impl;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class DefaultAnalysisStrategy implements AnalysisStrategy {

    @Override
    public void fillDetailsFromPost(final PostData postData, final AnalysisModel analysisModel) {
        analyzeFirstPost(postData, analysisModel);
        analyzeLastPost(postData, analysisModel);
        analyzeTotalPosts(analysisModel);
        analyzeTotalAcceptedPosts(postData, analysisModel);
        calcSumScore(postData, analysisModel);
    }

    protected void analyzeFirstPost(final PostData postData, final AnalysisModel analysisModel) {
        var postCreationDate = postData.getCreationDate();
        var firstPost = analysisModel.getFirstPost();
        if (isNull(firstPost) || postCreationDate.isBefore(firstPost)) {
            analysisModel.setFirstPost(postCreationDate);
        }
    }

    protected void analyzeLastPost(final PostData postData, final AnalysisModel analysisModel) {
        var postCreationDate = postData.getCreationDate();
        var lastPost = analysisModel.getLastPost();
        if (isNull(lastPost) || postCreationDate.isAfter(lastPost)) {
            analysisModel.setLastPost(postCreationDate);
        }
    }

    protected void analyzeTotalPosts(final AnalysisModel analysisModel) {
        var totalPosts = analysisModel.getTotalPosts();
        if (isNull(totalPosts)) {
            analysisModel.setTotalPosts(1L);
        } else {
            analysisModel.setTotalPosts(totalPosts + 1);
        }
    }

    protected void analyzeTotalAcceptedPosts(final PostData postData, final AnalysisModel analysisModel) {
        if (nonNull(postData.getAcceptedAnswerId())) {
            var totalAcceptedPosts = analysisModel.getTotalAcceptedPosts();
            if (isNull(totalAcceptedPosts)) {
                analysisModel.setTotalAcceptedPosts(1L);
            } else {
                analysisModel.setTotalAcceptedPosts(totalAcceptedPosts + 1);
            }
        }
    }

    protected void calcSumScore(final PostData postData, final AnalysisModel analysisModel) {
        var currentSumScore = getCurrentSumScore(analysisModel);

        var score = postData.getScore();
        if (nonNull(score)) {
            currentSumScore = currentSumScore.add(BigDecimal.valueOf(score));
        }
        analysisModel.setSumScore(currentSumScore);
    }

    private BigDecimal getCurrentSumScore(final AnalysisModel analysisModel) {
        var sumScore = analysisModel.getSumScore();
        return isNull(sumScore) ? BigDecimal.ZERO : sumScore;
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
        Instant start = analysisModel.getStart();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toSeconds();
        analysisModel.setAnalyseTimeInSeconds(timeElapsed);
    }
}


