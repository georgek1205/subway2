package com.example.subway2.repository;

import com.example.subway2.entity.SubwayTrainTimetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayTrainTimetableRepository extends JpaRepository<SubwayTrainTimetable, Long> {
}
