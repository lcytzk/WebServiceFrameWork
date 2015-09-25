/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.release.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Liang Chenye
 * @version $Id: PageList, v 0.1 2015/6/29 17:28
 */
public class PageList<E> implements Serializable {

    private static final long serialVersionUID = 3906365987534414965L;

    private Paginator paginator;

    private List<E> content;

    public PageList() {
        paginator = new Paginator(0);
        content = new ArrayList<E>();
    }

    public PageList(int size) {
        content = new ArrayList<E>(size);
        paginator = new Paginator(0);
    }

    public PageList(Collection<E> c) {
        this(c, null);
    }

    public PageList(Collection<E> c, Paginator paginator) {
        content = new ArrayList<E>(c);
        this.paginator = paginator;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public List<E> getContent() {
        return content;
    }

    public void setContent(List<E> content) {
        this.content = content;
    }

    public void add(E item) {
        content.add(item);
    }
}
