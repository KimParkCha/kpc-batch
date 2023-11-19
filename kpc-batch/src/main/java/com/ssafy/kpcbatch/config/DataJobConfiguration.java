package com.ssafy.kpcbatch.config;

import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.dto.region.RegionDto;
import com.ssafy.kpcbatch.entity.complex.Complex;
import com.ssafy.kpcbatch.entity.region.Region;
import com.ssafy.kpcbatch.processor.ComplexDetailProcessor;
import com.ssafy.kpcbatch.processor.ComplexProcessor;
import com.ssafy.kpcbatch.reader.RegionReader;
import com.ssafy.kpcbatch.writer.ComplexDetailWriter;
import com.ssafy.kpcbatch.writer.JpaItemListWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DataJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;

    private final RegionAPIJobConfiguration regionAPIJobConfiguration;
    private final ComplexAPIJobConfiguration complexAPIJobConfiguration;
    private final ComplexDetailAPIJobConfiguration complexDetailAPIJobConfiguration;
    private final ComplexPropertyAPIJobConfiguration complexPropertyAPIJobConfiguration;
    private final ComplexRealPriceAPIJobConfiguration complexRealPriceAPIJobConfiguration;


    @Bean
    public Job dataJob(Step dataStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("dataJob")
//                .preventRestart()
                .start(regionAPIJobConfiguration.regionStep())
                .start(complexAPIJobConfiguration.complexStep())
                .start(complexDetailAPIJobConfiguration.complexDetailStep())
                .start(complexPropertyAPIJobConfiguration.complexPropertyStep())
                .start(complexRealPriceAPIJobConfiguration.complexRealPriceStep())
                .build();
    }

}
