package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectChangeStatusByIndexResponse extends AbstractProjectResponse {
    public ProjectChangeStatusByIndexResponse(@Nullable final Project project) {
        super(project);
    }
}
