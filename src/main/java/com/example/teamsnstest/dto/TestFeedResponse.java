package com.example.teamsnstest.dto;


import com.example.teamsnstest.converter.ForTestResponse;
import com.example.teamsnstest.persist.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestFeedResponse {

    private Long feedId;

    private String content;
    private String imageUrls;

    private String authorUsername;
    private String authorProfileImageUrl;
    private String authorNickname;
    //private Integer numberOfFeedWrittenByAuthor;
    //private List<Long> feedIdList;

    public static TestFeedResponse from(Feed feed){

        System.out.println("테스트 피드리스펀스 빌드 시작!!");

        return TestFeedResponse.builder()
            .feedId(feed.getId())
            .content(feed.getContent())
            .imageUrls(feed.getImageUrls())
            .authorUsername(feed.getUser().getUsername())
            .authorProfileImageUrl(feed.getUser().getProfileImageUrl())
            .authorNickname(feed.getUser().getNickname())
            //.numberOfFeedWrittenByAuthor(feed.getUser().getFeedList().size())
            /*.feedIdList(
                feed.getUser().getFeedList().stream().map(
                    Feed::getId
                ).collect(Collectors.toList())
            )*/
            .build();
    }

    public static TestFeedResponse secondFrom(ForTestResponse testResponse){

        System.out.println("테스트 피드리스펀스 빌드 시작!!");

        return TestFeedResponse.builder()
            .feedId(testResponse.getFeedId())
            .content(testResponse.getContent())
            .imageUrls(testResponse.getImageUrls())
            .authorUsername(testResponse.getUsername())
            .authorProfileImageUrl(testResponse.getProfileImageUrl())
            .authorNickname(testResponse.getNickname())
            //.numberOfFeedWrittenByAuthor(feed.getUser().getFeedList().size())
            /*.feedIdList(
                feed.getUser().getFeedList().stream().map(
                    Feed::getId
                ).collect(Collectors.toList())
            )*/
            .build();
    }
}
