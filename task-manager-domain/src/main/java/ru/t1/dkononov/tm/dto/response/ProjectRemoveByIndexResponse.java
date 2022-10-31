package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectRemoveByIndexResponse extends AbstractProjectResponse {

    public ProjectRemoveByIndexResponse(@Nullable final ProjectDTO project) {
        super(project);
    }

}
