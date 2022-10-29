package com;

import org.junit.Test;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Description: 生成MD5加密密码
 * @Author: Alex McAvoy
 * @Date: 2022/10/28 22:46
 * @Version: 1.0
 **/
public class TestMD5 {
    @Test
    public void testMD5() {
        //密码明文
        String password = "123456";

        //带盐的 MD5 三次加密
        Md5Hash md5Hash3 = new Md5Hash(password, "salt", 3);
        System.out.println("带盐的 MD5 进行 3 次加密: " + md5Hash3.toHex());

    }
}
