package com.byzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byzh.pojo.BufferList;
import com.byzh.pojo.TeamMember;
import com.byzh.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamMemberMapper extends BaseMapper<TeamMember> {

    @Select("select member_id from team_member where team_member.team_id=#{teamId}")
    List<String> getMembersIdByTeamId(Integer teamId);

    @Select("select * from team_member where team_member.member_id=#{userId}")
    List<Integer> getTeamIdsByMemberId(String userId);

    @Delete("delete from team_member where team_member.team_id=#{teamId}")
    int deleteTeamMembersByTeamId(Integer teamId);

    @Select("select * from team_member where team_id=#{teamId}")
    List<TeamMember> getTeamMembersByTeamId(Integer teamId);


    /**
     * 申请管理
     *
     * @param tid
     * @param uid
     * @return
     */
    //申请人端:根据队伍id增加用户
    @Insert("insert into apply_buffer (apply_buffer.team_id,apply_buffer.user_id) values (#{tid},#{uid});")
    Boolean addMemberInToBuffer(Integer tid, String uid);

    //验证队长身份
        //传入teamId，获得postId
    @Select("select post_id from team where team.id = #{tid};")
    Integer getPostIdByTid(String tid);
        //传入postId，获得posterId
    @Select("select poster_id from post where post.id = #{pid};")
    String getPosterIdByPid(Integer pid);
        //传入postId，获得username
    @Select("select poster_name from post where post.id = #{pid};")
    String getPosterNameIdByPid(Integer pid);

        //传入posterId，获得postId
    @Select("select id from post where post.poster_id = #{uid};")
    List<Integer> getPostIdByUid(String uid);
        //传入postId，获得teamId
    @Select("select post_id from team where team.id = #{tid} = #{pid};")
    List<Integer> getTeamIdByPid(Integer pid);

    //队长获取状态为false：0的队列
    @Select("select team_id,user_id from apply_buffer where apply_buffer.state = 0 and apply_buffer.team_id = #{tid};")
    List<BufferList> getFalseList(String tid);

    //队长将申请人加入队伍并修改Buffer状态为true：1
    @Insert("insert into team_member (team_member.team_id,team_member.member_id) values (#{tid},#{uid});")
    Boolean addMemberByTeamId(String tid, String uid);

    //修改Buffer状态码
    @Update("UPDATE apply_buffer SET state = #{sta} where apply_buffer.team_id = #{tid} and apply_buffer.user_id = #{uid};")
    Boolean changeBufferMemberState(Integer sta,String tid, String uid);

    //获取自己状态码
    @Select("select state from apply_buffer where apply_buffer.team_id = #{tid} and apply_buffer.user_id = #{uid};")
    Integer getStateByTidAndUid(String tid,String uid);

    //获取自己缓存区的申请队列id
    @Select("select team_id from apply_buffer where apply_buffer.user_id = #{uid};")
    List<Integer> getApplyListFromBufferByUid (String uid);

}
