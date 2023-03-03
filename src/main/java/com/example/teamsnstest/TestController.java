package com.example.teamsnstest;

import com.example.teamsnstest.common.UserStatusType;
import com.example.teamsnstest.dto.TestFeedResponse;
import com.example.teamsnstest.persist.Feed;
import com.example.teamsnstest.persist.FeedRepository;
import com.example.teamsnstest.persist.User;
import com.example.teamsnstest.persist.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    @GetMapping("/run")
    @Transactional
    public void run() throws InterruptedException {

        // 유저를 3명 만든다.
        User user = User.builder()
            .userStatus(UserStatusType.ACTIVE)
            .username("박동빈")
            .nickname("dongvin99")
            .profileImageUrl("프로필이미지 url")
            .build();

        User user2 = User.builder()
            .userStatus(UserStatusType.ACTIVE)
            .username("박동빈2")
            .nickname("dongvin22")
            .profileImageUrl("프로필이미지 url")
            .build();

        User user3 = User.builder()
            .userStatus(UserStatusType.ACTIVE)
            .username("박동빈3")
            .nickname("dongvin33")
            .profileImageUrl("프로필이미지 url")
            .build();

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        System.out.println("유저 3명 세이브 완료.");

        // 게시물을 2개 만든다. 이때 1번 유저를 넣어서 양방향 매핑을 활용한다.
        // 두 번째 게시물은 10초의 시간을 두고 만든다.
        Feed feed = Feed.builder()
            .content("게시물 내용")
            .user(user)
            .imageUrls("이미지1,이미지2")
            .build();

        feedRepository.save(feed);

        Thread.sleep(1000*10);

        Feed feed2 = Feed.builder()
            .content("게시물 내용2")
            .user(user2)
            .imageUrls("이미지1,이미지2")
            .build();

        Feed feed3 = Feed.builder()
            .content("게시물 내용3")
            .user(user3)
            .imageUrls("이미지1,이미지2")
            .build();

        feedRepository.save(feed2);
        feedRepository.save(feed3);

        System.out.println("게시물 세이브 완료.");

    }// func

    @GetMapping("/read")
    public List<TestFeedResponse> makeResponse(){
        // 게시물 엔티티를 불러와서 리스펀스를 만들 때, 유저도 같이 참조하게 만든다.
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("id").descending());

        List<Long> feedIdList = new ArrayList<>();
        feedIdList.add(2L);

        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusSeconds(5);

        return feedRepository.findAllByJoinUserWithTimeDurationAndFeedIdListAndPaging(
            feedIdList, from, to, pageRequest
            ).getContent().stream().map(TestFeedResponse::secondFrom)
            .collect(Collectors.toList());
    }

}
