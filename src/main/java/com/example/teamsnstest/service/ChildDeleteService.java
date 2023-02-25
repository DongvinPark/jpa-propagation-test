package com.example.teamsnstest.service;

import com.example.teamsnstest.persist.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChildDeleteService {

    private final ChildRepository childRepository;

    @Transactional
    public void deleteChild(Long id){
            childRepository.deleteById(id);
        throw new RuntimeException("자식 엔티티 삭제 후 예외 발생!!");
    }

}
