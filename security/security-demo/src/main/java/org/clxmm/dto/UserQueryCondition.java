package org.clxmm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author clx
 * @date 2020-06-22 21:00
 */
@Data
public class UserQueryCondition {

    private String username;

    @ApiModelProperty("用户年龄起始值")
    private int age;

    @ApiModelProperty("用户年龄结束值")
    private int ageTo;

    private String others;


}
