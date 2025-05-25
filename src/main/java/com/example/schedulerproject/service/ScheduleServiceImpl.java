package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.*;
import com.example.schedulerproject.entity.Member;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.MemberRepository;
import com.example.schedulerproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    @Override
    public SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto) {
        Schedule newSchedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getName(),
                requestDto.getPassword()
        );

        return scheduleRepository.saveSchedule(newSchedule);
    }

    @Override
    public SaveScheduleResponseDto saveScheduleWithUserId(Long userId, SaveScheduleRequestDtoV2 requestDto) {
        Member findMember = memberRepository.findMember(userId);

        Schedule newSchedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                findMember.getName(),
                requestDto.getPassword(),
                userId
        );

        return scheduleRepository.saveScheduleWithUserId(newSchedule);
    }

    @Override
    public List<FindScheduleResponseDto> findAllSchedule(FindScheduleRequestDto requestDto) {

        return scheduleRepository.findAllSchedule(requestDto);
    }

    @Override
    public FindScheduleResponseDto findOneSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findOneSchedule(id);

        return new FindScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getName(),
                findSchedule.getModifiedAt()
        );
    }

    @Override
    public FindScheduleResponseDto findOneScheduleV2(Long id) {
        Schedule findSchedule = scheduleRepository.findOneScheduleV2(id);

        return new FindScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getName(),
                findSchedule.getModifiedAt()
        );
    }

    @Override
    public Page<FindScheduleResponseDto> findScheduleWithPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        List<FindScheduleResponseDto> content = scheduleRepository.findScheduleWithPage(pageable);
        int total = scheduleRepository.countAll();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<FindScheduleResponseDto> findScheduleWithPageV2(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        List<FindScheduleResponseDto> content = scheduleRepository.findScheduleWithPageV2(pageable);
        int total = scheduleRepository.countAll();

        return new PageImpl<>(content, pageable, total);
    }

    @Transactional
    @Override
    public UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {
        if(requestDto.getTitle() == null || requestDto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        Schedule findSchedule = scheduleRepository.findOneSchedule(id);

        if(!findSchedule.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password.");
        }

        int updateRow = scheduleRepository.updateSchedule(id, requestDto);

        if(updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Schedule schedule = scheduleRepository.findOneSchedule(id);

        return new UpdateScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getName(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    @Override
    public UpdateScheduleResponseDto updateScheduleV2(Long id, UpdateScheduleRequestDto requestDto) {
        if(requestDto.getTitle() == null || requestDto.getContents() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        Schedule findSchedule = scheduleRepository.findOneScheduleV2(id);

        //Lv 5. 비밀번호 확인
        if(!findSchedule.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password.");
        }

        int updateRow = scheduleRepository.updateScheduleV2(id, requestDto);

        if(updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Schedule schedule = scheduleRepository.findOneScheduleV2(id);

        return new UpdateScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getName(),
                schedule.getModifiedAt()
        );
    }

    @Override
    public void deleteScheduleById(Long id, DeleteScheduleRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findOneSchedule(id);

        //Lv 5. 비밀번호 확인
        if(!findSchedule.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password.");
        }

        int deleteRow = scheduleRepository.deleteScheduleById(id);

        if(deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

    @Override
    public void deleteScheduleByIdV2(Long id, DeleteScheduleRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findOneScheduleV2(id);

        if(!findSchedule.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Password.");
        }

        int deleteRow = scheduleRepository.deleteScheduleByIdV2(id);

        if(deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

    @Override
    public List<FindScheduleResponseDto> findAllScheduleWithUserId(Long userId) {

        return scheduleRepository.findAllScheduleWithUserId(userId);
    }
}
