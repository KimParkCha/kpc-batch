package com.ssafy.kpcbatch.entity.realPrice;

import com.ssafy.kpcbatch.dto.realPrice.RealPriceDto;
import com.ssafy.kpcbatch.dto.realPrice.RealPriceOnMonthDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RealPriceOnMonth {

    @Id @GeneratedValue
    Long areaNo;

    String tradeBaseYear;
    Integer tradeBaseMonth;

    @OneToMany(cascade = CascadeType.ALL)
    List<RealPrice> realPrices;


}
