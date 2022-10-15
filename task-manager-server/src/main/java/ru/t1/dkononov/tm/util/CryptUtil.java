package ru.t1.dkononov.tm.util;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public interface CryptUtil {

    @NotNull
    String TYPE = "AES/ECB/PKCS5Padding";

    @NotNull
    @SneakyThrows
    static SecretKeySpec getKey(@NotNull final String secretKey) {
        @NotNull final MessageDigest sha = MessageDigest.getInstance("SHA-1");
        @NotNull final byte[] key = secretKey.getBytes("UTF-8");
        @NotNull final byte[] digest = sha.digest(key);
        @NotNull final byte[] secret = Arrays.copyOf(digest,16);
        return  new SecretKeySpec(secret,"AES");
    }

    @NotNull
    @SneakyThrows
    static String encrypt (@NotNull final String secret, @NotNull final String strToEncrypt) {
        @NotNull final SecretKeySpec secretKey = getKey(secret);
        @NotNull final Cipher cipher = Cipher.getInstance(TYPE);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        @NotNull final byte[] bytes = strToEncrypt.getBytes("UTF-8");
        return Base64.getEncoder().encodeToString(cipher.doFinal(bytes));
    }

    @NotNull
    @SneakyThrows
    static String decrypt (@NotNull final String secret, @NotNull final String strToDencrypt) {
        getKey(secret);
        @NotNull final Cipher cipher = Cipher.getInstance(TYPE);
        cipher.init(Cipher.DECRYPT_MODE,getKey(secret));
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDencrypt)));
    }

}
