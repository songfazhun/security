package cn.duyunzhi.security.mapper;


import cn.duyunzhi.security.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @Author: Ezreal-d
* @Description: 用户Mapper
* @Date: 2019/9/2 13:53
*/

public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from tb_user where username = #{username}")
    User selectUserByName(@Param("username") String username);
}
