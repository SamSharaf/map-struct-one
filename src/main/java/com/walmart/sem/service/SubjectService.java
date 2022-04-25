package com.walmart.sem.service;

import com.walmart.sem.dto.SubjectDto;
import com.walmart.sem.mapper.SubjectMapper;
import com.walmart.sem.model.Subject;
import com.walmart.sem.repository.SubjectRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class SubjectService {
  private final SubjectRepository repository;
  private final SubjectMapper subjectMapper;

  public SubjectService(SubjectRepository repository, SubjectMapper subjectMapper) {
    this.repository = repository;
    this.subjectMapper = subjectMapper;
  }

  public SubjectDto save(SubjectDto subjectDto) {
    Subject entity = subjectMapper.toEntity(subjectDto);
    return subjectMapper.toDto(repository.save(entity));
  }

  public void deleteById(Integer id) {
    repository.deleteById(id);
  }

  public SubjectDto findById(Integer id) {
    return subjectMapper.toDto(repository.findById(id).orElseThrow(RuntimeException::new));
  }

  public Page<SubjectDto> findByCondition(SubjectDto subjectDto, Pageable pageable) {
    Page<Subject> entityPage = repository.findAll(pageable);
    List<Subject> entities = entityPage.getContent();
    return new PageImpl<>(subjectMapper.toDto(entities), pageable, entityPage.getTotalElements());
  }

  public SubjectDto update(SubjectDto subjectDto, Integer id) {
    SubjectDto originalDto = findById(id);
    Subject updatingEntity = subjectMapper.toEntity(subjectDto);
//    BeanUtil.copyProperties(originalDto, updatingEntity);
    // Copy from entity to data
    Subject subject = Subject.builder()
        .subjectName(originalDto.getSubjectName())
        .id(id)
        .marksObtained(originalDto.getMarksObtained())
        .student(originalDto.getStudent())
        .build();
    if (Objects.isNull(updatingEntity.getSubjectName())){
      subject.setSubjectName(updatingEntity.getSubjectName());
    }
    if (Objects.isNull(updatingEntity.getStudent())){
      subject.setStudent(updatingEntity.getStudent());
    }
    if (Objects.isNull(updatingEntity.getMarksObtained())){
      subject.setMarksObtained(updatingEntity.getMarksObtained());
    }
    return save(subjectMapper.toDto(updatingEntity));
  }
}