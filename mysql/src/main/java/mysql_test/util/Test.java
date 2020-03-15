package mysql_test.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

/**
 * 简单加密
 * @author lyy
 */
public class Test {



   /**
    * base64加密
    * @param plaintext
    */
    public static String baseSet(String plaintext){
        String ciphertext=null;
        ciphertext = Base64.getEncoder().encodeToString(plaintext.getBytes(StandardCharsets.UTF_8));
        System.out.println("加密后的字符串为:" + ciphertext);
        return ciphertext;
    }
   /**
    * base64解密
    * @param ciphertext
    */
   public static String baseGet(String ciphertext){
       String plainttext= new String(Base64.getDecoder().decode(ciphertext), StandardCharsets.UTF_8);
       System.out.println("解密后的字符串为" + plainttext);
       return plainttext;
   }

   /**
    * 数据运算实现字符串的加密和解密
    * @param value 明文
    * @param secret 异或运算进行加密字段
    */
    public static String encryptAndDencrypt(String value, char secret) {
        //将需要加密的内容转换为字节数组
        byte[] bt = value.getBytes();
        for (int i = 0; i < bt.length; i++) {
            //通过异或运算进行加密
            bt[i] = (byte) (bt[i] ^ (int) secret);
        }
        //将加密后的字符串保存到 newresult 变量中
        return new String(bt, 0, bt.length);
    }

    public static void main(String[] args) {
        //加密文字符
        char secret = '8';
        System.out.println("请输入您想加密的内容：");
        String pass = "jdbc:mysql://39.97.119.183:3306/RUNOOB";
        System.out.println("原字符串内容：" + pass);
        String encryptResult = encryptAndDencrypt(pass, secret);
        System.out.println("加密后的内容：" + encryptResult);
        String uncryptResult = encryptAndDencrypt(encryptResult, secret);
        String s=encryptAndDencrypt("",secret);
        System.out.println("解密后的内容：" + uncryptResult);
    }
}
