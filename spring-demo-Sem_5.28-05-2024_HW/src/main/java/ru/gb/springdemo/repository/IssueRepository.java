package ru.gb.springdemo.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import org.yaml.snakeyaml.tokens.*;
import ru.gb.springdemo.model.*;

import java.util.*;

public interface IssueRepository  extends JpaRepository<Issue, Long>, PagingAndSortingRepository<Issue, Long> {

    static long maxId = 1;

    @Query("select max(i.id) from Issue i")
    Long queryIssuesMaxId();

    @Query("select i.id from Issue i where (i.readerId = :id) and (i.returnTimestamp is null)")
    Long queryIssueByReaderId(@Param("id") long id);

    void deleteById(Long id);

    Issue save(Issue issues);

    List<Issue> findById(long id);
}
