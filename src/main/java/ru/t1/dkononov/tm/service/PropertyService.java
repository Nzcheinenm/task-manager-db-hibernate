package ru.t1.dkononov.tm.service;

import com.jcabi.manifests.Manifests;
import lombok.Cleanup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.services.IPropertyService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.ClassLoader.getSystemResourceAsStream;

public final class PropertyService implements IPropertyService {

    @NotNull
    public static final String APPLICATION_FILE_NAME_DEFAULT = "application.properties";

    @NotNull
    public static final String APPLICATION_FILE_NAME_KEY = "config";

    @NotNull
    public static final String APPLICATION_VERSION_KEY = "buildNumber";

    @NotNull
    public static final String AUTHOR_EMAIL_KEY = "email";

    @NotNull
    public static final String AUTHOR_NAME_KEY = "developer";

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
    public static final String GIT_BRANCH = "gitBranch";

    @NotNull
    public static final String GIT_COMMIT_ID = "gitCommitId";

    @NotNull
    public static final String GIT_COMMIT_TIME = "gitCommitTime";

    @NotNull
    public static final String GIT_COMMIT_MESSAGE = "gitCommitMessage";

    @NotNull
    public static final String GIT_COMMITER_NAME = "gitCommiterName";

    @NotNull
    public static final String GIT_COMMITER_EMAIL = "gitCommiterEmail";

    @NotNull
    public final Properties properties = new Properties();

    public PropertyService() {
        try {
            final boolean existsConfig = isExistsExternalConfig();
            if (existsConfig) loadExternalConfig(properties);
            else loadInternalConfig(properties);
        } catch (@NotNull final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private String read(@Nullable final String key) {
        if (key == null || key.isEmpty()) return EMPTY_VALUE;
        if (!Manifests.exists(key)) return EMPTY_VALUE;
        return Manifests.read(key);
    }

    private void loadInternalConfig(@NotNull final Properties properties) throws IOException {
        @NotNull final String name = APPLICATION_FILE_NAME_DEFAULT;
        @Cleanup @Nullable final InputStream inputStream = getSystemResourceAsStream(name);
        properties.load(inputStream);
    }

    private void loadExternalConfig(@NotNull final Properties properties) throws IOException {
        @NotNull final String name = getApplicationConfig();
        @NotNull final File file = new File(name);
        @Cleanup @NotNull final InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
    }

    private boolean isExistsExternalConfig() {
        @NotNull final String name = getApplicationConfig();
        @NotNull final File file = new File(name);
        return file.exists();
    }

    @Override
    @NotNull
    public String getApplicationVersion() {
        return read(APPLICATION_VERSION_KEY);
    }

    @Override
    @NotNull
    public String getAuthorEmail() {
        return read(AUTHOR_EMAIL_KEY);
    }

    @Override
    @NotNull
    public String getAuthorName() {
        return read(AUTHOR_NAME_KEY);
    }

    @Override
    @NotNull
    public String getApplicationConfig() {
        return getStringValue(APPLICATION_FILE_NAME_KEY, APPLICATION_FILE_NAME_DEFAULT);
    }

    @Override
    public @NotNull String getGitBranch() {
        return read(GIT_BRANCH);
    }

    @Override
    public @NotNull String getGitCommitId() {
        return read(GIT_COMMIT_ID);
    }

    @Override
    public @NotNull String getGitCommitTime() {
        return read(GIT_COMMIT_TIME);
    }

    @Override
    public @NotNull String getGitCommitMessage() {
        return read(GIT_COMMIT_MESSAGE);
    }

    @Override
    public @NotNull String getGitCommiterName() {
        return read(GIT_COMMITER_NAME);
    }

    @Override
    public @NotNull String getGitCommiterEmail() {
        return read(GIT_COMMITER_EMAIL);
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
