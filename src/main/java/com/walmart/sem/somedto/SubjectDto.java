package com.walmart.sem.somedto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto implements Serializable {
  private Integer id;
  private StudentDto student;
  private String subjectName;
  private Double marksObtained;
}
