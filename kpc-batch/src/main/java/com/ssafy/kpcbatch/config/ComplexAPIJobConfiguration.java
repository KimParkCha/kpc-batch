package com.ssafy.kpcbatch.config;

import com.ssafy.kpcbatch.dto.ComplexDto;
import com.ssafy.kpcbatch.dto.ComplexListDto;
import com.ssafy.kpcbatch.dto.RegionDto;
import com.ssafy.kpcbatch.entity.Complex;
import com.ssafy.kpcbatch.entity.Region;
import com.ssafy.kpcbatch.reader.RealEstateComplexReader;
import com.ssafy.kpcbatch.writer.JpaItemListWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ComplexAPIJobConfiguration {
    private static final String PROPERTY_REST_API_URL = "rest.api.complex.url"; // api 요청 url
    private String restUrl;
    private RestTemplate restTemplate;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource; // DataSource DI
    @Bean
    public Job complexJob(Step complexStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("complexJob")
//                .preventRestart()
                .start(complexStep)
                .build();
    }

    @Bean
    @JobScope
    public Step complexStep(ItemReader<Region> reader, JpaItemWriter<Complex> writer) {
        return stepBuilderFactory.get("complexStep")
                .allowStartIfComplete(true)
                .<Region, List<Complex>>chunk(1)
                .reader(reader)
                .processor(complexProcessor())
                .writer(complexWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Region> complexReader(Environment environment, RestTemplate restTemplate) {
        // Rest API 로 데이터를 가져온다.
        restUrl = environment.getRequiredProperty(PROPERTY_REST_API_URL);
        this.restTemplate = restTemplate;
        return new JdbcCursorItemReaderBuilder<Region>()
                .fetchSize(10)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Region.class))
                .sql("SELECT cortar_no from region")
                .name("jdbcCursorItemReader")
                .build();
    }

    @Bean
    public ItemProcessor<Region, List<Complex>>complexProcessor() {
        // 가져온 데이터를 적절히 가공해준다.
        return new RealEstateComplexReader(restUrl, restTemplate);
    }

    @Bean
    public JpaItemListWriter<Complex> complexWriter() {
        JpaItemWriter<Complex> itemListWriter = new JpaItemWriter<>();
        itemListWriter.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemListWriter<>(itemListWriter);
    }
}
