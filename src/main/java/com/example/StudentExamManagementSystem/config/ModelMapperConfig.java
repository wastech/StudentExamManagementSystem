package com.example.StudentExamManagementSystem.config;

import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.payload.ExamDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        // Mapping from ExamDTO to Exam
//        modelMapper.typeMap(ExamDTO.class, Exam.class).addMappings(mapper -> {
//            mapper.map(ExamDTO::getCourseId, (dest, v) -> dest.getCourse().setCourseId((Long) v));
//            mapper.map(ExamDTO::getUserId, (dest, v) -> dest.getUser().setUserId((Long) v));
//        });
//
//        // Mapping from Exam to ExamDTO
//        modelMapper.typeMap(Exam.class, ExamDTO.class).addMappings(mapper -> {
//            mapper.map(src -> src.getCourse().getCourseId(), ExamDTO::setCourseId);
//            mapper.map(src -> src.getUser().getUserId(), ExamDTO::setUserId);
//        });
//
//        return modelMapper;
//    }
}
