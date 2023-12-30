package com.example.subway2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(name = "Collected_at")
    private LocalDateTime accessedAt;

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
        this.accessedAt = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void updateAccessedAt() {
        this.accessedAt = LocalDateTime.now();
    }

    //즉, 개발자가 Hibernate를 사용하면, JPA의 인터페이스를 통해 데이터베이스를 객체 지향적으로 다룰 수 있으며,
    // 이는 내부적으로는 ORM 기법을 통해 이루어집니다. 이렇게 하면 개발자는 SQL 쿼리를 직접 다루지 않아도 객체를
    // 통해 데이터베이스 작업을 수행할 수 있으므로, 생산성을 크게 향상시킬 수 있습니다.
    //Hibernate를 사용한다는 것은 JPA의 표준을 따르는 것이며, JPA는 ORM(Object-Relational Mapping)의 구동 원리를 기반으로 합니다.
}
