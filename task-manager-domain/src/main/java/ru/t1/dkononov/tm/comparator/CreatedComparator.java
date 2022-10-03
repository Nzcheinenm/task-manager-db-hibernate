package ru.t1.dkononov.tm.comparator;

import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.model.IHasCreated;

import java.util.Comparator;

public enum CreatedComparator implements Comparator<IHasCreated> {

    INSTANCE;

    @Override
    public int compare(@Nullable final IHasCreated o1, @Nullable final IHasCreated o2) {
        if (o1 == null || o2 == null) return 0;
        if (o1.getCreated() == null || o2.getCreated() == null) return 0;
        return o1.getCreated().compareTo(o2.getCreated());
    }

}
