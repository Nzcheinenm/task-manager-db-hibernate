package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.enumerated.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ProjectListRequest extends AbstractUserRequest {

    @Nullable
    private Sort sort;

    public ProjectListRequest(@NotNull final String token) {
        super(token);
    }
}
