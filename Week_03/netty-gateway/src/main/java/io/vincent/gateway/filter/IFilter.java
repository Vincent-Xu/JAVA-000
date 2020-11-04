package io.vincent.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;


/**
 *@Author: Vincent Xu
 *@Description: filter interface
 *@CreatedTime:18:02 2020/11/3
 */ 
public interface IFilter {
    /**
     * run filter
     * @param fullHttpRequest
     */
    public void run(FullHttpRequest fullHttpRequest);
}
