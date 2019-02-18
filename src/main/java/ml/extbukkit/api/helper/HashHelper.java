package ml.extbukkit.api.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 encryption helper
 */
public class HashHelper {
    private HashHelper() {

    }

    /**
     * Try to encrypt a file
     * Recommended to use
     *
     * @param file File to encrypt
     * @return MD5 encrypted string
     */
    public static String md5FileTry(File file) {
        try {
            return md5File(file);
        } catch (IOException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Encrypt a file
     *
     * @param f File to encrypt
     * @return MD5 encrypted string
     * @throws IOException File is not exists
     * @throws NoSuchAlgorithmException MD5 algorithm is not found
     */
    public static String md5File(File f) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(f);
        byte[] byteArray = new byte[1024];
        int bytes = 0;
        while((bytes = fis.read(byteArray)) != -1)
            digest.update(byteArray, 0, bytes);
        fis.close();
        byte[] bts = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bts.length; i++)
            sb.append(Integer.toString((bts[i] & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }
}