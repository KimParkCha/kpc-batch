package com.ssafy.kpcbatch.writer;

import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexPyeongDetailDto;
import com.ssafy.kpcbatch.dto.complexDetail.LandPriceMaxByPtpDto;
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
//        List<ComplexDetailDto> complexDetails = new ArrayList<>();
//
//
//        String sql = "INSERT INTO land_price_max_by_ptp" +
//                "(pyeong_no, deal_count, lease_count, rent_count, short_term_rent_count," +
//                "deal_price_min, deal_price_max, lease_price_min, lease_price_max," +
//                "deal_price_per_space_min, deal_price_per_space_max, lease_price_per_space_min, lease_price_per_space_string" +
//                "lease_price_per_space_max, lease_price_rate_min, lease_price_rate_max, deal_price_string" +
//                "deal_price_per_space_string, lease_price_string, lease_price_per_space_string, lease_price_rate_string" +
//                ") values (:pyeongNo, :dealCount, :leaseCount, :rentCount, :shortTermRentCount," +
//                ":dealPriceMin, :dealPriceMax, :leasePriceMin, :leasePriceMax," +
//                ":dealPricePerSpaceMin, :dealPricePerSpaceMax, :leasePricePerSpaceMin, :leasePricePerSpaceString" +
//                ":leasePricePerSpaceMax, :leasePriceRateMin, :leasePriceRateMax, :dealPriceString" +
//                ":dealPricePerSpaceString, :leasePriceString, :leasePricePerSpaceString, :leasePriceRateString" +
//                ")";
//
//        jdbcBatchItemWriter.setDataSource(dataSource);
//        jdbcBatchItemWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
//        jdbcBatchItemWriter.setSql(sql);
//        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
//        jdbcBatchItemWriter.afterPropertiesSet();
//        jdbcBatchItemWriter.write(complexPyeongDetails);
    }
}
