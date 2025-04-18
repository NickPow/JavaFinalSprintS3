package com.gym.models;

/**
 * Represents a gym membership purchased by a user.
 */
public class Membership {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipCost;
    private int memberId;

    public Membership(int membershipId, String membershipType, String membershipDescription, double membershipCost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipCost = membershipCost;
        this.memberId = memberId;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getMembershipDescription() {
        return membershipDescription;
    }

    public double getMembershipCost() {
        return membershipCost;
    }

    public int getMemberId() {
        return memberId;
    }
}
