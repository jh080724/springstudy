package com.study.springstudy.springmvc.chap04.dto.request;

import com.study.springstudy.springmvc.chap04.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@Builder
public class ReplyPostRequestDTO {
    /* Client js가 보낸 JSON을 받기 위한 클래스
       const payload = {
                text: textVal,
                author: writeVal,
                bno: bno,
                };
     */
    // NotNull:  null만 안됨, 공백은 됨.
    // NotBlank: null 안됨, 공백도 안됨.
    @NotBlank
    @Size(min = 1, max = 300)
    private String text;

    @NotBlank
    @Size(min = 2, max = 8)
    private String author;

    @NotNull
    private int bno;

    //dto를 entity로 바꾸는 변환 메서드
    public Reply toEntity(){
        return Reply.builder()
                .replyText(text)
                .replyWriter(author)
                .boardNo(bno)
                .build();
    }
}
