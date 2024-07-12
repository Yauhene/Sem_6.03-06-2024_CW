package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {

  // спринг это все заинжектит
  private final BooksRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    Long readerDebt = issueRepository.queryIssueByReaderId(request.getReaderId());

    if (bookRepository.findById(request.getBookId()) == null) {
      System.out.println("Книги с id = " + request.getBookId() + " в нашей библиотеке нет. Заходите на неделе.");
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.findById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    if (readerDebt != null) {
      Optional<Issue> iss = issueRepository.findById(readerDebt);
      Long bookId = iss.get().getBookId();
      String bName = bookRepository.findById(bookId).toString();
      String debtDescription = "Долг читателя: книга \"" + bName + "\", перебьется пока.";

      log.info(debtDescription);
      throw new NoSuchElementException("Должок !!! \"" + bName + "\"");
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issue.setId(issue.getId() + 1);
    issueRepository.save(issue);

    return issue;
  }

}
