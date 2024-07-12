package ru.gb.springdemo.model;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Schema(name = "Читатели")
public class Readers {

  public static long maxId = 1L;

  @Id
  @Schema(name = "Код читателя")
  private long id;

  @Getter
  @Column(name = "name")
  @Schema(name = "Имя читателя")
  private String name;

  public Readers() {

    this.id = maxId;
  }

}
