package com.foodworld.repository;

import java.util.ArrayList;

public interface IUsersRepository {

    ArrayList<String> getSubscription(String stringSubscription);

    boolean modifySubscription(String stringSubscription, String subscriptionName);

    boolean deleteSubscription(String name);

    boolean addSubscription(String subscription);

}
