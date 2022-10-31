package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProjectResponse extends AbstractResponse {

    @Nullable
    private ProjectDTO project;

    public AbstractProjectResponse(@Nullable ProjectDTO project) {
        this.project = project;
    }

}
