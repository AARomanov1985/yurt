package org.romanov.yurt.analysis.converter;

import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.model.AnalysisModel;

public interface AnalysisConverter {
    AnalysisDto convert(final AnalysisModel analysisModel);
}
