package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

import java.util.Arrays;

public class Sprint {

    private final Ticket[] sprintTickets;
    private int nextFreeIndex = 0;
    private final int capacity;
    private int usedCapacity;
    private final int ticketsLimit;


    public Sprint(int capacity, int ticketsLimit) {
        this.capacity = capacity;
        this.ticketsLimit = ticketsLimit;
        this.sprintTickets = new Ticket[ticketsLimit];
    }

    private boolean isTicketValid(Ticket ticket) {
        if (ticket != null
                && !ticket.isCompleted()
                && (usedCapacity + ticket.getEstimate() <= capacity)
                && (nextFreeIndex <= ticketsLimit - 1))
            return true;
        return false;
    }

    public boolean addUserStory(UserStory userStory) {
        if (!isTicketValid(userStory)) return false;
        int dependStoryinSprintCount = 0;
        int unCompleteDepend = 0;

        for (UserStory story : userStory.getDependencies()) {
            if (!story.isCompleted()) {
                unCompleteDepend++;
                for (Ticket ticket : sprintTickets) {
                    if (ticket == null)
                        break;
                    if ((ticket.getId() == story.getId())) {
                        dependStoryinSprintCount++;
                        break;
                    }
                }
            }
        }
        boolean isDependStoryinSprint = dependStoryinSprintCount == unCompleteDepend;

        if (isDependStoryinSprint) {
            sprintTickets[nextFreeIndex++] = userStory;
            usedCapacity += userStory.getEstimate();
            return true;
        } else return false;
    }

    public boolean addBug(Bug bugReport) {
        if (isTicketValid(bugReport)
                && bugReport.getUserStory() != null
                && bugReport.getUserStory().isCompleted()) {
            //WRONG DESCRIPTION IN TASK!!! "addBug(Bug bugReport) - accepts a userStory, if it is not null and **not completed**. Returns true if the bug is accepted, false otherwise."
            sprintTickets[nextFreeIndex++] = bugReport;
            usedCapacity += bugReport.getEstimate();
            return true;
        } else return false;
    }

    public Ticket[] getTickets() {
        return Arrays.copyOf(sprintTickets, nextFreeIndex);
    }

    public int getTotalEstimate() {
        return usedCapacity;
    }
}
