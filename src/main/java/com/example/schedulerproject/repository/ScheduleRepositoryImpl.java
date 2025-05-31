package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.FindScheduleRequestDto;
import com.example.schedulerproject.dto.FindScheduleResponseDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.dto.UpdateScheduleRequestDto;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.exception.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Lv 1. 일정 생성
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

    //Lv 1. 일정 전체 조회
    @Override
    public List<FindScheduleResponseDto> findAllSchedule(FindScheduleRequestDto requestDto) {
        String name = requestDto.getName();
        LocalDate modifiedAt = requestDto.getModifiedAt();

        if (name != null && modifiedAt == null) {
            return jdbcTemplate.query("select * from schedule where name = ? order by modified_at desc", scheduleRowMapper(), name);
        }

        LocalDateTime startOfDay = modifiedAt.atStartOfDay();
        LocalDateTime endOfDay = modifiedAt.atTime(23, 59, 59);

        Timestamp startTimestamp = Timestamp.valueOf(startOfDay);
        Timestamp endTimestamp = Timestamp.valueOf(endOfDay);

        if (name != null && modifiedAt != null) {
            return jdbcTemplate.query("select * from schedule where name = ? and modified_at between ? and ? order by modified_at desc", scheduleRowMapper(), name, startTimestamp, endTimestamp);
        }

        if (name == null && modifiedAt != null) {
            return jdbcTemplate.query("select * from schedule where modified_at BETWEEN ? AND ? order by modified_at desc", scheduleRowMapper(), startTimestamp, endTimestamp);
        }

        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public List<FindScheduleResponseDto> findAllScheduleWithUserId(Long userId) {
        return jdbcTemplate.query("select * from scheduleV2 s join userV2 m on s.user_id = m.id where user_id = ? order by s.modified_at desc", scheduleRowMapper(), userId);
    }

    //Lv 1. 일정 단건 조회
    //Lv 5. 예외 처리
    @Override
    public Schedule findOneSchedule(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(NotFoundException::new);
    }

    @Override
    public Schedule findOneScheduleV2(Long id) {
        List<Schedule> result = jdbcTemplate.query("select * from scheduleV2 where id = ?", scheduleRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(NotFoundException::new);
    }

    //Lv 4. 페이징
    @Override
    public List<FindScheduleResponseDto> findScheduleWithPage(Pageable pageable) {
        String sql = "SELECT * FROM schedule ORDER BY modified_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(
                sql,
                scheduleRowMapper(),
                pageable.getPageSize(),
                pageable.getOffset()
        );
    }

    //Lv 3, 4 테이블 분리 및 페이징
    @Override
    public List<FindScheduleResponseDto> findScheduleWithPageV2(Pageable pageable) {
        String sql = "SELECT * FROM scheduleV2 ORDER BY modified_at DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(
                sql,
                scheduleRowMapper(),
                pageable.getPageSize(),
                pageable.getOffset()
        );
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM schedule";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //Lv 2. 스케쥴 업데이트
    @Override
    public int updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {
        Timestamp modifiedAt = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("name: " + requestDto.getName());
        System.out.println("title: " + requestDto.getTitle());
        System.out.println("contents: " + requestDto.getContents());
        if (requestDto.getName() == null) {
            return jdbcTemplate.update("update schedule set title = ?, contents = ?, modified_at = ? where id = ?", requestDto.getTitle(), requestDto.getContents(), modifiedAt, id);
        }
        return jdbcTemplate.update("update schedule set title = ?, contents = ?, name = ?, modified_at = ? where id = ?", requestDto.getTitle(), requestDto.getContents(), requestDto.getName(), modifiedAt, id);
    }

    @Override
    public int updateScheduleV2(Long id, UpdateScheduleRequestDto requestDto) {
        Timestamp modifiedAt = Timestamp.valueOf(LocalDateTime.now());

        return jdbcTemplate.update("update scheduleV2 set title = ?, contents = ?, modified_at = ? where id = ?", requestDto.getTitle(), requestDto.getContents(), modifiedAt, id);
    }

    //Lv 2. 스케쥴 삭제
    @Override
    public int deleteScheduleById(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }

    @Override
    public int deleteScheduleByIdV2(Long id) {
        return jdbcTemplate.update("delete from scheduleV2 where id = ?", id);
    }

    //Lv 3. 테이블 분리 및 검색
    @Override
    public SaveScheduleResponseDto saveScheduleWithUserId(Schedule newSchedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("scheduleV2").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        parameters.put("title", newSchedule.getTitle());
        parameters.put("contents", newSchedule.getContents());
        parameters.put("name", newSchedule.getName());
        parameters.put("password", newSchedule.getPassword());
        parameters.put("created_at", createdAt);
        parameters.put("modified_at", createdAt);
        parameters.put("user_id", newSchedule.getUserId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SaveScheduleResponseDto(key.longValue(),
                newSchedule.getTitle(),
                newSchedule.getContents(),
                newSchedule.getName(),
                newSchedule.getPassword(),
                createdAt,
                createdAt);
    }

    private RowMapper<FindScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<FindScheduleResponseDto>() {
            @Override
            public FindScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new FindScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("name"),
                        rs.getTimestamp("modified_at")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("modified_at")
                );
            }
        };
    }
}
