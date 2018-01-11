package com.cx.os.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author www.inxedu.com
 *
 */
@Data
public class QuerySysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String keyWord;

	/**角色ID*/
	private int roleId;
}
