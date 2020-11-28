package com.pawel.wojtanka.bootcamp.factory;

import com.pawel.wojtanka.bootcamp.dto.StudentDto;
import com.pawel.wojtanka.bootcamp.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentDtoFactory {

    public StudentDto create(Student student) {
        return StudentDto.builder()
            .id(student.getId())
            .firstName(student.getFirstName())
            .lastName(student.getLastName())
            .phoneNumber(student.getPhoneNumber())
            .email(student.getEmail())
            .ratePerHour(student.getRatePerHour())
            .build();
    }

}
