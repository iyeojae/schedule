package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //11k2hy3k123
    private String title;
    private String contants;
    private String username;
    private String password;
    private String date;
    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contants = requestDto.getContants();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contants = requestDto.getContants();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
    // Getters and setters
}
