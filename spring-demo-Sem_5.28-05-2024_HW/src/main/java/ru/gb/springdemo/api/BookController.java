package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;
//import ru.gb.springdemo.repository_old.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/book")
//@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {

    private final BooksRepository booksRepository;

//    @Autowired
    public BookController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    /**
     * Метод получения списка всех книг
     * @return - список книг
     */
    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "get all books", description = "Загружает список всех книг, которые есть в системе")
//    @GetMapping(path = "/all")
    public List<Books> getBooks() {

        return booksRepository.findAll();
    }

    /**
     * Метод получения книги по ее id
     * @param id - id нужной книги
     * @return - книга
     */
//    @GetMapping("/{id}")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "book by id", description = "Выбор книги по ее id-номеру")
    public Books getBook(@PathVariable long id) {
        return booksRepository.findById(id).stream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод добавления новой книги
     * @return - список всех книг
     */
//    @PostMapping("/add")
    @RequestMapping(path = "add", method = RequestMethod.POST)
    @Operation(summary = "add book", description = "Добавление книги в хранилище")
    public Books addBook(@RequestBody Books books) {
        books.setId(books.getId() + 1);
        return booksRepository.save(books);
    }

    /**
     * Метод удаления книги по ее id
     * @param id - id удаляемой книги
     */
//    @DeleteMapping("/del/{id}")
    @RequestMapping(path = "/del/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "delete book", description = "Удаление книги из хранилища")
    public void deleteBook(@PathVariable long id) {
        booksRepository.deleteById(id);
        Books.maxId = booksRepository.queryMaxId();
    }
}
