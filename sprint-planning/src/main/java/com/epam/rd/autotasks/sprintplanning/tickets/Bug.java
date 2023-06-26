package com.epam.rd.autotasks.sprintplanning.tickets;

public class Bug extends Ticket {
    private final UserStory userStory;

    public UserStory getUserStory() {
        return userStory;
    }

    public static Bug createBug(int id, String name, int estimate, UserStory userStory) {
        if (userStory == null|| !userStory.isCompleted())
            return null;
        else return new Bug(id, name, estimate, userStory);
    }

    private Bug(int id, String name, int estimate, UserStory userStory) {
        super(id, name, estimate);
        this.userStory = userStory;
    }

    @Override
    public String toString() {
        return "[Bug " + getId() +"] " + getUserStory().getName() + ": " + getName();
    }
}
