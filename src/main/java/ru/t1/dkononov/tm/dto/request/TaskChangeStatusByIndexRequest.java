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
public final class TaskChangeStatusByIndexRequest extends AbstractUserRequest {

    @Nullable
    private String index;

    @Nullable
    private String statusValue;

}
