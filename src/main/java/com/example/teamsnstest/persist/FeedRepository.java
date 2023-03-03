package com.example.teamsnstest.persist;

import com.example.teamsnstest.converter.ForTestResponse;
import com.example.teamsnstest.dto.ForTestResponseImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    Slice<Feed> findAllByUserIdOrderByCreatedAtDesc(Long authorId, PageRequest pageRequest);

    Integer countAllByUserId(Long userId);

    @Query(
        value =
            "SELECT"
            + " f.id as feedId,"
            + " f.content as content,"
            + " f.imageUrls as imageUrl,"
            + " u.username as username, "
            + " u.profileImageUrl as profileImageUrl,"
            + " u.nickname as nickname" +
        " FROM Feed f" +
        " JOIN User u" +
        " ON f.user.id = u.id"
    )
    Slice<ForTestResponse> findAllByJoinUser();

}

