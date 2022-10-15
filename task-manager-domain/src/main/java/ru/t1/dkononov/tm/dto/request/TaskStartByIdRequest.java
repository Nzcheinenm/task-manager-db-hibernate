package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class TaskStartByIdRequest extends AbstractUserRequest {

    @Nullable
    private String id;

    public TaskStartByIdRequest(@Nullable final String token) {
        super(token);
    }

}
