package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class TaskGetByIndexRequest extends AbstractUserRequest {

    @Nullable
    private Integer index;

    public TaskGetByIndexRequest(@Nullable final String token) {
        super(token);
    }

}
