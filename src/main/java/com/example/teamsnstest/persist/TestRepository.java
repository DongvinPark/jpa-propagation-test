package com.example.teamsnstest.persist;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    Page<TestEntity> findAllByTestNumberIn(Collection<Long> testNumber, Pageable pageable);

    Optional<TestEntity> findByTestString(String testString);

    Slice<TestEntity> findAllByOrderByCreatedAtAsc(PageRequest pageRequest);

    Slice<TestEntity> findAllByOrderByCreatedAtDesc(PageRequest pageRequest);

    Slice<TestEntity> findAllBySpecialValue(String specialValue, PageRequest pageRequest);

    Slice<TestEntity> findAllBySpecialValueOrderByCreatedAtDesc(String specialValue, PageRequest pageRequest);

}
