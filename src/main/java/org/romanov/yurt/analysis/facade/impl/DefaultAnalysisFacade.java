package org.romanov.yurt.analysis.facade.impl;

import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.dto.DetailsDto;
import org.romanov.yurt.analysis.dto.ResultDto;
import org.romanov.yurt.analysis.facade.AnalysisFacade;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultAnalysisFacade implements AnalysisFacade {

    public static final String SUCCESS_MESSAGE = "success";
    public static final String ERROR_MESSAGE = "Failed due to: ";
    @Resource
    private AnalysisService analysisService;

    @Override
    public AnalysisDto getAnalysisDtoForUid(long uid) {
        AnalysisModel analysisModel = analysisService.getAnalysisModelForUid(uid);
        return convert(analysisModel);
    }

    protected AnalysisDto convert(final AnalysisModel analysisModel) {
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

    @Override
    public List<AnalysisDto> getAllAnalysis() {
        List<AnalysisDto> result = new ArrayList<>();
        analysisService.getAllAnalysis()
                .forEach(model -> result.add(convert(model)));
        return result;
    }

    @Override
    public ResultDto deleteByUid(long uid) {
        try {
            analysisService.deleteByUid(uid);
            return new ResultDto(SUCCESS_MESSAGE);
        } catch (EmptyResultDataAccessException ex) {
            return new ResultDto(ERROR_MESSAGE + ex.getMessage());
        }
    }
}
