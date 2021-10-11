package org.romanov.yurt.analysis.facade.impl;

import org.romanov.yurt.analysis.converter.AnalysisConverter;
import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.dto.ResultDto;
import org.romanov.yurt.analysis.facade.AnalysisFacade;
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
    @Resource
    private AnalysisConverter analysisConverter;

    @Override
    public AnalysisDto getAnalysisDtoForUid(long uid) {
        var analysisModel = analysisService.getAnalysisModelForUid(uid);
        return analysisConverter.convert(analysisModel);
    }

    @Override
    public List<AnalysisDto> getAllAnalysis() {
        var models = analysisService.getAllAnalysis();
        var dtos = new ArrayList<AnalysisDto>(models.size());
        models.forEach(model -> dtos.add(analysisConverter.convert(model)));
        return dtos;
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
