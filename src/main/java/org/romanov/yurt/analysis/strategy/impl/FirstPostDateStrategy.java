package org.romanov.yurt.analysis.strategy.impl;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class FirstPostDateStrategy implements AnalysisStrategy<PostData, AnalysisModel> {
    @Override
    public void updateAnalysis(final PostData source, final AnalysisModel target) {
        var postCreationDate = source.getCreationDate();
        var firstPost = target.getFirstPost();
        if (isNull(firstPost) || postCreationDate.isBefore(firstPost)) {
            target.setFirstPost(postCreationDate);
        }
    }
}
