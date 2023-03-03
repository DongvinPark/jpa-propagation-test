package com.example.teamsnstest.persist;

import com.example.teamsnstest.converter.ForTestResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
                + " u.nickname as nickname"
                + " FROM Feed f"
                + " JOIN User u"
                + " ON f.user.id = u.id"
    )
    Slice<ForTestResponse> findAllByJoinWithNoPaging(
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to
    );

    @Query(
        value =
            "SELECT"
            + " f.id as feedId,"
            + " f.content as content,"
            + " f.imageUrls as imageUrl,"
            + " u.username as username, "
            + " u.profileImageUrl as profileImageUrl,"
            + " u.nickname as nickname"
                + " FROM Feed f"
                + " JOIN User u"
                + " ON f.user.id = u.id"
        + " WHERE f.createdAt >= :from "
        + " AND f.createdAt <= :to "
    )
    Slice<ForTestResponse> findAllByJoinUserWithTimeDurationAndPaging(
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to,
        PageRequest pageRequest
    );

    @Query(
        value =
            "SELECT"
                + " f.id as feedId,"
                + " f.content as content,"
                + " f.imageUrls as imageUrl,"
                + " u.username as username, "
                + " u.profileImageUrl as profileImageUrl,"
                + " u.nickname as nickname"
                + " FROM Feed f"
                + " JOIN User u"
                + " ON f.user.id = u.id"
                + " WHERE f.id in (:feedIdList) "
                + " AND f.createdAt >= :from "
                + " AND f.createdAt <= :to "
    )
    Slice<ForTestResponse> findAllByJoinUserWithTimeDurationAndFeedIdListAndPaging(
        @Param("feedIdList") List<Long> feedIdList,
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to,
        PageRequest pageRequest
    );

}

