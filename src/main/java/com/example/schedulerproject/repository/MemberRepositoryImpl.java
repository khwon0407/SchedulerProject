package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.dto.CreateMemberResponseDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.entity.Member;
import com.example.schedulerproject.entity.Schedule;
import lombok.Getter;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CreateMemberResponseDto createMember(CreateMemberRequestDto requestDto) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("userV2").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        parameters.put("name", requestDto.getName());
        parameters.put("email", requestDto.getEmail());
        parameters.put("created_at", createdAt);
        parameters.put("modified_at", createdAt);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CreateMemberResponseDto(key.longValue(),
                requestDto.getName(),
                requestDto.getEmail(),
                createdAt,
                createdAt);
    }

    @Override
    public Member findMember(Long userId) {
        List<Member> result = jdbcTemplate.query("select * from userV2 where id = ?", memberRowMapper(), userId);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + userId));
    }

    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Member(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("modified_at")
                );
            }
        };
    }
}
