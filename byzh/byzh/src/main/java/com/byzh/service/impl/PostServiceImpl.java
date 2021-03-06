package com.byzh.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byzh.Mapper.PostMapper;
import com.byzh.pojo.Post;
import com.byzh.service.PostService;
import com.byzh.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private TeamService teamService;
    @Override
    public IPage<Post> getPage(Integer currentPage, Integer pageSize) {
        IPage page = new Page(currentPage,pageSize);
        postMapper.selectPage(page,null);
        return page;
    }

    @Override
    public List<Post> searchByContent(String content) {
        return postMapper.getListByContent(content);
    }

    @Override
    public List<Post> searchByContentAndFlag(String content, int flag) {
        return postMapper.getListByContentAndFlag(content,flag);

    }


    @Override
    public List<Post> searchByPosterId(String id) {
        return postMapper.getListByPosterId(id);
    }

    @Override
    public List<Post> searchByPosterIdAndFlag(String id, int flag) {
        return postMapper.getListByPosterIdAndFlag(id,flag);
    }

    @Override
    public List<Post> searchByStartLocation(String startLocation) {
        return postMapper.getListByStartLocation(startLocation);
    }

    @Override
    public List<Post> searchByDestination(String destination) {
        return postMapper.getListByDestination(destination);
    }

    @Override
    public List<Post> searchByDestinationAndFlag(String destination, int flag) {
        return postMapper.getListByDestinationAndFlag(destination,flag);
    }


    @Override
    public List<Post> searchByTime(Integer year, Integer month, Integer day, Integer hour, Integer minute) {
        return postMapper.getListByTime(year,month,day,hour,minute);
    }

    @Override
    public List<Post> searchByTimeAndFlag(Integer year, Integer month, Integer day, Integer hour, Integer minute, int flag) {
        return postMapper.getListByTimeAndFlag(year,month,day,hour,minute,flag);
    }

    @Override
    public List<Post> searchByStartLocationAndDestination(String startLocation, String destination) {
        return postMapper.getListByStartLocationAndDestination(startLocation,destination);
    }

    @Override
    public List<Post> listPostByFlag(int flag) {
        return postMapper.listPostByFlag(flag);
    }

    @Override
    public Post getPostByIdAndFlag(Integer id, int flag) {
        return postMapper.getPostByIdAndFlag(id,flag);
    }

    @Override
    public boolean deleteById(Integer id) {
        Post post = postMapper.selectById(id);
        postMapper.deleteById(id);
        teamService.deleteTeam(post.getTeamId());
        return true;
    }

    @Override
    public boolean insertPost(Post post,Integer teamNumber) {
        boolean flag =true;
        post.setPostTime(new Date(System.currentTimeMillis()));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setYear(post.getYear() - 1900);
        timestamp.setMonth(post.getMonth() -1);
        timestamp.setDate(post.getDay());
        timestamp.setHours(post.getHour() );
        timestamp.setMinutes(post.getMinute());
        post.setDeadTime(timestamp);
        flag = flag & (postMapper.insert(post)>0);
        teamService.createNewTeam(post,teamNumber);
        postMapper.updateById(post);
        return flag;
    }
}
