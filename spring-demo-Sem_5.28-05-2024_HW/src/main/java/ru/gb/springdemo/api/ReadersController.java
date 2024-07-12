package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/reader")
@Tag(name = "Readers")
public class ReadersController {
    private ReaderRepository readerRepository;
    private IssueRepository issueRepository;

    @Autowired
    public void ReaderController(ReaderRepository readerRepository) {

        this.readerRepository = readerRepository;
    }

    public ReadersController() {
    }

//    @GetMapping("/{id}")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "reader by id", description = "Вывод инфо о читателе по его id-номеру")
    public List<Readers> getReader(@PathVariable long id) {
        return readerRepository.findById(id);
    }

    /**
     * Метод получения списка всех читателей
     * @return - список книг
     */
//    @GetMapping(path = "/all")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @Operation(summary = "all readers", description = "Вывод списка всех читателей")
    public List<Readers> getAllReaders() {
        return readerRepository.findAll();
    }

    /**
     * Метод добавления нового читателя
     * @return - список всех читателей
     */
    // восстановить в новых условиях
//    @PostMapping("/add")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @Operation(summary = "add reader", description = "Добавление читателя в список читателей")
    public Readers addReader(@RequestBody Readers readers) {
        readers.setId(readers.getId() + 1);
        Readers.maxId = readerRepository.queryMaxId() + 1;
        return readerRepository.save(readers);
    }

    /**
     * Метод удаления читателя по его id
     * @param id - id удаляемой книги
     */
//    @DeleteMapping("/del/{id}")
    @RequestMapping(path = "/del/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "delete reader by id", description = "Удаление записи о читателе по его id-номеру")
    public void deleteReader(@PathVariable long id) {
        readerRepository.deleteById(id);
        Readers.maxId = readerRepository.queryMaxId();
    }
}
