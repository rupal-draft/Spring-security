package com.springsecurity.Spring.Security.controller;

import com.springsecurity.Spring.Security.dto.FlatDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flats")
public class FlatController {
    @GetMapping
    @Secured("{ROLE_ADMIN,ROLE_BUYER}")
    public FlatDto getTestFlat(){
        return new FlatDto("Blue Heaven","AnandaNagar",100);
    }
    @Secured("{ROLE_ADMIN,ROLE_SELLER}")
    @PostMapping
    public FlatDto createTestFlat(){
        return new FlatDto("Blue Heaven","AnandaNagar",100);
    }
    @DeleteMapping
    @PreAuthorize("hasAnyRoles(ADMIN,SELLER)")
    public Boolean deleteTestFlat(){
        return true;
    }
    @Secured("{ROLE_ADMIN,ROLE_SELLER}")
    @PutMapping
    public FlatDto updateTestFlat(){
        return new FlatDto("Twilight","AnandaNagar",200);
    }
}
