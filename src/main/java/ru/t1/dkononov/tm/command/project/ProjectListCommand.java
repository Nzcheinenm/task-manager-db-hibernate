package ru.t1.dkononov.tm.command.project;

import ru.t1.dkononov.tm.enumerated.Sort;
import ru.t1.dkononov.tm.model.Project;
import ru.t1.dkononov.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public final class ProjectListCommand extends AbstractProjectCommand {

    public static final String PROJECT_LIST = "project-list";

    @Override
    public String getDescription() {
        return "Вывести список проектов.";
    }

    @Override
    public String getName() {
        return PROJECT_LIST;
    }

    @Override
    public void execute() {
        System.out.println("[SHOW PROJECTS]");
        System.out.println("[ENTER SORT: ]");
        System.out.println(Arrays.toString(Sort.values()));
        final String sortType = TerminalUtil.inLine();
        final Sort sort = Sort.toSort(sortType);
        int index = 0;
        final List<Project> projects = getProjectService().findAll(sort);
        for (final Project project : projects) {
            index++;
            System.out.println(index + ". " + project.getName());
        }
    }

}