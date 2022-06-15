package ru.t1.dkononov.tm.model;

import ru.t1.dkononov.tm.api.ICommandRepository;

public class Command {

    private String name;
    private String argument;
    private String description;

    public Command() {
    }

    public Command(String name, String argument, String description) {
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

    private void setArgument(String argument) {
        this.argument = argument;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "";
        if(name != null && !name.isEmpty())
            result += name;
        if(argument != null && !argument.isEmpty())
            result +=", " + argument;
        if(description != null && !description.isEmpty())
            result += ": " + description;
        return result;
    }

}
