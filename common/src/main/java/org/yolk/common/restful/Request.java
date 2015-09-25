/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.common.restful;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;

/**
 * @author Liang Chenye
 * @version $Id: Request, v 0.1 2015/8/11 11:00
 */

public interface Request {

    HttpPost toHttpPost() throws UnsupportedEncodingException;
}
