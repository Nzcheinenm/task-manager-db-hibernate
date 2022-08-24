package ru.t1.dkononov.tm.enumerated;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.NameComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;

import java.util.Comparator;


public enum Sort {

    BY_NAME("Сортировка по имени", NameComparator.INSTANCE),
    BY_STATUS("Сортировка по статусу", StatusComparator.INSTANCE),
    BY_CREATED("Сортировка по дате создания", CreatedComparator.INSTANCE);

    @Getter
    @NotNull
    private final String displayName;

    @Nullable
    private final Comparator<?> comparator;

    @Nullable
    public static Sort toSort(@Nullable final String value) {
        if (value == null || value.isEmpty()) return null;
        for (@NotNull final Sort sort : values()) {
            if (sort.name().equals(value)) return sort;
        }
        return null;
    }

    Sort(@NotNull final String displayName,@NotNull final Comparator<?> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    @Nullable
    @SuppressWarnings("rawtypes")
    public Comparator getComparator() {
        return comparator;
    }

}
