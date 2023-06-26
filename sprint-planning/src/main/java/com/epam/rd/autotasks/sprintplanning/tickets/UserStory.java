package com.epam.rd.autotasks.sprintplanning.tickets;

import java.util.Arrays;

public class UserStory extends Ticket {
    private final UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        dependencies = dependsOn.clone();
    }

    @Override
    public void complete() {
        boolean incomplete = false;
        for (UserStory userstory : dependencies) {
            if (!userstory.isCompleted()) {
                incomplete = true;
                break;
            }
        }
        if (!incomplete) {
            super.complete();
        }
    }

    public UserStory[] getDependencies() {
        return Arrays.copyOf(dependencies, dependencies.length);
    }

    @Override
    public String toString() {
        return "[US " + this.getId() + "] " + this.getName();
    }
}
