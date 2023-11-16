package com.ssafy.kpcbatch.entity.realPrice;

import com.ssafy.kpcbatch.dto.realPrice.RealPriceOnMonthDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RealPrices {

    @Id
    Integer areaNo;
    Integer addedRowCount;
    Integer totalRowCount;
    String realPriceBasisYearMonth;

    @OneToMany(cascade = CascadeType.ALL)
    List<RealPriceOnMonth> realPriceOnMonths;
}
