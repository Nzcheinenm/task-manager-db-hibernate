package ru.t1.dkononov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.services.IPropertyService;

import java.io.IOException;
import java.util.Properties;

public class PropertyService implements IPropertyService {

    @NotNull
    public static final String FILE_NAME = "application.properties";

    @NotNull
    public static final String APPLICATION_VERSION_KEY = "application.version";

    @NotNull
    public static final String AUTHOR_EMAIL_KEY = "author.email";

    @NotNull
    public static final String AUTHOR_NAME_KEY = "author.name";

    @NotNull
    public static final String PASSWORD_ITERATION_DEFAULT = "1234";

    @NotNull
    public static final String PASSWORD_ITERATION_KEY = "password.iteration";

    @NotNull
    public static final String PASSWORD_SECRET_DEFAULT = "5437612";

    @NotNull
    public static final String PASSWORD_SECRET_KEY = "password.secret";

    @NotNull
    public static final String EMPTY_VALUE = "---";

    @NotNull
    public final Properties properties = new Properties();

    public PropertyService() {
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(FILE_NAME));
        } catch (@NotNull final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NotNull
    public String getApplicationVersion() {
        return getStringValue(APPLICATION_VERSION_KEY);
    }

    @Override
    @NotNull
    public String getAuthorEmail() {
        return getStringValue(AUTHOR_EMAIL_KEY);
    }

    @Override
    @NotNull
    public String getAuthorName() {
        return getStringValue(AUTHOR_NAME_KEY);
    }

    @NotNull
    private String getEnvKey(@NotNull final String key) {
        return key.replace(".", "_").toUpperCase();
    }

    @NotNull
    private String getStringValue(@NotNull final String key) {
        return getStringValue(key, EMPTY_VALUE);
    }

    @Override
    @NotNull
    public Integer getPasswordIteration() {
        return getIntegerValue(PASSWORD_ITERATION_KEY, PASSWORD_ITERATION_DEFAULT);
    }

    @Override
    @NotNull
    public String getPasswordSecret() {
        return getStringValue(PASSWORD_SECRET_KEY, PASSWORD_SECRET_DEFAULT);
    }

    @NotNull
    private Integer getIntegerValue(
            @NotNull final String key,
            @NotNull final String defaultKey
    ) {
        return Integer.parseInt(getStringValue(key, defaultKey));
    }

    private String getStringValue(
            @NotNull final String key,
            @NotNull final String defaultKey
    ) {
        if (System.getProperties().containsKey(key)) return System.getProperties().getProperty(key);
        @NotNull final String envKey = getEnvKey(key);
        if (System.getenv().containsKey(envKey)) return System.getenv(envKey);
        return properties.getProperty(key, defaultKey);
    }

}
