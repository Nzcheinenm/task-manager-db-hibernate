package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public final class ApplicationErrorResponse extends AbstractResultResponse {

    public ApplicationErrorResponse() {
        setSuccess(false);
    }

    public ApplicationErrorResponse(@NotNull Throwable throwable) {
        super(throwable);
    }

}
