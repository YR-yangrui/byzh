package com.byzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byzh.Mapper.TeamMemberMapper;
import com.byzh.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TeamMemberService extends IService<TeamMember> {
    void insert(String userId, Integer teamId);
    List<User> getTeamMembers(Integer teamId);
    List<Integer> getTeamIdsByMemberId(String userId);
    int deleteTeamMembersByTeamId(Integer teamId);
    //成员加入队伍
    //boolean JoinTeam(Integer tid,String uid);

    /**
     * 申请管理
     * @param tid
     * @param uid
     * @return
     */
    //申请人加入申请缓冲区
    boolean JoinInBuffer(Integer tid,String uid);
    //队长获取队列
    List<BufferList> getApplyList(String tid);
    //队长身份验证
    Boolean verifyLeader(String uid,String tid);
    //队长将队员加入
    Boolean addApplyIntoTeam(String tid,String uid);
    //删除成员
    Boolean updateMemberInBuffer(Integer sta,String uid,String tid);
    //获取状态码
    Integer getState(String tid,String uid);
    //根据队伍id获取队伍人数
    Integer countMembers(Integer id);
    //申请人取消申请
    Boolean cancelApply(String tid,String uid);

    //获取自己加入的队列
    List<AddedTeam> addedTeam(String uid);
    //获取自己创建的队列
    List<Integer> myTeam(String uid);
    //获取自己申请的队列和队长名
    List<Applyer> myApplyTeam(String uid);
}
