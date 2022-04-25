package com.walmart.sem.subject;

import com.walmart.sem.dto.SubjectDto;
import com.walmart.sem.mapper.SubjectMapper;
import com.walmart.sem.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSubjectMapper {

  @Autowired
  private SubjectMapper subjectMapper;

  @Test
  public void test1(){
    Subject subject = Subject.builder()
        .subjectName("subject name")
        .marksObtained(3.1)
        .build();
    SubjectDto subjectDto = subjectMapper.toDto(subject);
    System.out.println(">>>>>>>>>>>>>>>>> " + subjectDto + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
  }
}
