package com.example.subway2.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Subway_Station_Geom")
public class SubwayStationGeomInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "openApiId")
    private Long openApiId;

    @Column(name = "outStnNum")
    private String outStnNum;

    @Column(name = "stnKrNm")
    private String stnKrNm;

    @Column(name = "lineNm")
    private String lineNm;

    @Column(name = "convX")
    private String convX;

    @Column(name = "convY")
    private String convY;

    @Column(name = "Collected_at")
    private LocalDateTime accessedAt;

    public SubwayStationGeomInfo(String outStnNum, String stnKrNm, String lineNm, String convX, String convY) {
        this.outStnNum = outStnNum;
        this.stnKrNm = stnKrNm;
        this.lineNm = lineNm;
        this.convX = convX;
        this.convY = convY;
        this.accessedAt = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void updateAccessedAt() {
        this.accessedAt = LocalDateTime.now();
    }

}
