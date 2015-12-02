package cn.concar.service.db.dao;

import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.concar.service.exception.ServicePersistenceException;
import cn.concar.service.util.StringUtils;

/**
 * ClassName: CommonDao Function: Provide common DAO function. Date: 2013-6-24
 *
 * @author haoli
 * @version 1.0
 */
@Repository
public class CommonDao {

	/**
	 * LOG: BaseDao logger.
	 */
	private final Logger logger = Logger.getLogger(CommonDao.class);

	/**
	 * sessionFactory
	 */
	@Autowired
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private static final String LAST_INSERT_ID_SQL = "SELECT LAST_INSERT_ID()";
	public static final String SEARCH_EQ = "eq_";
	public static final String SEARCH_LIKE = "like_";
	public static final String SEARCH_BTW = "btw_";
	private static final String SEARCH_SEPE = "_";
	private static final String SEARCH_REL = "\u002E";

	/**
	 * insert.
	 * 
	 * @param t
	 *            t
	 * @return Long
	 */
	public Long insert(Object t) {
		logger.debug("Insert " + t.getClass().getName());
		return (Long) sessionFactory.getCurrentSession().save(t);
	}

	/**
	 * save:(Describe the usage of this method). Save without return.
	 * 
	 * @author haoli
	 * @param t
	 */
	public void save(Object t) {
		logger.debug("Insert " + t.getClass().getName());
		sessionFactory.getCurrentSession().save(t);
	}

	/**
	 * batchInsert.
	 * 
	 * @param list
	 *            list
	 * @return Long
	 */
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

	/**
	 * batchUpdate.
	 * 
	 * @param list
	 *            list
	 * @return Long
	 */
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

	/**
	 * batchUpdate.
	 * 
	 * @param list
	 *            list
	 * @return Long
	 */
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

	/**
	 * delete.
	 * 
	 * @param t
	 *            t
	 */
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
					if (StringUtils.match(field.getName(), "id")) {
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

	/**
	 * delete.
	 * 
	 * @throws ServicePersistenceException
	 */
	public void logiDelete(String tableName, Long id) throws ServicePersistenceException {
		logger.debug("Logic delete " + tableName);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		String sql = "update " + tableName + " set is_valid = 0 where id = :id";
		updateByNativeSql(sql, param);
	}

	public void delete(Class<?> clazz, Long id) {
		logger.debug("Logic delete " + clazz.getSimpleName());
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("update " + clazz.getSimpleName() + " set isValid=0 where id =" + ":id");
		q.setParameter("id", id);
		q.executeUpdate();
	}

	public void deleteAll(Class<?> clazz) {
		logger.debug("Delete all " + clazz.getSimpleName());
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("update " + clazz.getSimpleName() + " set isValid = 0");
		q.executeUpdate();
	}

	/**
	 * update.
	 * 
	 * @param t
	 *            t
	 */
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

	public List<?> findByNativeSqlToBean(String query, HashMap<String, Object> parameterMap, Class<?> clazz)
			throws ServicePersistenceException {
		logger.debug("Inside findByNativeSqlToBean.");

		Query q = null;
		try {
			q = sessionFactory.getCurrentSession().createSQLQuery(query).addEntity(clazz);
			if (parameterMap != null) {
				q = setParam(q, parameterMap);
			}
		} catch (Exception e) {
			logger.error("Failed to execute query: " + query, e);
			throw new ServicePersistenceException("Failed to execute query: " + query, e);
		}
		return q.list();
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

	public List<?> findByNativeSql(String query, HashMap<String, Object> parameterMap, Class<?> clazz,
			Integer startIndex, Integer size) throws ServicePersistenceException {
		logger.debug("Inside findByNativeSql.");

		Query q = null;
		try {
			q = sessionFactory.getCurrentSession().createSQLQuery(query).setFirstResult(startIndex).setMaxResults(size)
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

	public List<?> findByNativeSql(String query, HashMap<String, Object> parameterMap, Class<?> clazz,
			Integer startIndex, Integer size, OrderUtil... orders) throws ServicePersistenceException {
		logger.debug("Inside findByNativeSql.");

		Query q = null;
		try {
			if (orders != null && orders.length > 0) {
				String orderBy = " order by ";
				for (OrderUtil order : orders) {
					if (order.isAsc()) {
						orderBy += order.getFieldName() + " asc,";
					} else if (order.isDesc()) {
						orderBy += order.getFieldName() + " desc,";
					}
				}
				orderBy = orderBy.substring(0, orderBy.lastIndexOf(","));
				query += orderBy;
			}
			q = sessionFactory.getCurrentSession().createSQLQuery(query).setFirstResult(startIndex).setMaxResults(size)
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

	public List<?> findByHql(String query, HashMap<String, Object> parameterMap, Class<?> clazz)
			throws ServicePersistenceException {
		logger.debug("Inside findByHql.");

		Query q = null;
		try {
			q = sessionFactory.getCurrentSession().createQuery(query)
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

	/**
	 * findById.
	 * 
	 * @param type
	 *            type
	 * @param id
	 *            id
	 * @return Object
	 */
	public Object findById(Class<?> type, Long id) {
		return (sessionFactory.getCurrentSession().get(type, id));
	}

	/**
	 * findById.
	 * 
	 * @param type
	 *            type
	 * @param id
	 *            id
	 * @return Object
	 */
	public Object findById(Class<?> type, Integer id) {
		return (sessionFactory.getCurrentSession().get(type, id));
	}

	/**
	 * findById.
	 * 
	 * @param type
	 *            type
	 * @param id
	 *            id
	 * @return Object
	 */
	public Object findById(Class<?> type, String id) {
		return (sessionFactory.getCurrentSession().get(type, id));
	}

	public List<?> findByNamedQuery(String namedQuery, HashMap<String, Object> parameterMap)
			throws ServicePersistenceException {
		logger.debug("Inside find.");
		Query q = execQuery(namedQuery, parameterMap);
		return q.list();
	}

	/**
	 * find.
	 * 
	 * @param query
	 *            query
	 * @param parameterMap
	 *            parameterMap
	 * @return List<Object>
	 * @throws ConCarPersistenceException
	 *             e
	 */
	@SuppressWarnings("unchecked")
	public List<Object> find(String query, HashMap<String, Object> parameterMap) throws ServicePersistenceException {
		logger.debug("Inside find.");
		Query q = execQuery(query, parameterMap);
		return q.list();
	}

	public List<?> findByNativeSql(String query, HashMap<String, Object> parameterMap)
			throws ServicePersistenceException {
		logger.debug("Inside find.");
		Query q = execQuery(query, parameterMap);
		return q.list();
	}

	/**
	 * Execute update query.
	 * 
	 * @param query
	 *            query
	 * @param parameterMap
	 *            parameterMap
	 * @return int
	 * @throws ConCarPersistenceException
	 *             e
	 */
	public int execUpdate(String query, HashMap<String, Object> parameterMap) throws ServicePersistenceException {
		logger.debug("Inside executeUpdate.");
		Query q = execQuery(query, parameterMap);
		return q.executeUpdate();
	}

	public Long insertByNativeSql(String query, HashMap<String, Object> parameterMap)
			throws ServicePersistenceException {
		logger.debug("Inside insertByNativeSql.");
		Session session = sessionFactory.getCurrentSession();
		Query q = null;
		try {
			q = session.getNamedQuery(query);
			setParam(q, parameterMap).executeUpdate();
			q = session.createSQLQuery(LAST_INSERT_ID_SQL);
			return Long.valueOf(q.list().get(0).toString());
		} catch (Exception e) {
			logger.error("Failed to execute query.", e);
			throw new ServicePersistenceException("Failed to execute query.", e);
		}
	}

	public Integer getTotalPage(Class<?> clazz, int pageSize) {
		logger.debug("Inside getTotalPage for " + clazz.getSimpleName() + "  with page size " + pageSize);
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("select count(*) from " + clazz.getSimpleName() + " where isValid = 1");
		Long count = (Long) q.uniqueResult();
		if (pageSize % count != 0) {
			return (int) (count / pageSize + 1);
		} else {
			return (int) (count / pageSize);
		}

	}

	public List<?> search(Class<?> clazz, List<CriteriaPath> criteriaPathList, Integer startIndex, Integer size,
			OrderUtil... orders) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = getCriteria(session.createCriteria(clazz), clazz, criteriaPathList);

		// default set for self-object
		if (orders != null) {
			for (OrderUtil order : orders) {
				if (order.isAsc()) {
					criteria = criteria.addOrder(asc(order.getFieldName()));
				} else if (order.isDesc()) {
					criteria = criteria.addOrder(desc(order.getFieldName()));
				}
			}

		}

		criteria.setFirstResult(startIndex.intValue());
		criteria.setMaxResults(size);
		return criteria.list();
	}

	public List<?> search(Class<?> clazz, List<CriteriaPath> criteriaPathList, ProjectionList projection,
			Integer startIndex, Integer size, OrderUtil... orders) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = getCriteria(session.createCriteria(clazz), clazz, criteriaPathList);

		if (projection != null)
			criteria.setProjection(projection);

		// default set for self-object
		if (orders != null) {
			for (OrderUtil order : orders) {
				if (order.isAsc()) {
					criteria = criteria.addOrder(asc(order.getFieldName()));
				} else if (order.isDesc()) {
					criteria = criteria.addOrder(desc(order.getFieldName()));
				}
			}

		}

		criteria.setFirstResult(startIndex.intValue());
		criteria.setMaxResults(size);
		return criteria.list();
	}

	public Integer getTotalPage(Class<?> clazz, List<CriteriaPath> criteriaPathList) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = getCriteria(session.createCriteria(clazz), clazz, criteriaPathList);

		return (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult().hashCode();
	}

	private Criteria getCriteria(Criteria criteria, Class<?> clazz, List<CriteriaPath> criteriaPathList) {
		HashMap<String, Criteria> criteriaCache = new HashMap<String, Criteria>();
		if (criteriaPathList != null && criteriaPathList.size() >= 1) {
			for (CriteriaPath criteriaPath : criteriaPathList) {
				List<String> subPathList = criteriaPath.getSubPath();
				if (subPathList.size() > 1) {

					for (int i = 0; i < subPathList.size() - 1; i++) {
						String[] alias = subPathList.get(i).split("\\.");
						if (alias.length > 1) {

							Criteria c = criteriaCache.get(clazz.getSimpleName() + "." + alias[1].replace("+", ""));

							if (c == null) {
								if (alias[1].contains("+")) {
									criteria = criteria.createAlias(subPathList.get(i).replace("+", ""),
											alias[1].replace("+", ""), JoinType.LEFT_OUTER_JOIN);
									criteriaCache.put(clazz.getSimpleName() + "." + alias[1].replace("+", ""),
											criteria);
								} else {
									criteria = criteria.createAlias(subPathList.get(i).replace("+", ""),
											alias[1].replace("+", ""));
									criteriaCache.put(clazz.getSimpleName() + "." + alias[1].replace("+", ""),
											criteria);
								}
							}

						} else {

							Criteria c = criteriaCache.get(clazz.getSimpleName() + "." + alias[0].replace("+", ""));
							if (c == null) {
								if (alias[0].contains("+")) {
									criteria = criteria.createAlias(subPathList.get(i).replace("+", ""),
											alias[0].replace("+", ""), JoinType.LEFT_OUTER_JOIN);
									criteriaCache.put(clazz.getSimpleName() + "." + alias[0].replace("+", ""),
											criteria);
								} else {
									criteria = criteria.createAlias(subPathList.get(i).replace("+", ""),
											alias[0].replace("+", ""));
									criteriaCache.put(clazz.getSimpleName() + "." + alias[0].replace("+", ""),
											criteria);
								}
							}

						}

					}
				}

				Disjunction or = Restrictions.disjunction();
				List<Criterion> exps = criteriaPath.getExps();
				if (exps.size() > 1) {
					for (Criterion exp : exps) {
						or.add(exp);
					}
					criteria.add(or);
				} else if (exps.size() == 1) {
					criteria.add(exps.get(0));
				}
			}
		}

		// default set for self-object
		// criteria.add(Restrictions.eq("isValid", VALID));
		return criteria;
	}

	/**
	 * find.
	 * 
	 * @param query
	 *            query
	 * @param parameterMap
	 *            parameterMap
	 * @param limit
	 * @return List<Object>
	 * @throws ConCarPersistenceException
	 *             e
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findWithPage(String query, HashMap<String, Object> parameterMap, int limit, int start)
			throws ServicePersistenceException {
		logger.debug("Inside find.");
		Query q = execQuery(query, parameterMap);
		q.setMaxResults(limit);
		q.setFirstResult(start);
		return q.list();
	}

	public List<?> searchByNativeSql(String sql, HashMap<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query = (SQLQuery) setParam(query, params);
		return query.list();
	}

	public int updateByNativeSql(String sql, HashMap<String, Object> params) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query = (SQLQuery) setParam(query, params);
		return query.executeUpdate();
	}

	@Deprecated
	public Integer getCriteriaCount(Class<?> clazz, HashMap<String, List<Object>> params, FilterByTime filterByTime) {

		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(clazz);
		if (params != null) {
			Set<String> keys = params.keySet();
			List<String> aliasKeys = new ArrayList<String>();

			// support multi-rels
			for (String key : keys) {
				List<Object> values = params.get(key);
				if (values != null && values.size() > 1) {
					Disjunction or = Restrictions.disjunction();

					if (key.startsWith(SEARCH_BTW)) {
						key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
						or.add(Restrictions.between(key, values.get(0), values.get(1)));
					}

					for (Object value : values) {
						if (key.indexOf(SEARCH_REL) > 0) {
							String prefix = key.substring(0, key.indexOf(SEARCH_SEPE) + 1);
							key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
							String alias = "";
							String aliasKey = null;
							while (key.contains(SEARCH_REL)) {
								String parentKey = key.substring(0, key.indexOf(SEARCH_REL));
								key = key.substring(key.indexOf(SEARCH_REL) + 1);
								if (key.contains(SEARCH_REL)) {
									if (!"".equals(alias)) {
										if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
												.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
											aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
													.concat(parentKey));
										}
									} else {
										if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(parentKey, parentKey);
											aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
										}
									}
									alias = parentKey;
								} else {
									if (!"".equals(alias)) {
										if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
												.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
											aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
													.concat(parentKey));
										}
									} else {
										if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(parentKey, parentKey);
											aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
										}
									}
									aliasKey = parentKey.concat(SEARCH_REL).concat(key);
								}
							}

							if (prefix.equals(SEARCH_EQ)) {
								or.add(Restrictions.eq(aliasKey, value));
							} else if (prefix.equals(SEARCH_LIKE)) {
								or.add(Restrictions.like(aliasKey, value));
							} else {
								or.add(Restrictions.eq(aliasKey, value));
							}
						} else {
							if (key.startsWith(SEARCH_EQ)) {
								key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
								or.add(Restrictions.eq(key, value));
							} else if (key.startsWith(SEARCH_LIKE)) {
								key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
								or.add(Restrictions.like(key, value));
							} else {
								or.add(Restrictions.eq(key, value));
							}
						}
					}
					criteria.add(or);
				} else if (values != null && values.size() == 1) {
					if (key.indexOf(SEARCH_REL) > 0) {
						String prefix = key.substring(0, key.indexOf(SEARCH_SEPE) + 1);
						key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
						String alias = "";
						String aliasKey = null;
						while (key.contains(SEARCH_REL)) {
							String parentKey = key.substring(0, key.indexOf(SEARCH_REL));
							key = key.substring(key.indexOf(SEARCH_REL) + 1);
							if (key.contains(SEARCH_REL)) {
								if (!"".equals(alias)) {
									if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
											.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
										aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
												.concat(parentKey));
									}
								} else {
									if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(parentKey, parentKey);
										aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
									}
								}
								alias = parentKey;
							} else {
								if (!"".equals(alias)) {
									if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
											.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
										aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
												.concat(parentKey));
									}
								} else {
									if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(parentKey, parentKey);
										aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
									}
								}
								aliasKey = parentKey.concat(SEARCH_REL).concat(key);
							}
						}
						if (prefix.equals(SEARCH_EQ)) {
							criteria.add(Restrictions.eq(aliasKey, values.get(0)));
						} else if (prefix.equals(SEARCH_LIKE)) {
							criteria.add(Restrictions.like(aliasKey, values.get(0)));
						} else {
							criteria.add(Restrictions.eq(aliasKey, values.get(0)));
						}
					} else {
						if (key.startsWith(SEARCH_EQ)) {
							key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
							criteria.add(Restrictions.eq(key, values.get(0)));
						} else if (key.startsWith(SEARCH_LIKE)) {
							key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
							criteria.add(Restrictions.like(key, values.get(0)));
						} else {
							criteria.add(Restrictions.eq(key, values.get(0)));
						}
					}
				}
			}
		}

		// default set for self-object
		if (filterByTime != null) {
			criteria.add(Restrictions.between(filterByTime.getTimeFieldName(), filterByTime.getStartTime(),
					filterByTime.getEndTime()));
		}

		// default set for self-object
		// criteria.add(Restrictions.eq("isValid", VALID));
		return (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult().hashCode();

	}

	@Deprecated
	public List<?> searchByCriteria(Class<?> clazz, HashMap<String, List<Object>> params, Integer startIndex,
			Integer size, FilterByTime filterByTime, OrderUtil... orders) {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(clazz);
		if (params != null) {
			Set<String> keys = params.keySet();
			List<String> aliasKeys = new ArrayList<String>();

			// support multi-rels
			for (String key : keys) {
				List<Object> values = params.get(key);
				if (values != null && values.size() > 1) {
					Disjunction or = Restrictions.disjunction();

					if (key.startsWith(SEARCH_BTW)) {
						key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
						or.add(Restrictions.between(key, values.get(0), values.get(1)));
					}

					for (Object value : values) {
						if (key.indexOf(SEARCH_REL) > 0) {
							String prefix = key.substring(0, key.indexOf(SEARCH_SEPE) + 1);
							key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
							String alias = "";
							String aliasKey = null;
							while (key.contains(SEARCH_REL)) {
								String parentKey = key.substring(0, key.indexOf(SEARCH_REL));
								key = key.substring(key.indexOf(SEARCH_REL) + 1);
								if (key.contains(SEARCH_REL)) {
									if (!"".equals(alias)) {
										if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
												.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
											aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
													.concat(parentKey));
										}
									} else {
										if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(parentKey, parentKey);
											aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
										}
									}
									alias = parentKey;
								} else {
									if (!"".equals(alias)) {
										if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
												.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
											aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
													.concat(parentKey));
										}
									} else {
										if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
											criteria.createAlias(parentKey, parentKey);
											aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
										}
									}
									aliasKey = parentKey.concat(SEARCH_REL).concat(key);
								}
							}

							if (prefix.equals(SEARCH_EQ)) {
								or.add(Restrictions.eq(aliasKey, value));
							} else if (prefix.equals(SEARCH_LIKE)) {
								or.add(Restrictions.like(aliasKey, value));
							} else {
								or.add(Restrictions.eq(aliasKey, value));
							}
						} else {
							if (key.startsWith(SEARCH_EQ)) {
								key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
								or.add(Restrictions.eq(key, value));
							} else if (key.startsWith(SEARCH_LIKE)) {
								key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
								or.add(Restrictions.like(key, value));
							} else {
								or.add(Restrictions.eq(key, value));
							}
						}
					}
					criteria.add(or);
				} else if (values != null && values.size() == 1) {
					if (key.indexOf(SEARCH_REL) > 0) {
						String prefix = key.substring(0, key.indexOf(SEARCH_SEPE) + 1);
						key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
						String alias = "";
						String aliasKey = null;
						while (key.contains(SEARCH_REL)) {
							String parentKey = key.substring(0, key.indexOf(SEARCH_REL));
							key = key.substring(key.indexOf(SEARCH_REL) + 1);
							if (key.contains(SEARCH_REL)) {
								if (!"".equals(alias)) {
									if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
											.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
										aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
												.concat(parentKey));
									}
								} else {
									if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(parentKey, parentKey);
										aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
									}
								}
								alias = parentKey;
							} else {
								if (!"".equals(alias)) {
									if (!aliasKeys.contains(alias.concat(SEARCH_REL).concat(parentKey)
											.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(alias.concat(SEARCH_REL).concat(parentKey), parentKey);
										aliasKeys.add(alias.concat(SEARCH_REL).concat(parentKey).concat(SEARCH_SEPE)
												.concat(parentKey));
									}
								} else {
									if (!aliasKeys.contains(parentKey.concat(SEARCH_SEPE).concat(parentKey))) {
										criteria.createAlias(parentKey, parentKey);
										aliasKeys.add(parentKey.concat(SEARCH_SEPE).concat(parentKey));
									}
								}
								aliasKey = parentKey.concat(SEARCH_REL).concat(key);
							}
						}
						if (prefix.equals(SEARCH_EQ)) {
							criteria.add(Restrictions.eq(aliasKey, values.get(0)));
						} else if (prefix.equals(SEARCH_LIKE)) {
							criteria.add(Restrictions.like(aliasKey, values.get(0)));
						} else {
							criteria.add(Restrictions.eq(aliasKey, values.get(0)));
						}
					} else {
						if (key.startsWith(SEARCH_EQ)) {
							key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
							criteria.add(Restrictions.eq(key, values.get(0)));
						} else if (key.startsWith(SEARCH_LIKE)) {
							key = key.substring(key.indexOf(SEARCH_SEPE) + 1);
							criteria.add(Restrictions.like(key, values.get(0)));
						} else {
							criteria.add(Restrictions.eq(key, values.get(0)));
						}
					}
				}
			}
		}

		// default set for self-object
		if (filterByTime != null) {
			criteria.add(Restrictions.between(filterByTime.getTimeFieldName(), filterByTime.getStartTime(),
					filterByTime.getEndTime()));
		}

		// default set for self-object
		// criteria.add(Restrictions.eq("isValid", VALID));

		// default set for self-object
		if (orders != null) {
			for (OrderUtil order : orders) {
				if (order.isAsc()) {
					criteria = criteria.addOrder(asc(order.getFieldName()));
				} else if (order.isDesc()) {
					criteria = criteria.addOrder(desc(order.getFieldName()));
				}
			}

		}

		criteria.setFirstResult(startIndex.intValue());
		criteria.setMaxResults(size);
		return criteria.list();

	}

	/**
	 * Execute query.
	 * 
	 * @param query
	 *            query
	 * @param parameterMap
	 *            parameterMap
	 * @return Query
	 * @throws ConCarPersistenceException
	 *             e
	 */
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