package org.romanov.yurt.analysis.facade;

import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.dto.ResultDto;

import java.util.List;

public interface AnalysisFacade {
    AnalysisDto getAnalysisDtoForUid(long uid);
    List<AnalysisDto> getAllAnalysis();
    ResultDto deleteAnalysis(long uid);
}
