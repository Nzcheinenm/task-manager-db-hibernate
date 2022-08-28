package ru.t1.dkononov.tm.comparator;

import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.IHasName;

import java.util.Comparator;

public enum NameComparator implements Comparator<IHasName> {

    INSTANCE;

    @Override
    public int compare(@Nullable final IHasName o1, @Nullable final IHasName o2) {
        if (o1 == null || o2 == null) return 0;
        if (o1.getName() == null || o2.getName() == null) return 0;
        return o1.getName().compareTo(o2.getName());
    }

}
