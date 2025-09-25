package com.goormthon.controller;

import com.goormthon.dto.response.EducationInfoResponses;
import com.goormthon.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EducationInfoController {

    private final EducationInfoService educationInfoService;

    @GetMapping("/infos/me")
    public ResponseEntity<EducationInfoResponses> getEducationInfo(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "pageSize") long pageSize
    ) {
        EducationInfoResponses userInfos = educationInfoService.findUserInfos(userId, pageSize);
        return ResponseEntity.ok(userInfos);
    }
}
