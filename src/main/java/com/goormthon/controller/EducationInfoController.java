package com.goormthon.controller;

import com.goormthon.domain.Education;
import com.goormthon.domain.Interest;
import com.goormthon.domain.Region;
import com.goormthon.domain.Residency;
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
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "pageSize") long pageSize
    ) {
        EducationInfoResponses userInfos = educationInfoService.findUserInfos(userId, pageSize);
        return ResponseEntity.ok(userInfos);
    }

    @GetMapping("/infos")
    public ResponseEntity<EducationInfoResponses> getEducationInfo(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "education", required = false) Education education,
            @RequestParam(name = "region", required = false) Region region,
            @RequestParam(name = "residency", required = false)Residency residency,
            @RequestParam(name = "interest", required = false) Interest interest
    ) {
        EducationInfoResponses userInfos = educationInfoService.search(search, education, region, residency, interest);
        return ResponseEntity.ok(userInfos);
    }
}
