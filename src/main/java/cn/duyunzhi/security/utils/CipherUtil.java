package cn.duyunzhi.security.utils;


import java.security.MessageDigest;

/**
* @Author: Ezreal-d
* @Description: 对密码进行加密和验证的类
* @Date: 2019/9/2 15:29
*/

public class CipherUtil {
    
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    /** * 把inputString加密     */
    public static String generatePassword(String inputString){
        return encodeByMD5(encodeByMD5(inputString));
    }
    
      /**
       * 验证登录时输入的密码是否正确
     * @param password    数据库中加密2次后的密码
     * @param inputString    登录时前台传过来的加密1次后的密码
     * @return    验证结果，TRUE:正确 FALSE:错误
     */
    public static boolean validateLoginPassword(String password, String inputString){
        if(password.equals(encodeByMD5(inputString))){
            return true;
        } else{
            return false;
        }
    }

    
    /**
     * 验证修改密码时输入的密码是否正确
   * @param password    数据库中加密2次后的密码
   * @param inputString    明文输入的密码
   * @return    验证结果，TRUE:正确 FALSE:错误
   */
  public static boolean validateModifyPassword(String password, String inputString){
      if(password.equals(encodeByMD5(encodeByMD5(inputString)))){
          return true;
      } else{
          return false;
      }
  }
  
    /**  对字符串进行MD5加密     */
    public static String encodeByMD5(String originString){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }
        return new String(a);
  
    }  
    
    /** 
     * 转换字节数组为十六进制字符串
     * @param b    字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    /** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

}