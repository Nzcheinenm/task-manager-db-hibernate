package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class TaskUpdateByIdRequest extends AbstractUserRequest {

    @Nullable
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    public TaskUpdateByIdRequest(@Nullable final String token) {
        super(token);
    }

}
