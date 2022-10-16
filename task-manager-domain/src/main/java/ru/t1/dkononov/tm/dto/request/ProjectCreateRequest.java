package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectCreateRequest extends AbstractUserRequest {

    @Nullable
    private String name;

    @Nullable
    private String description;

    public ProjectCreateRequest(@Nullable final String token) {
        super(token);
    }
}
