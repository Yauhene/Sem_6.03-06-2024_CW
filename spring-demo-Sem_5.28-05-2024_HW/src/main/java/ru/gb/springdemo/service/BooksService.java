package ru.gb.springdemo.service;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;

@Service
@RequiredArgsConstructor
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;


    public Books saveBooks(Books books) {
        return booksRepository.save(books);
    }
}
