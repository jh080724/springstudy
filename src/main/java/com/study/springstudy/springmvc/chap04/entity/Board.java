package com.study.springstudy.springmvc.chap04.entity;
/*
CREATE TABLE tbl_board(
	board_no INT PRIMARY KEY auto_increment,
    title varchar(100) not null,
    content varchar(2000),
    view_count int default 0,
    reg_date datetime default current_timestamp,
    writer varchar(50) not null
);
 */

import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    private int boardNo;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private String writer;

    public Board(BoardWriteRequestDTO dto) {
        this.writer = dto.getWriter();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
