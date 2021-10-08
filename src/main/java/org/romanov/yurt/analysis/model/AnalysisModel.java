package org.romanov.yurt.analysis.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "analysis")
public class AnalysisModel {
    // id of the analyze
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    // date request came in 2015-07-14T18:39:27.757
    private LocalDate analyseDate;
    private State state;
    // optional text field
    private String failedSummary;
    // the total time it took to analyze in seconds
    private Long analyseTimeInSeconds;
    // The CreationDate value of a row the file with the lowest value (first in time)
    private LocalDate firstPost;
    // The CreationDate value of a row the file with the highest value (last in time)
    private LocalDate lastPost;
    // total amount unique rows
    private Long totalPosts;
    private Long totalAcceptedPosts;
    private BigDecimal avgScore;
    @Transient
    private BigDecimal sumScore;
    @Transient
    private Instant start;
}
