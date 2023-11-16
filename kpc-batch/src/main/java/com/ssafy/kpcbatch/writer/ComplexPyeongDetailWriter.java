package com.ssafy.kpcbatch.writer;

import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexPyeongDetailDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ComplexPyeongDetailWriter implements ItemWriter<ComplexDetailsDto> {
    private final DataSource dataSource;
    private final JdbcBatchItemWriter jdbcBatchItemWriter;

    public ComplexPyeongDetailWriter(DataSource dataSource, JdbcBatchItemWriter jdbcBatchItemWriter) {
        this.dataSource = dataSource;
        this.jdbcBatchItemWriter = jdbcBatchItemWriter;
    }

    @Override
    public void write(List<? extends ComplexDetailsDto> items) throws Exception {
        List<ComplexPyeongDetailDto> complexPyeongDetails = items.get(0).getComplexPyeongDetailList();

        String sql = "INSERT INTO complex_pyeong_detail" +
                "(pyeong_no, supply_area_double, supply_area, pyeong_name, supply_pyeong," +
                "pyeong_name2, exclusive_area, exclusive_pyeong, household_count_by_pyeong," +
                "real_estate_type_code, exclusive_rate" +
                ") values (:pyeongNo, :supplyAreaDouble, :supplyArea, :pyeongName, :supplyPyeong," +
                ":pyeongName2, :exclusiveArea, :exclusivePyeong, :householdCountByPyeong," +
                ":realEstateTypeCode, :exclusiveRate" +
                ")";

        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        jdbcBatchItemWriter.setSql(sql);
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        jdbcBatchItemWriter.afterPropertiesSet();
        jdbcBatchItemWriter.write(complexPyeongDetails);
    }
}
