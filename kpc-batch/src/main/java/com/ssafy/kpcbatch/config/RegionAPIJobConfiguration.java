package com.ssafy.kpcbatch.config;

import com.ssafy.kpcbatch.dto.RegionDto;
import com.ssafy.kpcbatch.entity.Region;
import com.ssafy.kpcbatch.reader.RealEstateRegionReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RegionAPIJobConfiguration {
    private static final String PROPERTY_REST_API_URL = "rest.api.url"; // api 요청 url

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job regionJob(Step collectStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("regionJob")
//                .preventRestart()
                .start(collectStep)
                .build();
    }

    @Bean
    @JobScope
    public Step regionStep(ItemReader<RegionDto> reader, JpaItemWriter<Region> writer) {
        return stepBuilderFactory.get("regionStep")
//                .allowStartIfComplete(true) // Complete 상태가 되었어도 다시 실행
                .<RegionDto, Region>chunk(1)
                .reader(reader)
                .processor(regionProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<RegionDto> regionReader(Environment environment, RestTemplate restTemplate) {
        // Rest API 로 데이터를 가져온다.
        return new RealEstateRegionReader(environment.getRequiredProperty(PROPERTY_REST_API_URL),
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
}
