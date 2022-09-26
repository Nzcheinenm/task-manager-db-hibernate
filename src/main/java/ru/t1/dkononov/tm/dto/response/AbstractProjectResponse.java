package ru.t1.dkononov.tm.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.endpoint.ProjectEndpoint;
import ru.t1.dkononov.tm.model.Project;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractProjectResponse extends AbstractResponse {

    @Nullable
    private Project project;

    public AbstractProjectResponse(@Nullable Project project) {
        this.project = project;
    }

}
