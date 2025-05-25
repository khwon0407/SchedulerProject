package com.example.schedulerproject.repository;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
