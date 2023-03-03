package com.example.teamsnstest.converter;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface ForTestResponse {

    //private Long feedId;
    Long getFeedId();

    //private Long userId;
    Long getUserId();

    //private LocalDateTime createdAt;
    LocalDateTime getCreatedAt();

    //private LocalDateTime modifiedAt;
    LocalDateTime getModifiedAt();

    //private String content;
    String getContent();

    //private String imageUrls;
    String getImageUrls();

    //private String username;
    String getUsername();

    //private String profileImageUrl;
    String getProfileImageUrl();

    //private String nickname;
    String getNickname();

}
