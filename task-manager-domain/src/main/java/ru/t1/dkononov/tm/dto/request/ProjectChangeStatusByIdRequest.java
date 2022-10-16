package ru.t1.dkononov.tm.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectChangeStatusByIdRequest extends AbstractUserRequest {

    @Nullable
    private String id;

    @Nullable
    private String statusValue;

    public ProjectChangeStatusByIdRequest(@Nullable final String token) {
        super(token);
    }
}
