package com.example.subway2.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Subway_Train_Timetable")
public class SubwayTrainTimetable {

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

    @Column(name = "Collected_at")
    private LocalDateTime accessedAt;


    public SubwayTrainTimetable(String railOprIsttCd, String trnNo,
                                String dayCd, String dayNm, String stinCd,
                                String lnCd, String arvTm, String dptTm) {
        this.railOprIsttCd = railOprIsttCd;
        this.trnNo = trnNo;
        this.dayCd = dayCd;
        this.dayNm = dayNm;
        this.stinCd = stinCd;
        this.lnCd = lnCd;
        this.arvTm = arvTm;
        this.dptTm = dptTm;
        this.accessedAt = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void updateAccessedAt() {
        this.accessedAt = LocalDateTime.now();
    }


}
