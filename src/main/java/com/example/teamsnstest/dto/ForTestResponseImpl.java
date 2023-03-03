package com.example.teamsnstest.dto;

import com.example.teamsnstest.converter.ForTestResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ForTestResponseImpl {

    private Long feedId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String content;
    private String imageUrls;
    private String username;
    private String profileImageUrl;
    private String nickname;

}
