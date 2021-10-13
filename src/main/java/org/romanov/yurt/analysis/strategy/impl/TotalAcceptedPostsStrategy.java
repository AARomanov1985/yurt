package org.romanov.yurt.analysis.strategy.impl;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class TotalAcceptedPostsStrategy implements AnalysisStrategy<PostData, AnalysisModel> {
    @Override
    public void updateAnalysis(final PostData source, final AnalysisModel target) {
        if (nonNull(source.getAcceptedAnswerId())) {
            var totalAcceptedPosts = target.getTotalAcceptedPosts();
            if (isNull(totalAcceptedPosts)) {
                target.setTotalAcceptedPosts(1L);
            } else {
                target.setTotalAcceptedPosts(totalAcceptedPosts + 1);
            }
        }
    }
}
