package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class ProjectStartByIndexRequest extends AbstractUserRequest {

    @Nullable
    private Integer index;

    public ProjectStartByIndexRequest(@Nullable final String token) {
        super(token);
    }

}
