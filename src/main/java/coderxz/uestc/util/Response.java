package coderxz.uestc.util;

import com.alibaba.fastjson.JSONObject;

public class Response<T> {
    public static String success(Object data) {
        JSONObject object = new JSONObject(true);
        object.put("code", 2000);
        object.put("message", "操作成功");
        object.put("data", data);
        return object.toJSONString();
    }

    public static String failed(Object data) {
        JSONObject object = new JSONObject(true);
        object.put("code", 500);
        object.put("message", "操作失败！");
        object.put("data", data);
        return object.toJSONString();
    }
}
