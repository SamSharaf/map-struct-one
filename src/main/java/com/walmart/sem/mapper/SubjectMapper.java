package com.walmart.sem.mapper;

import com.walmart.sem.dto.SubjectDto;
import com.walmart.sem.model.Subject;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends EntityMapper<SubjectDto, Subject> {

  @Override
  Subject toEntity(SubjectDto dto);

  @Override
  SubjectDto toDto(Subject entity);

  @Override
  List<Subject> toEntity(List<SubjectDto> dtoList);

  @Override
  List<SubjectDto> toDto(List<Subject> entityList);

  @Override
  Set<SubjectDto> toDto(Set<Subject> entityList);
}