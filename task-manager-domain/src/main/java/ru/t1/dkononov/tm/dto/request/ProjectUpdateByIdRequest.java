package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectUpdateByIdRequest extends AbstractUserRequest {

    @Nullable
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    public ProjectUpdateByIdRequest(@Nullable final String token) {
        super(token);
    }

}
