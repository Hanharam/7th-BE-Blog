package com.leets.blog.repeat.dto.response;

public record RepeatResponse(
        String string_one,
        String string_two
) {
    public static RepeatResponse from(String value) {
        return new RepeatResponse(
                value,
                value
        );
    }
}

