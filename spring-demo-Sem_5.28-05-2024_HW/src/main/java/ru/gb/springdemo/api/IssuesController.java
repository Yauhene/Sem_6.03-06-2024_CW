package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
//import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;
import ru.gb.springdemo.service.IssueService;

import java.time.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/issue")
@Tag(name = "Issue")
public class IssuesController {
  private final IssueRepository issueRepository;

  @Autowired
  public IssuesController(IssueRepository issueRepository) {
    this.issueRepository = issueRepository;
  }

  @Autowired
  private IssueService service;

//  @PostMapping("/add")
  @RequestMapping(path = "/add", method = RequestMethod.POST)
  @Operation(summary = "add issue", description = "Добавление выдачи в список выдач")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу:  bookId = {}, readerId = {}, issueTimestamp = {}",  request.getBookId(), request.getReaderId(), LocalDateTime.now());
    Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(issue);
  }

//  @GetMapping(path = "/all")
  @RequestMapping(path = "/all", method = RequestMethod.GET)
  @Operation(summary = "all issues", description = "Вывод списка всех выдач")
  public List<Issue> getIssues() {

    return List.copyOf(issueRepository.findAll());
  }

//  @GetMapping("/{id}")
  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  @Operation(summary = "issue by id", description = "Вывод выдачи по ее id-номеру")
    public List<Issue> getById(@PathVariable long id) {
      return issueRepository.findById(id);
    }

  /**
   * Метод удаления записи о выдаче по ее id
   * @param id - id удаляемой записи
   */
//  @DeleteMapping("/del/{id}")
  @RequestMapping(path = "/del/{id}", method = RequestMethod.DELETE)
  @Operation(summary = "delete issue by id", description = "Удаление записи о выдаче по ее id-номеру")
  public void deleteById(@PathVariable long id) {
    issueRepository.deleteById(id);
    Issue.maxId = issueRepository.queryIssuesMaxId();
  }
}
