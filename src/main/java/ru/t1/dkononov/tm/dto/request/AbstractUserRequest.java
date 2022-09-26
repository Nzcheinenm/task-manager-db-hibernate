package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
public abstract class AbstractUserRequest extends AbstractRequest {

    @Nullable
    private String userId;

}
