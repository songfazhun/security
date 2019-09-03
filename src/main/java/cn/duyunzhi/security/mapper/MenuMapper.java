package cn.duyunzhi.security.mapper;

import cn.duyunzhi.security.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @Author: Ezreal-d
* @Description: 菜单Mapper
* @Date: 2019/9/2 13:53
*/

public interface MenuMapper extends BaseMapper<Menu> {

	/**
	 * 查询用户所拥有的菜单
	 * @param id
	 * @return
	 */
	@Select("select * from tb_menu")
	List<Menu> selectMenusByUserId(Integer id);
}
