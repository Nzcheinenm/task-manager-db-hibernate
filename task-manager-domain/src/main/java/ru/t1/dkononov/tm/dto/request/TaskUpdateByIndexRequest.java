package ru.t1.dkononov.tm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class TaskUpdateByIndexRequest extends AbstractUserRequest {

    @Nullable
    private String index;

    @Nullable
    private String name;

    @Nullable
    private String description;

}
