package com;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Shiro MD5 加密
 * @author Alex McAvoy
 * @date 2022/10/27 20:34
 * @version 1.0
 **/
public class MD5 {
    public static void main(String[] args) {
        //密码明文
        String password = "123456";
        //使用 MD5 加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("MD5: " + md5Hash.toHex());

        //带盐的 MD5 加密
        Md5Hash md5Hash2 = new Md5Hash(password, "salt");
        System.out.println("带盐的 MD5: " + md5Hash2.toHex());

        //为保证安全，可多次迭代加密
        Md5Hash md5Hash3 = new Md5Hash(password, "salt", 3);
        System.out.println("带盐的 MD5 进行 3 次加密: " + md5Hash3.toHex());

        //使用父类进行 MD5 加密
        SimpleHash simpleHash = new SimpleHash(
                "MD5", password, "salt", 3);
        System.out.println("父类带盐的 MD5 进行 3 次加密: " + simpleHash.toHex());
    }
}
