
package com.attendance.utils;

import java.util.UUID;

public class CommonUtils {
    public static String getUUid(){

        // UUID.randomUUID()调用随机方法，获取一个随机 id
        String uid = UUID.randomUUID().toString();

        // replace（"被替换的目标内容", "替换成指定内容"）
        String uuid = uid.replace("-","");
        return uuid;
    }

}
