package com.example.subway2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Subway_Station_Timetable")
public class SubwayStationTimetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "openApiId")
    private Long openApiId;

    @Column(name = "railOprIsttCd")
    private String railOprIsttCd;

    @Column(name = "trnNo")
    private String trnNo;

    @Column(name = "dayCd")
    private String dayCd;

    @Column(name = "dayNm")
    private String dayNm;

    @Column(name = "stinCd")
    private String stinCd;

    @Column(name = "lnCd")
    private String lnCd;

    @Column(name = "arvTm")
    private String arvTm;

    @Column(name = "dptTm")
    private String dptTm;

    @Column(name = "orgStinCd")
    private String orgStinCd;

    @Column(name = "tmnStinCd")
    private String tmnStinCd;

    public SubwayStationTimetable(String railOprIsttCd, String trnNo,
                                  String dayCd, String dayNm, String stinCd,
                                  String lnCd, String arvTm, String dptTm,
                                  String orgStinCd, String tmnStinCd) {
        this.railOprIsttCd = railOprIsttCd;
        this.trnNo = trnNo;
        this.dayCd = dayCd;
        this.dayNm = dayNm;
        this.stinCd = stinCd;
        this.lnCd = lnCd;
        this.arvTm = arvTm;
        this.dptTm = dptTm;
        this.orgStinCd = orgStinCd;
        this.tmnStinCd = tmnStinCd;
    }
}
