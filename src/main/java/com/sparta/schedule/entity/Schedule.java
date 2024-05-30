package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //11k2hy3k123

    private String title;

    private String contants;

     private String username;

    @Column(nullable = false)
    private String password;

    private LocalDateTime date;
    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contants = requestDto.getContants();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.date = LocalDateTime.now();
    }

//    public void update(ScheduleRequestDto requestDto) {
//        this.title = requestDto.getTitle();
//        this.contants = requestDto.getContants();
//        this.username = requestDto.getUsername();
//        this.password = requestDto.getPassword();
//        this.date = requestDto.getDate();
//    }
    // Getters and setters
}
