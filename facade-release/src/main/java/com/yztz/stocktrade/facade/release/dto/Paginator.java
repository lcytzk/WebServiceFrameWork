/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.release.dto;

import java.io.Serializable;

/**
 * @author Liang Chenye
 * @version $Id: Paginator, v 0.1 2015/6/29 17:29
 */

public class Paginator implements Serializable, Cloneable {

    /**
     * 默认的页大小10
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    private static final long serialVersionUID = 2439307328405264659L;

    /**
     * 当前页号
     */
    private int page;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 总记录数
     */
    private int items;

    public Paginator() {}

    /**
     * 根据总记录数构建分页器
     *
     * @param items  总记录数
     */
    public Paginator(int items) {
        this(1, DEFAULT_PAGE_SIZE, items);
    }

    /**
     * 根据页号和页大小构建分页器
     *
     * @param page      页号
     * @param pageSize  页大小
     */
    public Paginator(int page, int pageSize) {
        this(page, pageSize, 0);
    }

    /**
     * 构建分页器
     *
     * @param page       页号
     * @param pageSize   页大小
     * @param items  总记录数
     */
    public Paginator(int page, int pageSize, int items) {
        this.page = page <= 0 ? 1 : page;
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
        this.items = items < 0 ? 0 : items;
        calcPage();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public int getPages() {
        return pages;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items < 0 ? 0 : items;
        calcPage();
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页大小
     *
     * <p>如果页大小小于0，那个设置成 {@link #DEFAULT_PAGE_SIZE 默认}的页大小</p>
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
        calcPage();
    }

    /**
     * 当前页号是否在正确的范围内
     *
     * @return
     */
    public boolean isPageSafe() {
        return page <= pages;
    }

    /**
     * 获取最后一页
     *
     * @return
     */
    public int getLastPage() {
        return pages;
    }

    /**
     * 获取前一页
     *
     * <p>如果当前页为1，那么没有上一页，则返回首页1</p>
     *
     * @return 上一页, 如果没有上一页，则返回首页1
     */
    public int getPreviousPage() {
        return page <= 1 ? 1 : page - 1;
    }

    /**
     * 获取页偏移量
     *
     * @return
     */
    public int getOffset() {
        return this.page > 0 ? (page - 1) * pageSize : 0;
    }

    /**
     * 获得当前页的数据的最后一行在中记录中的行号
     *
     * @return
     */
    public int getTop() {
        return getOffset() + pageSize;
    }

    protected void calcPage() {
        pages = items <= 0 ? 0 : (items / pageSize + (items % pageSize == 0 ? 0 : 1));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Paginator [page=" + page + ", pages=" + pages + ", items=" + items + ", pageSize=" + pageSize + "]";
    }

}
