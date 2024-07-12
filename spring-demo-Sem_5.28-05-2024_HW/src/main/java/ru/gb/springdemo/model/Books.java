package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "books")
@Data
@Schema(name = "Книги")
public class Books {

  public static long maxId = 1;
  @Id
  @Schema(name = "Идентификатор")
  private long id;

  @Column(name = "name")
  @Schema(name = "Название книги")
  private String name;

  public Books() {

    this.id = maxId;
  }
}
