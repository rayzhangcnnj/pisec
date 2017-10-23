package com.ray.pi.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title: null判断辅助类
 * @Description:
 * @Copyright: Copyright (c) 2014-6-19
 * @Company: asiainfo
 * @Author: shenjun
 */
@SuppressWarnings("rawtypes")
public abstract class CheckNull
{
	/**
	 * 
	 * 检查字符串是否为空 包括空字符串,去空格
	 * 
	 * @param str
	 * @return true:空 false：非空
	 */
	public static boolean isNullTrim(String str)
	{
		return (null == str || "".trim().equals(str));
	}

	/**
	 * 
	 * 检查字符串是否为空 包括空字符串
	 * 
	 * @param str
	 * @return true:空 false：非空
	 */
	public static boolean isNull(String str)
	{
		return (null == str || "".equals(str.trim()));
	}

	/**
	 * 
	 * 检查list是否为空 包括list的size为0
	 * 
	 * @param List
	 * @return true:空 false：非空
	 */
    public static boolean isNull(List lst)
	{
		return null == lst || 0 == lst.size();
	}
	
	   /**
     * 
     * 检查set是否为空 包括set的size为0
     * 
     * @param List
     * @return true:空 false：非空
     */
    public static boolean isNull(Set set)
    {
        return null == set || 0 == set.size();
    }

	/**
	 * 检查数组是否为空
	 * 
	 * @param objs
	 * @return true:空 false：非空
	 */
	public static boolean isNull(Object[] objs)
	{
		if (null == objs)
		{
			return true;
		}
		else
		{
			for (int i = 0; i < objs.length; i++)
			{
				if (objs[i] != null && !"".equals(objs[i].toString().trim()))
				{
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 
	 * 检查map是否为空 包括map的size为0
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isNull(Map map)
	{
		return null == map || 0 == map.size();
	}

	/**
	 * 
	 * 检查object是否为空
	 * 
	 * @param map
	 * @return true 空
	 */
	public static boolean isNull(Object obj)
	{
		return null == obj;
	}
}
