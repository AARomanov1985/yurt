package org.romanov.yurt.analysis.facade.impl;

import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.dto.DetailsDto;
import org.romanov.yurt.analysis.facade.AnalysisFacade;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultAnalysisFacade implements AnalysisFacade {

    @Resource
    private AnalysisService analysisService;

    @Override
    public AnalysisDto getAnalysisDtoForUid(long uid) {
        AnalysisModel analysisModel = analysisService.getAnalysisModelForUid(uid);

        AnalysisDto analysisDto = new AnalysisDto();
        analysisDto.setUid(analysisModel.getUid());
        analysisDto.setAnalyseDate(analysisModel.getAnalyseDate());
        analysisDto.setState(analysisModel.getState());
        analysisDto.setFailedSummary(analysisModel.getFailedSummary());
        analysisDto.setAnalyseTimeInSeconds(analysisModel.getAnalyseTimeInSeconds());

        DetailsDto detailsDto = new DetailsDto();
        detailsDto.setFirstPost(analysisModel.getFirstPost());
        detailsDto.setLastPost(analysisModel.getLastPost());
        detailsDto.setTotalPosts(analysisModel.getTotalPosts());
        detailsDto.setTotalAcceptedPosts(analysisModel.getTotalAcceptedPosts());
        detailsDto.setAvgScore(analysisModel.getAvgScore());
        analysisDto.setDetails(detailsDto);
        return analysisDto;
    }
}
