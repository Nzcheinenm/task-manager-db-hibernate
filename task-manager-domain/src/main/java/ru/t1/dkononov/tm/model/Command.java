package ru.t1.dkononov.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class Command {

    @Nullable
    private String name;

    @Nullable
    private String argument;

    @Nullable
    private String description;

    public Command(@Nullable final String name, @Nullable final String argument, @Nullable final String description) {
        this.name = name;
        this.argument = argument;
        this.description = description;
    }

    @NotNull
    @Override
    public String toString() {
        @Nullable String result = "";
        if (name != null && !name.isEmpty())
            result += name;
        if (argument != null && !argument.isEmpty())
            result += ", " + argument;
        if (description != null && !description.isEmpty())
            result += ": " + description;
        return result;
    }

}
