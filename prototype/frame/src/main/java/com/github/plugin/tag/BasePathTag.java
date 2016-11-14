package com.github.plugin.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.github.util.FrameUtil;

public class BasePathTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    public BasePathTag() {
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(FrameUtil.getBasePath((HttpServletRequest) pageContext.getRequest()).toString());
        } catch (IOException e) {
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }

}