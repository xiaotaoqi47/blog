package com.scs.web.blog.util;

import lombok.Data;

/**
 * @author cyq-xn
 * @ClassName ResponseObject
 * @Description TODO
 * @Date 2019/10/10
 * @Version 1.0
 **/

@Data
public class ResponseObject {
    private Integer code;
    private String msg;
    private Object data;

    public static ResponseObject success(Integer code, String msg) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }

    /**
     * 静态方法，对外提供该类的对象，请求成功有数据返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ResponseObject success(Integer code, String msg, Object data) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        ro.setData(data);
        return ro;
    }

}
