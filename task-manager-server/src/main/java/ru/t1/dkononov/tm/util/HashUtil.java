package ru.t1.dkononov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.component.ISaltProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface HashUtil {

    @Nullable
    static String salt(
            @Nullable final String value,
            @Nullable final String secret,
            @Nullable final Integer iteration
    ) {
        if (value == null || iteration == null || secret == null) return null;
        @Nullable String result = null;
        for (int i = 0; i < iteration; i++) {
            result = md5(value + result + secret);
        }
        return result;
    }

    @Nullable
    static String salt(
            @Nullable final ISaltProvider saltProvider,
            @Nullable final String value
    ) {
        if (saltProvider == null) return null;
        @Nullable final String secret = saltProvider.getPasswordSecret();
        @Nullable final Integer iteration = saltProvider.getPasswordIteration();
        return salt(value, secret, iteration);
    }

    @Nullable
    static String md5(@Nullable String md5) {
        if (md5 == null) return null;
        try {
            @NotNull MessageDigest md = MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(md5.getBytes());
            @NotNull final StringBuffer sb = new StringBuffer();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (@NotNull NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
