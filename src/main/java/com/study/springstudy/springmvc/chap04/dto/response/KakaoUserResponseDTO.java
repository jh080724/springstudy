package com.study.springstudy.springmvc.chap04.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserResponseDTO {
    private long id;

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

}
