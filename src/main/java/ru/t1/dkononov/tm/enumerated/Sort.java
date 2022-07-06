package ru.t1.dkononov.tm.enumerated;

import ru.t1.dkononov.tm.comparator.CreatedComparator;
import ru.t1.dkononov.tm.comparator.NameComparator;
import ru.t1.dkononov.tm.comparator.StatusComparator;

import java.util.Comparator;

public enum Sort {

    BY_NAME("Сортировка по имени", NameComparator.INSTANCE),
    BY_STATUS("Сортировка по статусу", StatusComparator.INSTANCE),
    BY_CREATED("Сортировка по дате создания", CreatedComparator.INSTANCE);

    private final String displayName;

    private final Comparator<?> comparator;

    public static Sort toSort(final String value) {
        if (value == null || value.isEmpty()) return null;
        for (final Sort sort : values()) {
            if (sort.name().equals(value)) return sort;
        }
        return null;
    }

    Sort(final String displayName, final Comparator<?> comparator) {
        this.displayName = displayName;
        this.comparator = comparator;
    }

    public String getDisplayName() {
        return displayName;
    }

    @SuppressWarnings("rawtypes")
    public Comparator getComparator() {
        return comparator;
    }

}
