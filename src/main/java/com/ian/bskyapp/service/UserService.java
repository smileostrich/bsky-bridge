package com.ian.bskyapp.service;

import com.ian.bskyapp.entity.Session;
import com.ian.bskyapp.entity.dto.User;

public interface UserService {

    Session login(User user);

    Session refreshSession(Session session);

}
