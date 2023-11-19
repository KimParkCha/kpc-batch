package com.ssafy.kpcbatch.config;

import com.ssafy.kpcbatch.dto.property.ArticleDto;
import com.ssafy.kpcbatch.entity.property.Article;
import com.ssafy.kpcbatch.processor.ComplexPropertyProcessor;
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
public class ComplexPropertyAPIJobConfiguration {
    private String restUrl;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    @Bean
    public Job complexPropertyJob(Step complexPropertyStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("complexPropertyJob")
//                .preventRestart()
                .start(complexPropertyStep())
                .build();
    }

    @JobScope
    public Step complexPropertyStep() {
        return stepBuilderFactory.get("complexPropertyStep")
                .allowStartIfComplete(true)
                .<Long, List<Article>>chunk(1)
                .reader(complexPropertyReader())
                .processor(complexPropertyProcessor())
                .writer(complexPropertyWriter())
                .build();
    }

    @StepScope
    public JpaPagingItemReader<? extends Long> complexPropertyReader() {
        // Rest API 로 데이터를 가져온다.
        restUrl = "https://new.land.naver.com/api/complexes";
        return new JpaPagingItemReaderBuilder<Long>()
                .queryString("select complexNo from Complex co")
                .entityManagerFactory(entityManagerFactory)
                .name("jdbcPagingItemReader")
                .pageSize(1)
                .build();
    }

    @StepScope
    public ItemProcessor<Long, List<Article>> complexPropertyProcessor() {
        // 가져온 데이터를 적절히 가공해준다.
        return new ComplexPropertyProcessor(restUrl, new RestTemplate());
    }
    @StepScope
    public JpaItemListWriter<Article> complexPropertyWriter() {
        final JpaItemWriter<Article> itemListWriter = new JpaItemWriter<>();
        itemListWriter.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemListWriter<>(itemListWriter);
    }
}