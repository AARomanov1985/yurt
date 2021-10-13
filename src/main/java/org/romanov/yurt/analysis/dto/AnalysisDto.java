package org.romanov.yurt.analysis.dto;

import lombok.Data;
import org.romanov.yurt.analysis.model.State;

import java.time.LocalDate;

@Data
public class AnalysisDto {
    private Long uid;
    private LocalDate analyseDate;
    private State state;
    private String failedSummary;
    private Long analyseTimeInSeconds;
    private DetailsDto details;
}
