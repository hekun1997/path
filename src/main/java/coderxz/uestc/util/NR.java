package coderxz.uestc.util;

import com.alibaba.fastjson.JSONObject;

public class NR<T> {
    public static String r(Object data) {
        JSONObject object = new JSONObject(true);
        object.put("code", 2000);
        object.put("message", "操作成功");
        object.put("data", data);
        return object.toJSONString();
    }
}
