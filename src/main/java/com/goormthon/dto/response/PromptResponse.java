package com.goormthon.dto.response;

public record PromptResponse(
        DataResponse data
) {

    record DataResponse(
            ChatResponse chat
    ) {

    }

    record ChatResponse(
            String answer
    ) {

    }

    public String getAnswer() {
        return data.chat.answer;
    }
}
