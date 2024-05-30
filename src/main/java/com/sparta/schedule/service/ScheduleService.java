package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));

        schedule.setTitle(requestDto.getTitle());
        schedule.setContants(requestDto.getContants());
        schedule.setUsername(requestDto.getUsername());
        schedule.setPassword(requestDto.getPassword());

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public String deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));

        scheduleRepository.delete(schedule);

        return id + "가 삭제되었습니다.";
    }
}
