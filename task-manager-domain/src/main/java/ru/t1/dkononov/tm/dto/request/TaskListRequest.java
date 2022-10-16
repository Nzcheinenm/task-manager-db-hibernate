package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;

@Getter
@Setter
@NoArgsConstructor
public class TaskListRequest extends AbstractUserRequest {

    @Nullable
    private Sort sort;

    public TaskListRequest(@Nullable final String token) {
        super(token);
    }

}
