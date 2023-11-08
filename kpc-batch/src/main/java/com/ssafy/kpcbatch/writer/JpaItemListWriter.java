package com.ssafy.kpcbatch.writer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {
    private final JpaItemWriter<T> jpaItemWriter;

    public JpaItemListWriter(JpaItemWriter<T> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(final List<? extends List<T>> items) {
        final List<T> list = new ArrayList<>();

        for (final List<T> item : items) {
            list.addAll(item);
        }

        this.jpaItemWriter.write(list);
    }
}
