package org.romanov.yurt.analysis.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DetailsDto {
    private LocalDate firstPost;
    private LocalDate lastPost;
    private Long totalPosts;
    private Long totalAcceptedPosts;
    private BigDecimal avgScore;
}
