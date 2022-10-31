package ru.t1.dkononov.tm.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.t1.dkononov.tm.dto.model.ProjectDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectListResponse extends AbstractProjectResponse {

    @Nullable
    private List<ProjectDTO> projects;

    public ProjectListResponse(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}
