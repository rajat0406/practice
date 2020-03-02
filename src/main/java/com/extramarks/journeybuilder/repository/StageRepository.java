package com.extramarks.journeybuilder.repository;

import com.extramarks.journeybuilder.model.StageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<StageModel, Long> {
    @Query("select s from StageModel s where s.contentName=:contentName")
    StageModel findByContentName(String contentName);
}
