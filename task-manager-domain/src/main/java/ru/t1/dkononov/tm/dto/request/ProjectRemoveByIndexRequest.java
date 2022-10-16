package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectRemoveByIndexRequest extends AbstractUserRequest {

    @Nullable
    private Integer index;

    public ProjectRemoveByIndexRequest(@Nullable final String token) {
        super(token);
    }

}
