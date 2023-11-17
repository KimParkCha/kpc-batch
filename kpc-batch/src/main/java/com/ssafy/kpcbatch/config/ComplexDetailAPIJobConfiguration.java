package com.ssafy.kpcbatch.config;

import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import com.ssafy.kpcbatch.processor.ComplexDetailProcessor;
import com.ssafy.kpcbatch.writer.ArticleStatisticsWriter;
import com.ssafy.kpcbatch.writer.ComplexDetailWriter;
import com.ssafy.kpcbatch.writer.ComplexPyeongDetailWriter;
import com.ssafy.kpcbatch.writer.LandPriceMaxByPtpWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ComplexDetailAPIJobConfiguration {
    private String restUrl = "https://new.land.naver.com/api/complexes";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    @Bean
    public Job complexDetailJob(Step complexDetailStep) { // 이런식으로 의존성 주입을 받을 수도 있구나
        return jobBuilderFactory.get("complexDetailJob")
//                .preventRestart()
                .start(complexDetailStep())
                .build();
    }

    @JobScope
    public Step complexDetailStep() {
        return stepBuilderFactory.get("complexDetailStep")
                .allowStartIfComplete(true)
                .<Long, ComplexDetailsDto>chunk(1)
                .reader(complexDetailReader())
                .processor(complexDetailProcessor())
                .writer(compositeComplexDetailItemWriter())
                .build();
    }

    @StepScope
    public JpaPagingItemReader<? extends Long> complexDetailReader() {
        // Rest API 로 데이터를 가져온다.
        return new JpaPagingItemReaderBuilder<Long>()
                .queryString("select complexNo from Complex co")
                .entityManagerFactory(entityManagerFactory)
                .name("jdbcPagingItemReader")
                .pageSize(1)
                .build();
    }

    @StepScope
    public ItemProcessor<Long, ComplexDetailsDto> complexDetailProcessor() {
        // 가져온 데이터를 적절히 가공해준다.
        return new ComplexDetailProcessor(restUrl, new RestTemplate());
    }
    @StepScope
    public ItemWriter<? super ComplexDetailsDto> complexDetailWriter() {
        final JpaItemWriter<ComplexDetailsDto> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);

        return new JpaItemWriter();
    }

    @StepScope
    public CompositeItemWriter<ComplexDetailsDto> compositeComplexDetailItemWriter() {
        List<ItemWriter> delegates = new ArrayList<>(3);
        delegates.add(new ComplexDetailWriter(dataSource, new JdbcBatchItemWriter()));
        delegates.add(new ComplexPyeongDetailWriter(dataSource, new JdbcBatchItemWriter()));
        delegates.add(new ArticleStatisticsWriter(dataSource, new JdbcBatchItemWriter()));



        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        compositeItemWriter.setDelegates(delegates);
        return compositeItemWriter;
    }
}