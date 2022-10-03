package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractResultResponse extends AbstractResponse {

    @NotNull
    private Boolean success = true;

    private String message = "";

    public AbstractResultResponse(@NotNull final Throwable throwable) {
        setSuccess(false);
        setMessage(throwable.getMessage());
    }

}
