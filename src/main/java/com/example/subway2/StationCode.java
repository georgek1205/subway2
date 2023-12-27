package com.example.subway2;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class StationCode {
    private String railOprIsttCd;
    private String lnCd;
    private String stinCd;

    @Override
    public String toString() {
        return "StationCode{" +
                "railOprIsttCd='" + railOprIsttCd + '\'' +
                ", lnCd='" + lnCd + '\'' +
                ", stinCd='" + stinCd + '\'' +
                '}' + '\n';
    }
}
