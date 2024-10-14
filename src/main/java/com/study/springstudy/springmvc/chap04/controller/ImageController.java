package com.study.springstudy.springmvc.chap04.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController  // 비동기 방식
@RequestMapping("/display")
@Slf4j
public class ImageController {
    @Value("${file.upload.root-path}")
    public String rootPath;
    /*
    # img 태그의 src속성을 통해서 들어오는 요청을 처리
    페이지가 랜더링 될때 img에 작성된 요청 url을 통해 비동기 방식의 요청이 들어옵니다.
     */

    @GetMapping("c:/develop/upload")
    public ResponseEntity<?> getImage(HttpServletRequest request) {
        String uri = request.getRequestURI();
        File file = new File(uri);
        ResponseEntity<byte[]> result = null;

        HttpHeaders headers = new HttpHeaders(); // 응답용 헤더 객체 생성
        try {
            headers.add("Content-type", Files.probeContentType(file.toPath()));
            result // 전달하고자 하는 파일을 카피한 후 바이트 배열로 변환해서 전달.
                    = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

//        uri = uri.substring(uri.lastIndexOf("/"));
//        log.info("정제된 uri: {}", uri);

        return result;

    }
}
