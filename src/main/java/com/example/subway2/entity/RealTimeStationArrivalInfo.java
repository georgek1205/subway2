package com.example.subway2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Real_Time_Station_Arrival_Time_Info")
public class RealTimeStationArrivalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "openApiId")
    private Long openApiId;

    @Column(name = "subwayId")
    private String subwayId;

    @Column(name = "subwayNm")
    private String subwayNm;

    @Column(name = "updnLine")
    private String updnLine;

    @Column(name = "trainLineNm")
    private String trainLineNm;

    @Column(name = "subwayHeading")
    private String subwayHeading;

    @Column(name = "statnFid")
    private String statnFid;

    @Column(name = "statnTid")
    private String statnTid;

    @Column(name = "statnId")
    private String statnId;

    @Column(name = "statnNm")
    private String statnNm;

    @Column(name = "trainCo")
    private String trainCo;

    @Column(name = "trnsitCo")
    private String trnsitCo;

    @Column(name = "ordkey")
    private String ordkey;

    @Column(name = "subwayList")
    private String subwayList;

    @Column(name = "statnList")
    private String statnList;

    @Column(name = "btrainSttus")
    private String btrainSttus;

    @Column(name = "barvlDt")
    private String barvlDt;

    @Column(name = "btrainNo")
    private String btrainNo;

    @Column(name = "bstatnId")
    private String bstatnId;

    @Column(name = "bstatnNm")
    private String bstatnNm;

    @Column(name = "recptnDt")
    private String recptnDt;

    @Column(name = "arvlMsg2")
    private String arvlMsg2;

    @Column(name = "arvlMsg3")
    private String arvlMsg3;

    @Column(name = "arvlCd")
    private String arvlCd;

    public RealTimeStationArrivalInfo(String subwayId, String subwayNm, String updnLine,
                                      String trainLineNm, String subwayHeading, String statnFid,
                                      String statnTid, String statnId, String statnNm, String trainCo,
                                      String trnsitCo, String ordkey, String subwayList, String statnList,
                                      String btrainSttus, String barvlDt, String btrainNo,
                                      String bstatnId, String bstatnNm, String recptnDt,
                                      String arvlMsg2, String arvlMsg3, String arvlCd) {
        this.subwayId = subwayId;
        this.subwayNm = subwayNm;
        this.updnLine = updnLine;
        this.trainLineNm = trainLineNm;
        this.subwayHeading = subwayHeading;
        this.statnFid = statnFid;
        this.statnTid = statnTid;
        this.statnId = statnId;
        this.statnNm = statnNm;
        this.trainCo = trainCo;
        this.trnsitCo = trnsitCo;
        this.ordkey = ordkey;
        this.subwayList = subwayList;
        this.statnList = statnList;
        this.btrainSttus = btrainSttus;
        this.barvlDt = barvlDt;
        this.btrainNo = btrainNo;
        this.bstatnId = bstatnId;
        this.bstatnNm = bstatnNm;
        this.recptnDt = recptnDt;
        this.arvlMsg2 = arvlMsg2;
        this.arvlMsg3 = arvlMsg3;
        this.arvlCd = arvlCd;
    }
}
