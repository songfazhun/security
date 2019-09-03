package cn.duyunzhi.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
* @Author: Ezreal-d
* @Description: 角色实体
* @Date: 2019/9/2 14:08
*/
@Data
@TableName("tb_role")
public class Role implements Serializable {
	private static final long serialVersionUID = -3711253298788767581L;

	@TableId(type = IdType.AUTO)
	private Integer id;

	private String roleName;
}
