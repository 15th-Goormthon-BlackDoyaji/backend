package com.goormthon.repository;

import com.goormthon.domain.Education;
import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Interest;
import com.goormthon.domain.Region;
import com.goormthon.domain.Residency;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EducationInfoRepository extends JpaRepository<EducationInfos, Long> {

    List<EducationInfos> findAllByIdIn(List<Long> selectedInfos);


    @Query("""
                select educationInfo
                    from EducationInfos educationInfo
                    where (:region is null or educationInfo.region = :region or educationInfo.region is null)
                         and (:education is null or educationInfo.education = :education or educationInfo.education is null)
                         and (:interest is null or educationInfo.interest = :interest or educationInfo.interest is null)
                         and (:residency is null or educationInfo.residency = :residency or educationInfo.residency is null)
                    order by educationInfo.deadline desc
                    limit :pageSize
            """)
    List<EducationInfos> findByConditions(
            Region region,
            Education education,
            Interest interest,
            Residency residency,
            long pageSize
    );

    Optional<EducationInfos> findByTitleIsContaining(String title);
}
