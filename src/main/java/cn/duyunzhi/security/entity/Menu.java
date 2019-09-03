package cn.duyunzhi.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
* @Author: Ezreal-d
* @Description: 菜单实体
* @Date: 2019/9/2 13:56
*/
@Data
@TableName("tb_menu")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1969351151971241811L;

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String name;

	private String url;

	private String icon;

	private Integer parentId;
}
