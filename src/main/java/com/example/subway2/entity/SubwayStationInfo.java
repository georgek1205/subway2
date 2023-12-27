package com.example.subway2.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Basic_SubwayStation_Info")
public class SubwayStationInfo {

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

    @Column(name = "exitNo")
    private String exitNo;

    @Column(name = "impFaclNm")
    private String impFaclNm;

    @Column(name = "dst")
    private String dst;

    @Column(name = "adr")
    private String adr;

    @Column(name = "telNo")
    private String telNo;

    public SubwayStationInfo(Long openApiId, String railOprIsttCd, String lnCd, String stinCd,
                             String exitNo, String impFaclNm, String dst, String adr, String telNo) {
        this.openApiId = openApiId;
        this.railOprIsttCd = railOprIsttCd;
        this.lnCd = lnCd;
        this.stinCd = stinCd;
        this.exitNo = exitNo;
        this.impFaclNm = impFaclNm;
        this.dst = dst;
        this.adr = adr;
        this.telNo = telNo;
    }
}
