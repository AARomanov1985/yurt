package org.romanov.yurt.analysis.strategy.impl;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TotalPostsStrategy implements AnalysisStrategy<PostData, AnalysisModel> {
    @Override
    public void updateAnalysis(final PostData source, final AnalysisModel target) {
        var totalPosts = target.getTotalPosts();
        if (isNull(totalPosts)) {
            target.setTotalPosts(1L);
        } else {
            target.setTotalPosts(totalPosts + 1);
        }
    }
}
