package ru.t1.dkononov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface HashUtil {

    @NotNull
    String SECRET = "321321";

    @NotNull
    Integer ITERATION = 4040;

    @Nullable
    static String salt(@Nullable final String value) {
        if (value == null) return null;
        String result = null;
        for (int i = 0; i < ITERATION; i++) {
            result = md5(SECRET + result + SECRET);
        }
        return result;
    }

    @Nullable
    static String md5(@Nullable String md5) {
        if (md5 == null) return null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(md5.getBytes());
            @NotNull final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
