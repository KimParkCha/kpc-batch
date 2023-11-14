//package com.ssafy.kpcbatch.writer;
//
//import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailDto;
//import org.springframework.batch.item.database.JpaItemWriter;
//
//import javax.sql.DataSource;
//
//public class ComplexDetailWriter extends JpaItemWriter<ComplexDetailDto> {
//    private final DataSource dataSource;
//    private final JpaItemWriter<ComplexDetailDto> jpaItemWriter;
//
//    public ComplexDetailWriter(DataSource dataSource, JpaItemWriter jpaItemWriter) {
//        this.dataSource = dataSource;
//        this.jpaItemWriter = jpaItemWriter;
//    }
//
//    @Override
//    public void write(ComplexDetailDto items) {
//
//        this.jpaItemWriter.write(items);
//    }
//}
