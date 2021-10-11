package org.romanov.yurt.analysis.converter.impl;

import org.romanov.yurt.analysis.converter.AnalysisConverter;
import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.dto.DetailsDto;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class DefaultAnalysisConverter implements AnalysisConverter {
    public static final String EMPTY = "";

    @Override
    public AnalysisDto convert(AnalysisModel analysisModel) {
        var analysisDto = new AnalysisDto();
        analysisDto.setUid(analysisModel.getUid());
        analysisDto.setAnalyseDate(analysisModel.getAnalyseDate());
        analysisDto.setState(analysisModel.getState());
        populateFailedSummary(analysisModel, analysisDto);
        analysisDto.setAnalyseTimeInSeconds(analysisModel.getAnalyseTimeInSeconds());

        var detailsDto = populateDetailsDto(analysisModel);
        analysisDto.setDetails(detailsDto);
        return analysisDto;
    }

    private DetailsDto populateDetailsDto(final AnalysisModel analysisModel) {
        var detailsDto = new DetailsDto();
        detailsDto.setFirstPost(analysisModel.getFirstPost());
        detailsDto.setLastPost(analysisModel.getLastPost());
        detailsDto.setTotalPosts(analysisModel.getTotalPosts());
        detailsDto.setTotalAcceptedPosts(analysisModel.getTotalAcceptedPosts());
        detailsDto.setAvgScore(analysisModel.getAvgScore());
        return detailsDto;
    }

    private void populateFailedSummary(final AnalysisModel analysisModel, final AnalysisDto analysisDto) {
        var failedSummary = analysisModel.getFailedSummary();
        if (isNull(failedSummary)) {
            analysisDto.setFailedSummary(EMPTY);
        } else {
            analysisDto.setFailedSummary(failedSummary);
        }
    }
}
