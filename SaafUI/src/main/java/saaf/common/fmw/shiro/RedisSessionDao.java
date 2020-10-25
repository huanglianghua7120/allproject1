package saaf.common.fmw.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSessionDao extends AbstractSessionDAO {

	private final static Logger LOGGER = LoggerFactory.getLogger(RedisSessionDao.class);

	private RedisTemplate<String, Object> redisTemplate;
	private int defaultExpireTime = 3600;
	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_session:";

	public RedisSessionDao(RedisTemplate<String, Object> redisTemplate, int defaultExpireTime) {
		this.redisTemplate = redisTemplate;
		this.defaultExpireTime = defaultExpireTime;
	}

	@Override
	protected Serializable doCreate(Session session) {
		LOGGER.debug("doCreate " + session + " *******");
		Serializable sessionID = generateSessionId(session);
		assignSessionId(session, sessionID);
		// 将创建好的sessionID序列传入redis数据库
		String preKey = this.keyPrefix + sessionID;
		redisTemplate.opsForValue().set(preKey, session);
		redisTemplate.expire(preKey, this.defaultExpireTime, TimeUnit.SECONDS);
		return sessionID;
	}

	// 根据sessionID读取session数据,此方法主要目的在于先去本地址读取session,如果没有
	// 那么还有一种可能是此数据在别的服务器上,那么我们不能跨服务器读session,只能从redis
	// 数据中查看是否有此sessionID,如果还没有表示时间到期,无session需要重新登陆
	@Override
	protected Session doReadSession(Serializable sessionId) {
		LOGGER.debug("doreadsession " + sessionId);
		String preKey = this.keyPrefix + sessionId;
		return (Session) redisTemplate.opsForValue().get(preKey);
	}

	// session有个存储时间,更新session的更新
//	@Override
//	protected void doUpdate(Session session) {
//		System.out.println("doUpdate " + session + " *******");
//		if (session != null) { // 如果更新的时候session还存在,那么还需要再去存数据库一次
//			redisTemplate.opsForValue().set(session.getId().toString(), session);
//			redisTemplate.expire(session.getId().toString(), this.defaultExpireTime, TimeUnit.SECONDS);
//		}
//	}

	// 执行session的删除处理
//	@Override
//	protected void doDelete(Session session) {
//		System.out.println("doDelete " + session + " **********");
//		redisTemplate.delete(session.getId().toString());
//	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		LOGGER.debug("doUpdate " + session + " *******");
		if (session != null) { // 如果更新的时候session还存在,那么还需要再去存数据库一次
			String preKey = this.keyPrefix + session.getId().toString();
			redisTemplate.opsForValue().set(preKey, session);
			redisTemplate.expire(preKey, this.defaultExpireTime, TimeUnit.SECONDS);
		}
	}

	@Override
	public void delete(Session session) {
		LOGGER.debug("doDelete " + session + " **********");
		String preKey = this.keyPrefix + session.getId().toString();
		redisTemplate.delete(preKey);
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		Set<String> keys = redisTemplate.keys(this.keyPrefix + "*");
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				Session s = (Session) redisTemplate.opsForValue().get(key);
				sessions.add(s);
			}
		}
		return sessions;
//		return null;
	}

}
