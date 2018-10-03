package com.coin.shortline.util;


import java.util.HashMap;
import java.util.Map;

public class ResultFactory {
    public static <T> Result<T> getErrorResult(T data) {
        Result model = new Result();
        model.setMsg(CommonFinal.FAIL);
        model.setResultStatus(CommonFinal.RESULT_CODE_FAILURE);
        model.setResultData(data);
        return model;
    }

    public static <T>  Result<T> getErrorResult(Integer code, T data) {
        Result model = new Result();
        model.setMsg(CommonFinal.FAIL);
        model.setResultStatus(code);
        model.setResultData(data);
        return model;
    }

    public static  Result getErrorResult(String msg) {
        return getErrorResult(CommonFinal.RESULT_CODE_FAILURE, msg);
    }

    public static <T> Result<T>  getSuccessResult(T data) {
        Result model = new Result();
        model.setMsg(CommonFinal.SUCCESS);
        model.setResultStatus(CommonFinal.RESULT_CODE_SUCCESS);
        model.setResultData(data);
        return model;
    }



    public static<T> Result<Map<String, T>> getSuccessResult(String key, T data){
        Result model = new Result();
        model.setMsg(CommonFinal.SUCCESS);
        model.setResultStatus(CommonFinal.RESULT_CODE_SUCCESS);
        Map<String, T> map = new HashMap<String, T>();
        map.put(key, data);
        model.setResultData(map);
        return model;
    }

    public static Result<Map<String, Integer>> getSuccessResultForSumbitData(int id){
        Result model = new Result();
        model.setMsg(CommonFinal.SUCCESS);
        model.setResultStatus(CommonFinal.RESULT_CODE_SUCCESS);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        model.setResultData(map);
        return model;
    }
}
