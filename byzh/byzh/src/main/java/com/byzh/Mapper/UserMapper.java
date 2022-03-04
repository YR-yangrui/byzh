package com.byzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byzh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id = #{id} and pwd = #{pwd} ;")
    User getByIdAndPwd (String id,String pwd);

    @Insert("insert into user (id,nickname,gender,qq,wechat,pwd,major,campus,heading) values(#{id},#{nickname},#{gender},#{qq},#{wechat},#{pwd},#{major},#{campus},#{heading})")
    boolean register(User user);

}
