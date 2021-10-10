package org.romanov.yurt.analysis.facade;

import org.romanov.yurt.analysis.dto.AnalysisDto;

public interface AnalysisFacade {
    AnalysisDto getAnalysisDtoForUid(long uid);
}
