package ru.gb.springdemo.service;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;

@Service
@RequiredArgsConstructor
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;


    public Readers saveReaders(Readers readers) {

        return readerRepository.save(readers);
    }
}
