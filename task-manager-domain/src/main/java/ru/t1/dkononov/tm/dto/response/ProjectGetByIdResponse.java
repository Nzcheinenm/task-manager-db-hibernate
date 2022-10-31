package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectGetByIdResponse extends AbstractProjectResponse {

    public ProjectGetByIdResponse(@Nullable final ProjectDTO project) {
        super(project);
    }

}
