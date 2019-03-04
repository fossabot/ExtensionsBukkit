package ml.extbukkit.api.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * MD5 encryption helper
 */
public class HashHelper {

    private HashHelper() {
        throw new UnsupportedOperationException( "This class cannot be instanced." );
    }

    /**
     * Encrypt a file safely.
     *
     * @param file File to encrypt
     * @return MD5 encrypted string if not fail
     */
    public static String md5FileSafe(File file) {
        try {
            return md5File(file);
        } catch ( IOException | NoSuchAlgorithmException e) {
            return "Encryption has failed: " + ExceptionUtils.getMessage( e.fillInStackTrace() );
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
        int bytes;

        while((bytes = fis.read(byteArray)) != -1)
        {
            digest.update(byteArray, 0, bytes);
        }

        fis.close();
        byte[] bts = digest.digest();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < bts.length; i++)
        {
            sb.append(Integer.toString((bts[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}