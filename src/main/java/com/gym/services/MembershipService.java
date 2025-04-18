package com.gym.services;

import com.gym.dao.MembershipDAO;
import com.gym.models.User;

/**
 * Service layer for managing gym membership logic.
 */
public class MembershipService {
    private final MembershipDAO membershipDAO = new MembershipDAO();

    /**
     * Allows a user to purchase a new gym membership.
     *
     * @param user The user making the purchase.
     */
    public void purchaseMembership(User user) {
        membershipDAO.purchaseMembership(user.getUserId());
    }

    /**
     * Displays all memberships for all users.
     */
    public void viewAllMemberships() {
        membershipDAO.viewAllMemberships();
    }

    /**
     * Displays total revenue generated from memberships.
     */
    public void viewTotalRevenue() {
        membershipDAO.viewTotalRevenue();
    }

    /**
     * Displays the total cost of memberships for a single user.
     *
     * @param user The user whose expenses to display.
     */
    public void viewUserExpenses(User user) {
        membershipDAO.viewUserExpenses(user.getUserId());
    }
}
