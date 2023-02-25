package com.example.teamsnstest.controller;

import com.example.teamsnstest.application.DeleteApplication;
import com.example.teamsnstest.persist.ChildEntity;
import com.example.teamsnstest.persist.ChildRepository;
import com.example.teamsnstest.persist.ParentEntity;
import com.example.teamsnstest.persist.ParentRepository;
import com.example.teamsnstest.service.ParentDeleteService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ParentDeleteService parentDeleteService;
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final DeleteApplication deleteApplication;

    @GetMapping("/run")
    public void run(){
        // 부모 엔티티 5개와 자식 엔티티 5개를 저장한다.
        for(int i=1; i<=5; i++){
            parentRepository.save(
                ParentEntity.builder()
                    .build()
            );
        }

        for(int i=1; i<=5; i++){
            childRepository.save(
                ChildEntity.builder()
                    .build()
            );
        }

        System.out.println("부모 및 자식 엔티티 각각 1~5번 까지 저장 완료.");;
        deleteApplication.deleteApp();

        // 그 후 부모 엔티티를 삭제 서비스를 호출할 때 자신과 주키 값이 같은 자식 엔티티도 삭제하는 서비스를
        // 내부적으로 호출하게 만든다.
        // 그러고나서 예외를 발생시켜본다. 자식 엔티티 삭제 전 , 자식 엔티티 삭제 중, 자식 엔티티 삭제 후 이렇게.
        // JPA 전파단계중 4종류에 대해서 이렇게 3가지 테스트를 진행해보고 로그를 찍어본다.
    }

}
