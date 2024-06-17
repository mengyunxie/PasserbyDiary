package com.passerby.diaryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//Response code，1 success; 0 fail
    private String msg;  //Response message
    private Object data; //Response Data

    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
    public static Result error(Object data){
        return new Result(0,"error",data);
    }
    public static Result error(String msg, Object data){
        return new Result(0,msg,data);
    }
}
