package com.walmart.sem.controller;

import com.walmart.sem.dto.SubjectDto;
import com.walmart.sem.mapper.SubjectMapper;
import com.walmart.sem.model.Subject;
import com.walmart.sem.service.SubjectService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/subject")
@RestController
@Slf4j
public class SubjectController {
  private final SubjectService subjectService;

  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody @Validated SubjectDto subjectDto) {
    subjectService.save(subjectDto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubjectDto> findById(@PathVariable("id") Integer id) {
    SubjectDto subject = subjectService.findById(id);
    return ResponseEntity.ok(subject);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    subjectService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/page-query")
  public ResponseEntity<Page<SubjectDto>> pageQuery(SubjectDto subjectDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
    Page<SubjectDto> subjectPage = subjectService.findByCondition(subjectDto, pageable);
    return ResponseEntity.ok(subjectPage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody @Validated SubjectDto subjectDto, @PathVariable("id") Integer id) {
    subjectService.update(subjectDto, id);
    return ResponseEntity.ok().build();
  }
}