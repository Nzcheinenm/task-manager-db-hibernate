package ru.t1.dkononov.tm.api;

import ru.t1.dkononov.tm.dto.request.AbstractRequest;
import ru.t1.dkononov.tm.dto.response.AbstractResponse;

@FunctionalInterface
public interface Operation<RQ extends AbstractRequest, RS extends AbstractResponse> {

    RS execute(RQ request);

}
