package com.goormthon.client;

import static org.junit.jupiter.api.Assertions.*;

import com.goormthon.dto.request.PromptRequest;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class AiClientTest {

    @Autowired
    private AiClient aiClient;

    @Test
    void test() {
        PromptRequest request = new PromptRequest(
                """
                        앞에 '''json 없이 정확히 JSON STING 배열로 응답을 보내, 잘 모르겠으면 빈 배열을 반환해
                        제주시의 교육훈련 공고 중에 자격증 관련 공고를 관련도 순으로 title 배열로 반환해줘
                        """
        );
        List<String> anaylzedTitle = aiClient.getAnaylzedTitle(request);
        System.out.println(anaylzedTitle);
    }
}
