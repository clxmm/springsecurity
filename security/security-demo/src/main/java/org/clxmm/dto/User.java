package org.clxmm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.clxmm.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author clx
 * @date 2020-06-22 20:43
 */
public class User {


    public interface UserSimpleView {
    }


    public interface UserDetailView extends UserSimpleView {
    }


    public Integer id;

    @MyConstraint(message = "只是一个测试")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

