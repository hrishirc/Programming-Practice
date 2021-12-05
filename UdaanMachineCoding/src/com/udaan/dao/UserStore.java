package com.udaan.dao;

import com.udaan.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserStore {
    AtomicLong counter = new AtomicLong(0);
    Map<Long, User> userMap = new HashMap<>();
    private static final UserStore userStore = new UserStore();

    public synchronized static UserStore getInstance()
    {
        return userStore;
    }

    public long add()
    {
        User user = new User(counter.getAndAdd(1));
        userMap.put(user.id, user);
        return user.id;
    }

    public boolean isUserExist(long id)
    {
        return userMap.containsKey(id);
    }
}
