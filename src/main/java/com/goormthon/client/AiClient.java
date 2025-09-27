package com.goormthon.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goormthon.dto.request.PromptRequest;
import com.goormthon.dto.response.PromptResponse;
import com.goormthon.exception.custom.GroomServerErrorException;
import com.goormthon.exception.errorcode.ServerErrorCode;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class AiClient {

    private final RestClient restClient;

    public AiClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public List<String> getAnaylzedTitle(PromptRequest request) {
        log.info("request: {} ", request);

        PromptResponse response = restClient.post()
                .uri("https://live-stargate.sionic.im/api/v2/answer")
                .header("storm-api-key", "")
                .body(request)
                .retrieve()
                .body(PromptResponse.class);
        log.info("response: {} ", response);
        return mapToRseponse(response.getAnswer());
    }

    private List<String> mapToRseponse(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("response: {} ", response);
            throw new GroomServerErrorException(ServerErrorCode.RESPONSE_PARSING_ERROR);
        }
    }
}
