package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by guanxiaoda on 17/5/8.
 */
public class StringUtil {
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static void main(String[] args) {
        System.out.println(MD5("http://tieba.baidu.com/p/2804574675?pid=44175149419&cid=0#44175149419"));
        logger.info("ok." );
    }


        public static final String MD5 = "MD5";
        public static final String CODE = "%02x";
        private static MessageDigest digest;
        static {
            try {
                digest = MessageDigest.getInstance(MD5);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("no such algorithm.");
            }
        }

        public synchronized static String MD5(String text) {
            byte[] bytes = digest.digest(text.getBytes());
            StringBuilder output = new StringBuilder(bytes.length);
            for (byte entry : bytes) {
                output.append(String.format(CODE, entry));
            }
            digest.reset();
            bytes = null;
            return output.toString();
        }

}
