package com.example.subway2.repository;

import com.example.subway2.entity.SubwayStationGeomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubwayStationGeomRepository extends JpaRepository<SubwayStationGeomInfo, Long> {
}
