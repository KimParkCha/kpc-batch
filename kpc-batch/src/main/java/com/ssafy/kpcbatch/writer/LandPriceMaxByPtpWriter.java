package com.ssafy.kpcbatch.writer;

import com.ssafy.kpcbatch.dto.complexDetail.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LandPriceMaxByPtpWriter implements ItemWriter<ComplexDetailsDto> {
    private final DataSource dataSource;
    private final JdbcBatchItemWriter jdbcBatchItemWriter;

    public LandPriceMaxByPtpWriter(DataSource dataSource, JdbcBatchItemWriter jdbcBatchItemWriter) {
        this.dataSource = dataSource;
        this.jdbcBatchItemWriter = jdbcBatchItemWriter;
    }


    @Override
    public void write(List<? extends ComplexDetailsDto> items) throws Exception {
        List<ComplexDetailDto> complexDetails = new ArrayList<>();
        List<ComplexPyeongDetailDto> complexPyeongDetailDtos = items.get(0).getComplexPyeongDetailList();
        List<LandPriceMaxByPtpDto> ls = new ArrayList<>();
        complexPyeongDetailDtos.forEach(complexPyeongDetailDto -> {
            if (complexPyeongDetailDto.getLandPriceMaxByPtp() != null)
                ls.add(complexPyeongDetailDto.getLandPriceMaxByPtp());
        });
        if (!ls.isEmpty()) {
            log.info("complexPyeongDetailDto : {}", ls);
            String sql = "INSERT INTO land_price_max_by_ptp" +
                    "(complex_no, ptp_no, city_area_tax, decision_tax, local_edu_tax," +
                    "max_price, min_price, property_tax, property_total_tax," +
                    "real_estate_total_tax, rural_special_tax, std_year, std_ymd, supply_area, total_area" +
                    ") values (:complexNo, :ptpNo, :cityAreaTax, :decisionTax," +
                    ":localEduTax, :maxPrice, :minPrice, :propertyTax, :propertyTotalTax," +
                    ":realEstateTotalTax, :ruralSpecialTax, :stdYear, :stdYmd, :supplyArea, :totalArea)";

            jdbcBatchItemWriter.setDataSource(dataSource);
            jdbcBatchItemWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
            jdbcBatchItemWriter.setSql(sql);
            jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
            jdbcBatchItemWriter.afterPropertiesSet();
            jdbcBatchItemWriter.write(ls);
        }
    }
}
