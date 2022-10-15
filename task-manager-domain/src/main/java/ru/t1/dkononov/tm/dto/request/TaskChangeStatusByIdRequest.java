package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class TaskChangeStatusByIdRequest extends AbstractUserRequest {

    @Nullable
    private String statusValue;

    @Nullable
    private String id;

    public TaskChangeStatusByIdRequest(@Nullable final String token) {
        super(token);
    }

}
