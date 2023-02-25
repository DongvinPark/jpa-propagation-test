package com.example.teamsnstest;

import com.example.teamsnstest.persist.TestEntity;
import com.example.teamsnstest.persist.TestRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;

    @DeleteMapping("/void-test")
    public void test(){
        System.out.println("삭제 작업 진행");
    }


    @GetMapping("/date-test")
    public ResponseEntity<LocalDateTime> dateTest(){
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok(now);
    }


    @GetMapping("/run")
    public void run(){
        for(int i = 1; i<=5000; i++){
            if(i<= 20){
                testRepository.save(
                    TestEntity.builder()
                        .testString(String.valueOf(i))
                        .specialValue("special")
                        .build()
                );
            } else if (i >= 4900) {
                testRepository.save(
                    TestEntity.builder()
                        .testString(String.valueOf(i))
                        .specialValue("special")
                        .build()
                );
            } else {
                testRepository.save(
                    TestEntity.builder().testString(String.valueOf(i)).build()
                );
            }
        }

        PageRequest pageRequest = PageRequest.of(0, 20);

        // 상위 1000개 중 1페이지 20개를 뒤져본다. 시간 측정한다.
        long start = System.currentTimeMillis();
        List<TestEntity> upper20 = testRepository.findAllBySpecialValue("special", pageRequest).getContent();
        long end = System.currentTimeMillis();
        System.out.println("상위 20개 시간 밀리초 Asc 정렬 없이 : " + (end-start));
        for(TestEntity entity : upper20){
            System.out.println(entity.getTestString() + "/" + entity.getCreatedAt());
        }

        // 하위 1000개 중 1페이지 20개를 뒤져본다. 시간 측정한다.
        start = System.currentTimeMillis();
        List<TestEntity> lower20 = testRepository.findAllByOrderByCreatedAtDesc(pageRequest).getContent();
        end = System.currentTimeMillis();
        System.out.println("하위 20개 시간 밀리초 : " + (end-start));
        for(TestEntity entity : lower20){
            System.out.println(entity.getTestString() + "/" + entity.getCreatedAt());
        }

        // 조건을 만족하는 것들 중 1페이지 20개를 뒤져본다. 시간 측정한다.
        start = System.currentTimeMillis();
        List<TestEntity> specialLower20 = testRepository
            .findAllBySpecialValueOrderByCreatedAtDesc("special", pageRequest)
            .getContent();
        end = System.currentTimeMillis();
        System.out.println("특수 조건 만족하는 하위 20개 시간 밀리초 : " + (end-start));
        for(TestEntity entity : specialLower20){
            System.out.println(entity.getTestString() + "/" + entity.getSpecialValue() + "/" + entity.getCreatedAt());
        }
    }

}
