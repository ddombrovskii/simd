package ru.ssau.simd.pojo;

import lombok.Data;
import ru.ssau.simd.entity.User;

@Data
public class UserPojo {
    private Long id;
    private String name;

    public static UserPojo fromEntity(User user) {
        UserPojo pojo = new UserPojo();

        pojo.setId(user.getId());
        pojo.setName(user.getName());

        return pojo;
    }

    public static User toEntity(UserPojo pojo) {
        User user = new User();

        user.setId(pojo.getId());
        user.setName(pojo.getName());

        return user;
    }
}
