package com.ssafy.kpcbatch.writer;

import com.ssafy.kpcbatch.dto.complexDetail.ArticleStatisticsDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexPyeongDetailDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ArticleStatisticsWriter implements ItemWriter<ComplexDetailsDto> {
    private final DataSource dataSource;
    private final JdbcBatchItemWriter jdbcBatchItemWriter;

    public ArticleStatisticsWriter(DataSource dataSource, JdbcBatchItemWriter jdbcBatchItemWriter) {
        this.dataSource = dataSource;
        this.jdbcBatchItemWriter = jdbcBatchItemWriter;
    }

    @Override
    public void write(List<? extends ComplexDetailsDto> items) throws Exception {
        List<ComplexDetailDto> complexDetails = new ArrayList<>();
        List<ComplexPyeongDetailDto> complexPyeongDetailDtos = items.get(0).getComplexPyeongDetailList();
        List<ArticleStatisticsDto> ls = new ArrayList<>();
        complexPyeongDetailDtos.forEach(complexPyeongDetailDto -> {
            ls.add(complexPyeongDetailDto.getArticleStatisticsDto());
        });

        String sql = "INSERT INTO article_statistics" +
                "(pyeong_no, complex_no, deal_count, deal_price_max, deal_price_min," +
                "deal_price_per_space_max, deal_price_per_space_min, deal_price_per_space_string, deal_price_string," +
                "lease_count, lease_price_max, lease_price_min, lease_price_per_space_max, lease_price_per_space_min," +
                "lease_price_per_space_string, lease_price_rate_max, lease_price_rate_min, lease_price_rate_string," +
                "lease_price_string, rent_count, short_term_rent_count" +
                ") values (:pyeongNo, :complexNo, :dealCount, :dealPriceMax, :dealPriceMin," +
                ":dealPricePerSpaceMax, :dealPricePerSpaceMin, :dealPricePerSpaceString, :dealPriceString," +
                ":leaseCount, :leasePriceMax, :leasePriceMin, :leasePricePerSpaceMax, :leasePricePerSpaceMin," +
                ":leasePricePerSpaceString, :leasePriceRateMax, :leasePriceRateMin, :leasePriceRateString," +
                ":leasePriceString, :rentCount, :shortTermRentCount)";

        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        jdbcBatchItemWriter.setSql(sql);
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        jdbcBatchItemWriter.afterPropertiesSet();
        jdbcBatchItemWriter.write(complexDetails);
    }
}
