package com.ssafy.kpcbatch.writer;

import org.springframework.batch.item.database.JpaItemWriter;

import java.util.ArrayList;
import java.util.List;

public class JpaItemListWriter<T> extends JpaItemWriter<List<T>> {
    private JpaItemWriter<T> jpaItemWriter;

    public JpaItemListWriter(JpaItemWriter<T> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(List<? extends List<T>> items) {
        List<T> list = new ArrayList<>();
        for (List<T> ls: items) {
            list.addAll(ls);
        }
        jpaItemWriter.write(list);
    }
}
