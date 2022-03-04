package com.byzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byzh.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

    @Select(("select * from byzhDB.post where flag=#{flag} order by `dead_time`"))
    List<Post> listPostByFlag(int flag);

    @Select("select * from byzhDB.post where id=#{id} and flag=#{flag} order by `dead_time`")
    Post getPostByIdAndFlag(Integer id,int flag);

    @Select("select * from byzhDB.post where content like '%${content}%' order by `dead_time`")
    List<Post> getListByContent(String content);

    @Select("select * from byzhDB.post where flag=#{flag} and content like '%${content}%' order by `dead_time`")
    List<Post> getListByContentAndFlag(String content,int flag);

    @Select("select * from byzhDB.post where poster_id=#{id} order by `dead_time`")
    List<Post> getListByPosterId(String id);

    @Select("select * from byzhDB.post where poster_id=#{id} and flag=#{flag} order by `dead_time`")
    List<Post> getListByPosterIdAndFlag(String id,int flag);

    @Select("select * from byzhDB.post where year=#{year} and month=#{month} and day=#{day} and hour=#{hour} and minute=#{minute}")
    List<Post> getListByTime(Integer year,Integer month,Integer day,Integer hour,Integer minute);

    @Select("select * from byzhDB.post where year=#{year} and month=#{month} and day=#{day} and hour=#{hour} and minute=#{minute} and flag=#{flag}")
    List<Post> getListByTimeAndFlag(Integer year,Integer month,Integer day,Integer hour,Integer minute,int flag);

    //拼车专用
    @Select("select * from byzhDB.post where start_location like '%${startLocation}%' and flag=0 order by `dead_time` ")
    List<Post> getListByStartLocation(String startLocation);


    @Select("select * from byzhDB.post where destination like '%${destination}%' order by `dead_time`")
    List<Post> getListByDestination(String destination);

    @Select("select * from byzhDB.post where destination like '%${destination}%' and flag=#{flag} order by `dead_time`")
    List<Post> getListByDestinationAndFlag(String destination,int flag);

    //拼车专用
    @Select("select * from byzhDB.post where start_location like '%${startLocation}%'  and destination like '%${destination}%' order by `dead_time`")
    List<Post> getListByStartLocationAndDestination(String startLocation,String destination);
}
