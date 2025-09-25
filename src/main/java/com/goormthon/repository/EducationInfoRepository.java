package com.goormthon.repository;

import com.goormthon.domain.Education;
import com.goormthon.domain.EducationInfos;
import com.goormthon.domain.Interest;
import com.goormthon.domain.Region;
import com.goormthon.domain.Residency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EducationInfoRepository extends JpaRepository<EducationInfos, Long> {

    List<EducationInfos> findAllByIdIn(List<Long> selectedInfos);


    @Query("""
                select educationInfo
                    from EducationInfos educationInfo
                    where (:region is null or educationInfo.region = :region)
                         and (:education is null or educationInfo.education = :education)
                         and (:interest is null or educationInfo.interest = :interest)
                         and (:residency is null or educationInfo.residency = :residency)
                    order by educationInfo.deadline desc
            """)
    List<EducationInfos> findByConditions(
            Region region,
            Education education,
            Interest interest,
            Residency residency
    );
}
