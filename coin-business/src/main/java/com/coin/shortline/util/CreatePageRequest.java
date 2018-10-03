package com.coin.shortline.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author 作者 E-mail:ahuiwanglei@126.com
 * @version 创建时间：2014-4-4 下午3:03:58
 * 类说明
 */
public class CreatePageRequest {
 
	/**
     * 创建分页请求.
     */
    public static PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType, String sortName) {
        Sort sort = null;
        if ("desc".equals(sortType)) {
            sort = new Sort(Direction.DESC, sortName);
        } else{
            sort = new Sort(Direction.ASC, sortName);
        }
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }
}
