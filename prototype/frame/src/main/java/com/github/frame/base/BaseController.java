package com.github.frame.base;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.frame.common.Code;
import com.github.frame.exception.ServiceException;
import com.github.frame.plugin.validator.BaseGroup;
import com.github.frame.util.FrameUtil;

@Component
public abstract class BaseController<T> {

    @Autowired(required = false)
    protected HttpServletRequest request;

    @Autowired(required = false)
    protected HttpServletResponse response;

    @Autowired(required = false)
    protected HttpSession httpSession;

    protected String basePath = FrameUtil.getBasePath(getClass());

    public Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping("/search")
    public String search(@ModelAttribute T t, @ModelAttribute Pagination<T> p) {
        getBaseService().doPage(t, p);
        return basePath + "list";
    }

    @RequestMapping("/gotoAdd")
    public String gotoAdd(T t) {
        return basePath + "edit";
    }

    @RequestMapping("/add")
    public String add(T t) throws Exception {
        validate(t, BaseGroup.ADD.class);
        getBaseService().doSave(t);
        return "redirect:" + basePath + "search.html";
    }

    @RequestMapping("/gotoEdit")
    public String gotoEdit(T t, ModelMap m) {
        m.addAttribute(getBaseService().doFind(t));
        return basePath + "edit";
    }

    @RequestMapping("/edit")
    public String edit(T t) throws Exception {
        validate(t, BaseGroup.Edit.class);
        getBaseService().doUpdate(t);
        return "redirect:" + basePath + "search.html";
    }

    @RequestMapping("/view")
    public String view(T t, ModelMap m) {
        m.addAttribute(getBaseService().doFind(t));
        return basePath + "view";
    }

    @RequestMapping("/delete")
    public String delete(T t) throws Exception {
        getBaseService().doDelete(t);
        return "redirect:" + basePath + "search.html";
    }

    public void validate(T t, Class<?> clazz) {
        Set<ConstraintViolation<T>> constraints = validator.validate(t, clazz);
        if (!constraints.isEmpty()) {
            StringBuffer sb = new StringBuffer();
            Iterator<ConstraintViolation<T>> it = constraints.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getMessage());
            }
            throw new ServiceException(Code.PARAMETERS_ERROR, sb.toString());
        }
    }

    public Validator getValidator() {
        return validator;
    }

    public abstract BaseService<T> getBaseService();

}