package saaf.common.fmw.common.model.inter;

import java.lang.reflect.InvocationTargetException;

import saaf.common.fmw.bean.UserInfoSessionBean;


public interface ISessionBean {
    public void setUserInfoSessionBean(UserInfoSessionBean userInfoSessionBean);

    public UserInfoSessionBean getUserInfoSessionBean();

    public Object set5WhoAttribute(boolean insertFlag, Object targetObject_, Integer userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    public Object set5WhoAttribute(boolean insertFlag, Object targetObject_, String userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
