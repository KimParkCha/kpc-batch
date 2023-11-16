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
public class RegionAPIJobConfiguration {
    private static final String PROPERTY_REST_API_URL = "rest.api.url"; // api 요청 url
    private String restUrl = "https://new.land.naver.com/api/complexes";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    @Bean
    public Job regionJob(Step regionStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("regionJob")
//                .preventRestart()
                .start(regionStep)
                .start(complexStep())
                .build();
    }

    @Bean
    @JobScope
    public Step regionStep(ItemReader<RegionDto> reader, JpaItemWriter<Region> writer) {
        return stepBuilderFactory.get("regionStep")
                .allowStartIfComplete(true) // Complete 상태가 되었어도 다시 실행
                .<RegionDto, Region>chunk(1)
                .reader(reader)
                .processor(regionProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<RegionDto> regionReader(Environment environment, RestTemplate restTemplate) {
        // Rest API 로 데이터를 가져온다.
        return new RegionReader(environment.getRequiredProperty(PROPERTY_REST_API_URL),
                restTemplate);
    }

    @Bean
    public ItemProcessor<RegionDto, Region> regionProcessor() {
        // 가져온 데이터를 적절히 가공해준다.
        return regionDto -> Region.builder()
                .cortarNo(regionDto.getCortarNo())
                .cortarName(regionDto.getCortarName())
                .centerLat(regionDto.getCenterLat())
                .centerLon(regionDto.getCenterLon())
                .cortarType(regionDto.getCortarType())
                .build();
    }

    @Bean
    public JpaItemWriter<Region> regionWriter() {
        // 데이터베이스에 저장한다.
        JpaItemWriter<Region> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
    @JobScope
    public Step complexStep() {
        return stepBuilderFactory.get("complexStep")
                .allowStartIfComplete(true)
                .<Region, List<Complex>>chunk(1)
                .reader(complexReader())
                .processor(complexProcessor())
                .writer(complexListWriter())
                .build();
    }

    @StepScope
    public JpaPagingItemReader<? extends Region> complexReader() {
        // Rest API 로 데이터를 가져온다.
        restUrl = "https://new.land.naver.com/api/regions/complexes";
        return new JpaPagingItemReaderBuilder<Region>()
                .queryString("select r from Region r where cortarType='sec'")
                .entityManagerFactory(entityManagerFactory)
                .name("jdbcCursorItemReader")
                .pageSize(1)
                .build();
    }

    @StepScope
    public ItemProcessor<Region, List<Complex>>complexProcessor() {
        // 가져온 데이터를 적절히 가공해준다.
        return new ComplexProcessor(restUrl, new RestTemplate());
    }
    @StepScope
    public JpaItemListWriter<Complex> complexListWriter() {
        final JpaItemWriter<Complex> itemListWriter = new JpaItemWriter<>();
        itemListWriter.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemListWriter<>(itemListWriter);
    }
}
