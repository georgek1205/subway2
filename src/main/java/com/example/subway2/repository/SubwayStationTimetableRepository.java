package com.example.subway2.repository;

import com.example.subway2.entity.SubwayStationTimetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayStationTimetableRepository extends JpaRepository<SubwayStationTimetable,Long> {
}
