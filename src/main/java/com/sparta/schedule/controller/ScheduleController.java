package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (title, contants, username, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContants());
                    preparedStatement.setString(3, schedule.getUsername());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getDate());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> Schedule() {
        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                String title = rs.getString("title");
                String contants = rs.getString("contants");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String date = rs.getString("date");
                return new ScheduleResponseDto(title, contants, username, password, date);
            }
        });
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto SelectSchedule(@PathVariable int id) {
        // DB 조회
        Schedule schedule = findById(id);
        return new ScheduleResponseDto(schedule.getTitle(), schedule.getContants(), schedule.getUsername(), schedule.getPassword(), schedule.getDate());
    }

    @PutMapping("/schedules/{id}")
    public int updateSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = findById(id);
        if(schedule != null) {
            String sql = "UPDATE schedule SET title = ?, contants = ?, username = ?, password = ?, date = ? WHERE id = ?"; //요뇨석
            jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContants(), requestDto.getUsername(), requestDto.getPassword(), requestDto.getDate(), id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/schedules/{id}")
    public String deleteSchedule(@PathVariable int id) {
        Schedule schedule = findById(id);
        if(schedule != null) {
            String sql = "DELETE FROM schedule WHERE id = ?";
            jdbcTemplate.update(sql, id);
            return id + "가 삭제되었습니다.";
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    private Schedule findById(int id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContants(resultSet.getString("contants"));
                schedule.setUsername(resultSet.getString("username"));
                schedule.setDate(resultSet.getString("date"));

                return schedule;
            } else {
                return null;
            }
        }, id);
    }
}
