package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.media.*;
import lombok.Data;

import java.time.*;

/**
 * Запрос на выдачу
 */
@Data
@Schema(name = "Запрос на выдачу")
public class IssueRequest {

  /**
   * Идентификатор книги
   */
  @Schema(name = "Код книги")
  private long bookId;

  /**
   * Идентификатор читателя
   */
  @Schema(name = "Код читателя")
  private long readerId;

  /**
   * Идентификатор времени выдачи
   */
  @Schema(name = "Дата и время выдачи")
  private LocalDateTime issueTimeStamp = LocalDateTime.now();
}
