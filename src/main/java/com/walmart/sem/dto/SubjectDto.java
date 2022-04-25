package com.walmart.sem.dto;

import com.walmart.sem.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto extends AbstractDto<Integer> {
  private Integer id;
  private Student student;
  private String subjectName;
  private Double marksObtained;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Student getStudent() {
    return this.student;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

  public String getSubjectName() {
    return this.subjectName;
  }

  public void setMarksObtained(Double marksObtained) {
    this.marksObtained = marksObtained;
  }

  public Double getMarksObtained() {
    return this.marksObtained;
  }
}