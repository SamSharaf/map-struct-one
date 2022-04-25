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
public class StudentDto implements Serializable {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
}
