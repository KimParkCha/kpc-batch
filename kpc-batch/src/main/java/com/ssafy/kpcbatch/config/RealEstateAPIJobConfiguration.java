package com.ssafy.kpcbatch.config;

import com.ssafy.kpcbatch.entity.Complex;
import com.ssafy.kpcbatch.entity.Region;
import com.ssafy.kpcbatch.processor.RealEstateComplexProcessor;
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
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class RealEstateAPIJobConfiguration {
    private String restUrl;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    @Bean
    public Job complexJob(Step complexStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("complexJob")
//                .preventRestart()
                .start(complexStep())
                .build();
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
        return new RealEstateComplexProcessor(restUrl, new RestTemplate());
    }
    @StepScope
    public JpaItemListWriter<Complex> complexListWriter() {
        final JpaItemWriter<Complex> itemListWriter = new JpaItemWriter<>();
        itemListWriter.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemListWriter<>(itemListWriter);
    }
}
