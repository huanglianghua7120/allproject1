package saaf.common.fmw.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafRespMenuEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SaafUserRespEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafMenuFunctions;
import saaf.common.fmw.base.model.inter.ISaafUserResp;
import saaf.common.fmw.base.model.inter.ISaafUsers;

import java.util.*;

/**
 * Created by zhm on 2015/7/10.
 */
public class CustomRealm extends AuthorizingRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

	private String keyPrefix = "shiro_authorization:";

	@Autowired
	private ISaafUsers baseSaafUsersServer;
	@Autowired
	private ISaafMenuFunctions baseSaafMenuFunctionsServer;
	@Autowired
	private ISaafUserResp baseSaafUserRespServer;

//	// 设置自定义的MD5加密匹配
//	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//		HashedCredentialsMatcher tmp = new HashedCredentialsMatcher("MD5");
//		tmp.setStoredCredentialsHexEncoded(true);
//		super.setCredentialsMatcher(tmp);
//	}

	/**
	 * 该方法在需要授权访问资源的时候调用。
	 * 
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// String currentUsername = (String) super.getAvailablePrincipal(principals);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		/**
		 * 权限表可以自己建表关联。
		 */
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		// simpleAuthorInfo.addRole("admin");
		SaafUsersEntity_HI userEntity = (SaafUsersEntity_HI) session.getAttribute(ShiroConstant.session_userEntity_key);
		Object[] param = new Object[] { userEntity.getUserId() };
		Set<String> stringPermissions = new HashSet<String>();
		try {
			List<SaafUserRespEntity_HI_RO> userRespList = baseSaafUserRespServer.findSaafUserResp(param);
			Object[] objparams = new Object[2];
			objparams[1] = "SAAF";
			for (SaafUserRespEntity_HI_RO userResp : userRespList) {
				objparams[0] = userResp.getResponsibilityId();
				List<SaafRespMenuEntity_HI_RO> menuBtnlist = baseSaafMenuFunctionsServer.findSaafMenuBtnList(objparams);
				for (SaafRespMenuEntity_HI_RO temp : menuBtnlist) {
					String buttonPermission = temp.getButtonPermission();
					if (org.springframework.util.StringUtils.hasText(buttonPermission)) {
						stringPermissions.add(buttonPermission);
					}
				}

			}
			simpleAuthorInfo.setStringPermissions(stringPermissions);
		} catch (Exception e) {
			LOGGER.error("用户认证，查询用户职责、权限异常", e);
			return null;
		}
		return simpleAuthorInfo;
	}

	/**
	 * 该方法在LoginController执行dologin之后调用
	 * 
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", token.getUsername());
		map.put("encryptedPassword", new String(token.getPassword()));
		SaafUsersEntity_HI userEntity;
		try {
			userEntity = baseSaafUsersServer.findUserLogin(map);
		} catch (Exception e) {
			LOGGER.error("用户认证，查询用户信息异常", e);
			return null;
		}
		if (null != userEntity) {
			// 不用验证密码对不对，只需要把数据传递过去就行。shiro会帮忙比较
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userEntity.getUserName(),
					userEntity.getEncryptedPassword(), userEntity.getUserFullName());
			this.setSession(ShiroConstant.session_userEntity_key, userEntity);
			return authcInfo;
		} else {
			return null;
		}
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return keyPrefix + super.getAvailablePrincipal(principals);
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}
