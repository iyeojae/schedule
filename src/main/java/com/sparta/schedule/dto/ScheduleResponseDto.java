package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleResponseDto {
    private String title;
    private String contants;
    private String username;
    private String password;
    private String date;

    public ScheduleResponseDto(Schedule schedule) {
        this.title = schedule.getTitle();
        this.contants = schedule.getContants();
        this.username = schedule.getUsername();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(String title, String contants, String username, String password, String date) {
        this.title = title;
        this.contants = contants;
        this.username = username;
        this.password = password;
        this.date = date;
    }
}
