package ru.t1.dkononov.tm.component;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.api.Operation;
import ru.t1.dkononov.tm.dto.request.AbstractRequest;
import ru.t1.dkononov.tm.dto.response.AbstractResponse;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dispatcher {

    @Nullable
    private final Map<Class<? extends AbstractRequest>, Operation<?, ?>> map = new LinkedHashMap<>();

    @NotNull
    public <RQ extends AbstractRequest, RS extends AbstractResponse> void registry(
            @NotNull final Class<RQ> reqClass, @NotNull final Operation<RQ, RS> operation
    ) {
        map.put(reqClass, operation);
    }

    @NotNull Object call(@NotNull final AbstractRequest request) throws Exception {
        @NotNull final Operation operation = map.get(request.getClass());
        return operation.execute(request);
    }

}
