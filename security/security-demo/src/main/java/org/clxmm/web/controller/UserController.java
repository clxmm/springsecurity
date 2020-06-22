package org.clxmm.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.clxmm.dto.User;
import org.clxmm.dto.UserQueryCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author clx
 * @date 2020-06-22 20:41
 */
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * @param queryCondition
     * @param pageable       spring data
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
//    public List<User> query(@RequestParam(name = "username", required = false, defaultValue = "tom") String username) {
    public List<User> query(UserQueryCondition queryCondition,
                            @PageableDefault(page = 1, size = 11) Pageable pageable) {
        System.out.println(queryCondition.getUsername() + "-" + queryCondition.getAge());
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername(String.valueOf(i));
            users.add(user);
        }

        return users;
    }


    /**
     * \\id+ 只能接受数字
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {
        User user = new User();
        user.setUsername("tom");
        return user;
    }


}
