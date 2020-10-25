package saaf.common.fmw.common.model.inter.server;


import com.yhg.base.utils.SToolUtils;

import java.lang.reflect.InvocationTargetException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.common.model.inter.ISessionBean;

@Component("sessionBeanServer")
public class SessionBeanServer implements ISessionBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionBeanServer.class);
    
    private UserInfoSessionBean userInfoSessionBean;

    public SessionBeanServer() {
        super();
    }

    public void setUserInfoSessionBean(UserInfoSessionBean userInfoSessionBean) {
        this.userInfoSessionBean = userInfoSessionBean;
    }

    public UserInfoSessionBean getUserInfoSessionBean() {
        return userInfoSessionBean;
    }

    public Object set5WhoAttribute(boolean insertFlag, Object targetObject_, Integer userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return set5WhoAttribute(insertFlag, targetObject_, userId + "");
    }

    public Object set5WhoAttribute(boolean insertFlag, Object targetObject_, String userId) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (insertFlag) {
            targetObject_.getClass().getDeclaredMethod("setCreatedBy", java.lang.Integer.class).invoke(targetObject_, SToolUtils.object2Int(userId));
            targetObject_.getClass().getDeclaredMethod("setCreationDate", Date.class).invoke(targetObject_, new Date());
            targetObject_.getClass().getDeclaredMethod("setLastUpdatedBy", java.lang.Integer.class).invoke(targetObject_, SToolUtils.object2Int(userId));
            targetObject_.getClass().getDeclaredMethod("setLastUpdateDate", Date.class).invoke(targetObject_, new Date());
            targetObject_.getClass().getDeclaredMethod("setLastUpdateLogin", java.lang.Integer.class).invoke(targetObject_, SToolUtils.object2Int(userId));
        } else {
            targetObject_.getClass().getDeclaredMethod("setLastUpdatedBy", java.lang.Integer.class).invoke(targetObject_, SToolUtils.object2Int(userId));
            targetObject_.getClass().getDeclaredMethod("setLastUpdateDate", Date.class).invoke(targetObject_, new Date());
            targetObject_.getClass().getDeclaredMethod("setLastUpdateLogin", java.lang.Integer.class).invoke(targetObject_, SToolUtils.object2Int(userId));
        }
        return targetObject_;
    }
}