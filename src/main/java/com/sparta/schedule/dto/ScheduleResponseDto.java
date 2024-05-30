package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private String title;
    private String contants;
    private String username;
    private String password;
    private LocalDateTime date;

    public ScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.contants = schedule.getContants();
        this.username = schedule.getUsername();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }

//    public ScheduleResponseDto(String title, String contants, String username, String password, LocalDateTime date) {
//        this.title = title;
//        this.contants = contants;
//        this.username = username;
//        this.password = password;
//        this.date = date;
//    }
}
