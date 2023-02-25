package com.example.teamsnstest.service;

import com.example.teamsnstest.persist.ParentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParentDeleteService {
    private final ParentRepository parentRepository;
    private final ChildDeleteService childDeleteService;
    @Transactional
    public void deleteParent(){
        System.out.println("부모 엔티티 삭제 호출 시작");
            // 1번 제거
            parentRepository.deleteById(1L);
    }

}
