package com.example.teamsnstest.application;

import com.example.teamsnstest.service.ChildDeleteService;
import com.example.teamsnstest.service.ParentDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteApplication {

    private final ParentDeleteService parentDeleteService;
    private final ChildDeleteService childDeleteService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteApp(){
        parentDeleteService.deleteParent();
        childDeleteService.deleteChild(1L);
    }

}
