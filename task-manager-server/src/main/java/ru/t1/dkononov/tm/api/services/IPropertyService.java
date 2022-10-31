package ru.t1.dkononov.tm.api.services;

import org.jetbrains.annotations.NotNull;
import ru.t1.dkononov.tm.api.component.ISaltProvider;

public interface IPropertyService extends ISaltProvider, IDatabaseProperty {
    @NotNull
    String getApplicationVersion();

    @NotNull
    String getAuthorEmail();

    @NotNull
    String getAuthorName();

    @NotNull
    String getApplicationConfig();

    @NotNull Integer getServerPort();

    @NotNull String getServerHost();

    @NotNull String getSessionKey();

    @NotNull String getSessionTimeout();

    @NotNull
    String getGitBranch();

    @NotNull
    String getGitCommitId();

    @NotNull
    String getGitCommitTime();

    @NotNull
    String getGitCommitMessage();

    @NotNull
    String getGitCommiterName();

    @NotNull
    String getGitCommiterEmail();

    @NotNull String getDatabaseDriver();
}
