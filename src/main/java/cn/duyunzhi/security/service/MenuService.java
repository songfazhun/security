package cn.duyunzhi.security.service;

import cn.duyunzhi.security.entity.Menu;
import cn.duyunzhi.security.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Author: Ezreal-d
* @Description: 菜单Service
* @Date: 2019/9/2 13:47
*/

@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> getMenusByUserId(Integer id) {
		return this.menuMapper.selectMenusByUserId(id);
	}
}
