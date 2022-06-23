package ru.t1.dkononov.tm.model;

public class Command {

    private String name;
    private String argument;
    private String description;

    public Command() {
    }

    public Command(final String name, final String argument, final String description) {
        this.name = name;
        this.argument = argument;
        this.description = description;
    }

    private String getArgument() {
        return this.argument;
    }

    private String getDescription() {
        return this.description;
    }

    private String getName() {
        return this.name;
    }

    private void setArgument(final String argument) {
        this.argument = argument;
    }

    private void setName(final String name) {
        this.name = name;
    }

    private void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "";
        if (name != null && !name.isEmpty())
            result += name;
        if (argument != null && !argument.isEmpty())
            result += ", " + argument;
        if (description != null && !description.isEmpty())
            result += ": " + description;
        return result;
    }

}
