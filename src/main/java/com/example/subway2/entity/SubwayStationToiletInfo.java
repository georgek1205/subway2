package com.example.subway2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Subway_Station_Toilet_Info")
public class SubwayStationToiletInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "openApiId")
    private Long openApiId;

    @Column(name = "railOprIsttCd")
    private String railOprIsttCd;

    @Column(name = "lnCd")
    private String lnCd;

    @Column(name = "stinCd")
    private String stinCd;

    @Column(name = "grndDvNm")
    private String grndDvNm;

    @Column(name = "stinFlor")
    private String stinFlor;

    @Column(name = "gateInotDvNm")
    private String gateInotDvNm;

    @Column(name = "exitNo")
    private String exitNo;

    @Column(name = "dtlLoc")
    private String dtlLoc;

    @Column(name = "mlFmlDvNm")
    private String mlFmlDvNm;

    @Column(name = "toltNum")
    private String toltNum;

    @Column(name = "diapExchNum")
    private String diapExchNum;

    @Column(name = "Collected_at")
    private LocalDateTime accessedAt;


    public SubwayStationToiletInfo(String railOprIsttCd, String lnCd,
                                   String stinCd, String grndDvNm, String stinFlor, String gateInotDvNm,
                                   String exitNo, String dtlLoc, String mlFmlDvNm, String toltNum,
                                   String diapExchNum) {
//        this.openApiId = openApiId;
        this.railOprIsttCd = railOprIsttCd;
        this.lnCd = lnCd;
        this.stinCd = stinCd;
        this.grndDvNm = grndDvNm;
        this.stinFlor = stinFlor;
        this.gateInotDvNm = gateInotDvNm;
        this.exitNo = exitNo;
        this.dtlLoc = dtlLoc;
        this.mlFmlDvNm = mlFmlDvNm;
        this.toltNum = toltNum;
        this.diapExchNum = diapExchNum;
        this.accessedAt = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void updateAccessedAt() {
        this.accessedAt = LocalDateTime.now();
    }

}
