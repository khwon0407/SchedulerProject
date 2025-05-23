package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public SaveScheduleResponseDto saveSchedule(Schedule newSchedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        parameters.put("title", newSchedule.getTitle());
        parameters.put("contents", newSchedule.getContents());
        parameters.put("name", newSchedule.getName());
        parameters.put("password", newSchedule.getPassword());
        parameters.put("created_at", createdAt);
        parameters.put("modified_at", createdAt);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SaveScheduleResponseDto(key.longValue(),
                newSchedule.getTitle(),
                newSchedule.getContents(),
                newSchedule.getName(),
                newSchedule.getPassword(),
                createdAt,
                createdAt);
    }
}
