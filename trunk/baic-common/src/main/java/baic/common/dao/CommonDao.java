package baic.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import baic.common.exception.ServicePersistenceException;

@Repository
public class CommonDao {

	private final Logger logger = Logger.getLogger(CommonDao.class);

	@Autowired
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Long insert(Object t) {
		logger.debug("Insert " + t.getClass().getName());
		return (Long) sessionFactory.getCurrentSession().save(t);
	}

	public Long save(Object t) {
		logger.debug("Insert " + t.getClass().getName());
		return (Long) sessionFactory.getCurrentSession().save(t);
	}

	public Long batchInsert(List<Object> list) {
		logger.debug("batchInsert");
		int i = 1;
		for (Object t : list) {
			if (t != null) {
				i++;
				logger.info("Insert " + t.getClass().getName());
				insert(t);
			} else {
				logger.error("Can not update null value.");
			}

		}
		return (long) i;
	}

	public Long batchUpdate(List<Object> list) {
		logger.debug("batchUpdate");
		int i = 1;
		for (Object t : list) {
			if (t != null) {
				i++;
				logger.info("Update " + t.getClass().getName());
				update(t);
			} else {
				logger.error("Can not update null value.");
			}

		}
		return (long) i;
	}

	public Long batchDelete(List<Object> list) {
		logger.debug("batchDelete");
		int i = 1;
		for (Object t : list) {
			if (t != null) {
				i++;
				logger.info("Delete " + t.getClass().getName());
				delete(t);
			} else {
				logger.error("Can not delete null value.");
			}
		}
		return (long) i;
	}

	public void delete(Object t) {
		logger.debug("Delete " + t.getClass().getName());
		try {
			sessionFactory.getCurrentSession().delete(t);
		} catch (NonUniqueObjectException e) {
			logger.info("Non unique instance in session of " + t.getClass().getName());
			Long id = null;
			try {
				Field[] fields = t.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					if (StringUtils.equals(field.getName(), "id")) {
						id = (Long) field.get(t);
					}
				}
			} catch (Exception e1) {
				logger.error("Failed to get value of id.", e1);
			}
			if (id != null) {
				Object obj = sessionFactory.getCurrentSession().get(t.getClass(), id);
				sessionFactory.getCurrentSession().delete(obj);
			} else {
				logger.error("Failed to delete " + t.getClass().getName() + ": " + t);
			}

		}

	}

	public void update(Object t) {
		logger.debug("Update " + t.getClass().getName());
		if (t != null) {
			sessionFactory.getCurrentSession().update(sessionFactory.getCurrentSession().merge(t));
		}
	}

	public List<?> findAll(Class<?> type, Order order) {
		if (null != order) {
			return sessionFactory.getCurrentSession().createCriteria(type).addOrder(order).list();
		}
		return sessionFactory.getCurrentSession().createCriteria(type).list();
	}

	@SuppressWarnings("rawtypes")
	public Long findCountByNativeSql(String query, HashMap<String, Object> parameterMap)
			throws ServicePersistenceException {
		logger.debug("Inside findCountByNativeSql.");
		Long cnt = 0L;
		Query q = null;
		query = "select count(*) from (" + query + ") countTemp ";
		try {
			q = sessionFactory.getCurrentSession().createSQLQuery(query);
			if (parameterMap != null) {
				q = setParam(q, parameterMap);
			}
			List out = q.list();
			if (out != null && !out.isEmpty()) {
				cnt = Long.valueOf(String.valueOf(out.get(0)));
			}
			return cnt;
		} catch (Exception e) {
			logger.error("Failed to execute query: " + query, e);
			throw new ServicePersistenceException("Failed to execute query: " + query, e);
		}

	}

	public List<?> findByNativeSql(String query, HashMap<String, Object> parameterMap, Class<?> clazz)
			throws ServicePersistenceException {
		logger.debug("Inside findByNativeSql.");

		Query q = null;
		try {
			q = sessionFactory.getCurrentSession().createSQLQuery(query)
					.setResultTransformer(Transformers.aliasToBean(clazz));
			if (parameterMap != null) {
				q = setParam(q, parameterMap);
			}
		} catch (Exception e) {
			logger.error("Failed to execute query: " + query, e);
			throw new ServicePersistenceException("Failed to execute query: " + query, e);
		}
		return q.list();
	}

	public Object findById(Class<?> type, Object id) {
		return (sessionFactory.getCurrentSession().get(type, (Serializable) id));
	}

	public List<?> findByNamedQuery(String namedQuery, HashMap<String, Object> parameterMap)
			throws ServicePersistenceException {
		logger.debug("Inside find.");
		Query q = execQuery(namedQuery, parameterMap);
		return q.list();
	}

	public List<?> findByHql(String query, HashMap<String, Object> parameterMap) throws ServicePersistenceException {
		logger.debug("Inside findByHql.");
		Query q = null;
		try {
			q = sessionFactory.getCurrentSession().createQuery(query);
			if (parameterMap != null) {
				q = setParam(q, parameterMap);
			}
		} catch (Exception e) {
			logger.error("Failed to execute query: " + query, e);
			throw new ServicePersistenceException("Failed to execute query: " + query, e);
		}
		return q.list();
	}

	private Query execQuery(String query, HashMap<String, Object> parameterMap) throws ServicePersistenceException {
		logger.debug("Inside executeQuery.");
		Query q = null;
		try {
			q = sessionFactory.getCurrentSession().getNamedQuery(query);
			if (parameterMap != null) {
				q = setParam(q, parameterMap);
			}
		} catch (Exception e) {
			logger.error("Failed to execute query.", e);
			throw new ServicePersistenceException("Failed to execute query.", e);
		}
		return q;
	}

	private Query setParam(Query q, HashMap<String, Object> params) {
		String[] namedParameters = q.getNamedParameters();
		for (String namedParameter : namedParameters) {
			Object object = params.get(namedParameter);
			if (object instanceof Collection) {
				q.setParameterList(namedParameter, (Collection<?>) object);
			} else {
				q.setParameter(namedParameter, object);
			}
		}
		return q;
	}
}