package com.goormthon.dto.request;

import com.goormthon.domain.Education;
import com.goormthon.domain.Interest;
import com.goormthon.domain.Region;
import com.goormthon.domain.Residency;
import jakarta.validation.constraints.Email;

public record SubscribeRequest(
        @Email(message = "이메일 형식에 어긋납니다")
        String email,
        Education education,
        Region region,
        Residency residency,
        Interest interest
) {

}
