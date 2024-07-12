package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */

@Entity
@Table(name = "issues")
@Data
@Schema(name = "Выдачи")
public class Issue {

  public static long maxId = 1L;

  @Id
  @Schema(name = "Код выдачи")
  private long id;

  @Column(name = "book_id")
  @Schema(name = "Код книги")
  private long bookId;

  @Column(name = "reader_id")
  @Schema(name = "Код читателя")
  private long readerId;

  /**
   * Дата выдачи
   */
  @Column(name = "issue_time")
  @Schema(name = "Дата выдачи")
  private LocalDateTime issueTimestamp;
  /**
   * Дата возврата
   */
  @Column(name = "return_time")
  @Schema(name = "Дата возврата")
  private LocalDateTime returnTimestamp;

  public Issue(long bookId, long readerId) {

    this.id = maxId;
    this.bookId = bookId;
    this.readerId = readerId;
    this.setIssueTimestamp(LocalDateTime.now());
  }

  public Issue() {

  }

  public void setReturnTimestamp() {

    this.setReturnTimestamp(LocalDateTime.now());
  }

  private void setReturnTimestamp(LocalDateTime now) {
  }

  public long getId() {
    return id;
  }

  public long getBookId() {
    return bookId;
  }

  public long getReaderId() {
    return readerId;
  }

  public LocalDateTime getIssueTimestamp() {
    return issueTimestamp;
  }

  public LocalDateTime getReturnTimestamp() {
    return returnTimestamp;
  }

}
