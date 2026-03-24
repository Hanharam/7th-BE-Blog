    package com.example.demo.dto;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import lombok.Getter;

    import java.util.List;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    public class ErrorResponseDto {
        private int status;
        private String message;
        private List<ValidationErrorDto> validation;

        public ErrorResponseDto(int status,String message) {
            this.status = status;
            this.message = message;
        }

        public ErrorResponseDto(int status,String message,List validation) {
            this.status =status;
            this.message = message;
            this.validation =validation;
        }


        public record ValidationErrorDto (String field,String message) {}

    }
