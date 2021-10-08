package org.romanov.yurt.analysis.dao;

import org.romanov.yurt.analysis.model.AnalysisModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisDao extends CrudRepository<AnalysisModel, Long> {
}
