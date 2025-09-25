package com.goormthon.etc;

import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Education;
import com.goormthon.domain.Region;
import com.goormthon.domain.Residency;
import com.goormthon.domain.Interest;
import com.goormthon.repository.EducationInfoRepository;
import java.time.LocalDate;
import java.util.Random;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EducationInfosDataInitializer {

    private final EducationInfoRepository repository;
    private final Random random = new Random();

    public EducationInfosDataInitializer(EducationInfoRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        long count = repository.count();
        if (count > 0) {
            return; // 이미 데이터 있으면 초기화 안 함
        }

        int total = 10000;

        Education[] educations = Education.values();
        Region[] regions = Region.values();
        Residency[] residencies = Residency.values();
        Interest[] interests = Interest.values();

        for (int i = 1; i <= total; i++) {
            EducationInfos info = new EducationInfos(
                    null,
                    "교육 제목 " + i,
                    "교육 요약 " + i + "\n상세 설명 포함",
                    educations[i % educations.length], // 균등 분포
                    regions[i % regions.length],       // 균등 분포
                    residencies[i % residencies.length], // 균등 분포
                    interests[i % interests.length],     // 균등 분포
                    LocalDate.now().plusDays(random.nextInt(180)), // 마감일 랜덤
                    "https://example.com/education/" + i
            );

            repository.save(info);

            if (i % 1000 == 0) {
                System.out.println(i + "개 데이터 생성 완료");
            }
        }
    }
}
