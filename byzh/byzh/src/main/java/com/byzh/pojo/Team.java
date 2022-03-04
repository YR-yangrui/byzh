package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Team {
    private Integer id;//前端不用
    private Integer postId;
    private Integer teamNumber;
}



/*按照用户id搜索自己加入的team
传入uid，返回tid，leadername list

取消申请
uid，tid

给uid查创建队伍

uid看申请列表
 */
