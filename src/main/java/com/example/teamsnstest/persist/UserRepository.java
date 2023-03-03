package com.example.teamsnstest.persist;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Slice<User> findAllByIdIn(Collection<Long> idList, PageRequest pageRequest);

    List<User> findAllByIdIn(Collection<Long> id);

    Optional<User> findByUsername(String username);

}
