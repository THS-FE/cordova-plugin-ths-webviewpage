package cn.com.ths.webview.page;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

    //算法
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";

    /**
     * @param encryptStr 要加密的字符串
     * @param keyStr 秘钥字符串
     * @param ivStr 偏移量，必须16位
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String aesEncrypt(String encryptStr,String keyStr,String ivStr) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        byte[] encryptBytes = encryptStr.getBytes("UTF-8");
        byte[] temp = ivStr.getBytes("UTF-8");
        IvParameterSpec iv = new IvParameterSpec(temp);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyStr.getBytes(), "AES"), iv);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        // return  new Base64().encodeAsString(decryptBytes);
//        return  new String(android.util.Base64.encode(decryptBytes, android.util.Base64.DEFAULT));
        return  new String(android.util.Base64.encode(parseByte2HexStr(decryptBytes).getBytes("UTF-8"),android.util.Base64.DEFAULT));
        //
    }

    /**
     * @param encryptStr 要解密的字符串
     * @param keyStr 秘钥字符串
     * @param ivStr 偏移量，必须16位
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr,String keyStr,String ivStr) throws Exception {

        // byte[] encryptBytes = new Base64().decodeBase64(encryptStr.getBytes());
        byte[] encryptBytes = android.util.Base64.decode(encryptStr.getBytes(), android.util.Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);

        byte[] temp = ivStr.getBytes("UTF-8");
        IvParameterSpec iv = new IvParameterSpec(temp);

        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyStr.getBytes(), "AES"), iv);
//        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        byte[] decryptBytes = cipher.doFinal(parseHexStr2Byte(new String(encryptBytes)));
        return new String(decryptBytes);
    }

//    public static void main(String[] args) throws Exception {
//        String en = aesEncrypt("adminsolution","64cf6dc95568a29e8e682f240c3fba38","solutionsolution");
//        String de = aesDecrypt(en,"64cf6dc95568a29e8e682f240c3fba38","solutionsolution");
//        System.out.println(en);
//        System.out.println(de);
//    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
