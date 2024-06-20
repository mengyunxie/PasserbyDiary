package com.passerby.diaryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<R> {
    private Integer code; // Response code: 1 success; 0 fail
    private String msg;   // Response message
    private R data;       // Response Data

    public static <R> Result<R> success() {
        return new Result<>(1, "success", null);
    }

    public static <R> Result<R> success(R data) {
        return new Result<>(1, "success", data);
    }

    public static <R> Result<R> error(String msg) {
        return new Result<>(0, msg, null);
    }

    public static <R> Result<R> error(R data) {
        return new Result<>(0, "error", data);
    }

    public static <R> Result<R> error(String msg, R data) {
        return new Result<>(0, msg, data);
    }
}
