package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedules/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        return scheduleService.deleteSchedule(id);
    }
}
