package com.study.springstudy.springmvc.chap04.entity;

import lombok.*;

import java.time.LocalDateTime;

/*
    CREATE TABLE tbl_reply(
        reply_no int auto_increment,
        reply_text varchar(1000) not null,
        reply_writer varchar(100) not null,
        reply_date datetime default current_timestamp,
        board_tbl_replyno int,

        constraint pk_reply primary key(reply_no),
        constraint fk_reply foreign key(board_no)
        REFERENCES tbl_board(board_no)
        ON DELETE CASCADE
    );
 */

@Getter
@Setter // 보통 entity는 setter를 필요한 필드에만 직접 구현하는 편이다.
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    private int replyNo;
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private int boardNo;
}
