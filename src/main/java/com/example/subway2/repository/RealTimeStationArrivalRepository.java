package com.example.subway2.repository;

import com.example.subway2.entity.RealTimeStationArrivalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealTimeStationArrivalRepository extends JpaRepository<RealTimeStationArrivalInfo,Long> {
}
