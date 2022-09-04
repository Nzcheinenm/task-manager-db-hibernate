package ru.t1.dkononov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.management.ManagementFactory;

public interface SystemUtil {

    static long getPID() {
        @Nullable final String processName = ManagementFactory.getRuntimeMXBean().getName();
        if (processName != null && processName.length() > 0) {
            try {
                return Long.parseLong(processName.split("@")[0]);
            } catch (@NotNull final Exception e) {
                return 0;
            }
        }
        return 0;
    }

}
