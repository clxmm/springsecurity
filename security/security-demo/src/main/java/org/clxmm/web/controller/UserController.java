package org.clxmm.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.clxmm.dto.User;
import org.clxmm.dto.UserQueryCondition;
import org.clxmm.exception.UserNotExistException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public User getInfo(@PathVariable String id,String name) {
        System.out.println(name);
//        throw new UserNotExistException("1");
//        throw new RuntimeException("1");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername("tom");
        return user;
    }


    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(e -> {
                System.out.println(e.getDefaultMessage());

                FieldError fieldError = (FieldError) errors;

                String s = fieldError.getField() + e.getDefaultMessage();
                System.out.println(s);
            });
        }

        System.out.println(user);
        user.setId(1);
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(e -> {
                System.out.println(e.getDefaultMessage());

                FieldError fieldError = (FieldError) e;

                String s = fieldError.getField() + e.getDefaultMessage();
                System.out.println(s);
            });
        }

        System.out.println(user);
        user.setId(3);
        return user;
    }


    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("用户删除")
    public void delete(@ApiParam("用户id") @PathVariable String id) {
        System.out.println(id);
    }








}
