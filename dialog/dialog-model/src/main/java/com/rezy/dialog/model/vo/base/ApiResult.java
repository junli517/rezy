
package com.rezy.dialog.model.vo.base;

import java.io.Serializable;

import com.rezy.dialog.model.enums.ApiCodeEnum;

/**
 * @ClassName:  ApiResult
 * @Description: 接口返回
 * @Version: V1.0
 * @Author: jun.li
 * @Date: 2019年03月25 
 */
public class ApiResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private Object result;

    public String getCode() {
        return code;
    }

    public ApiResult setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ApiResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ApiResult setResult(Object result) {
        this.result = result;
        return this;
    }

    public static ApiResult success() {
        return success(null);
    }

    public static ApiResult success(Object result) {
        return code(ApiCodeEnum.SUCCESS).setResult(result);
    }

    public static ApiResult code(ApiCodeEnum apiCode) {
        ApiResult vo = new ApiResult();
        vo.code = apiCode.getCode();
        vo.msg = apiCode.getMsg();
        return vo;
    }
    
    public static ExceptionResult fail(Exception e) {
        return fail().setException(e);
    }

    public static ExceptionResult fail() {
        return fail((String) null);
    }

    public static ExceptionResult fail(String msg) {
        return ExceptionResult.fail(msg);
    }

    @Override
    public String toString() {
        return "BaseOutVo [code=" + code + ", msg=" + msg + ", result=" + result + "]";
    }
}
