package com.goormthon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "education_infos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String summary;

    @Enumerated(value= EnumType.STRING)
    private Education education;

    @Enumerated(value= EnumType.STRING)
    private Region region;

    @Enumerated(value= EnumType.STRING)
    private Residency residency;

    @Enumerated(value= EnumType.STRING)
    private Interest interest;

    @NotNull
    private LocalDate deadline;

    @NotBlank
    private String url;
}
