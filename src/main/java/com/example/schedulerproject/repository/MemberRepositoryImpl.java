package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.entity.Member;
import com.example.schedulerproject.entity.Schedule;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createMember(CreateMemberRequestDto requestDto) {
        String sql = "INSERT INTO userV2 (name, email, created_at, modified_at) VALUES (?, ?, ?, ?)";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        jdbcTemplate.update(sql,
                requestDto.getName(),
                requestDto.getEmail(),
                now,
                now
        );
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
