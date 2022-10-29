package com.getir.ReadingIsGood.dto.response;

import com.getir.ReadingIsGood.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    Long id;
    int avatarId;
    String userName;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
    }
}