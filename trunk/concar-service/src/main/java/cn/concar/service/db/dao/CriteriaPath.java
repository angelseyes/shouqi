package cn.concar.service.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import cn.concar.service.util.ServiceConstants;
import cn.concar.service.util.StringUtils;

public class CriteriaPath {
	public static String EQ = "eq_";
	public static String LT = "lt_";
	public static String GT = "gt_";
	public static String BTW = "btw_";
	public static String LIKE = "like_";
	public static String SQL = "sql_";
	public static String IN = "in_";
	public static Object[] params;
	private String path;
	private List<String> subPath = new ArrayList<String>();
	private String op;
	private List<Criterion> exps = new ArrayList<Criterion>();

	public CriteriaPath(String path, Object... params) {
		this.path = path;

		String[] pathList = this.path.split("\\.");

		op = pathList[0].split("_")[0] + "_";

		String parent = "";
		for (String p : pathList) {
			if (StringUtils.isEmpty(parent)) {
				p = p.split("_")[1];
				subPath.add(p);
			} else {
				subPath.add(parent + "." + p);
			}
			parent = p;
		}

		if (params != null && params.length > 0) {
			if (StringUtils.match(op, EQ)) {
				if (params.length > 0) {
					for (Object param : params) {
						if (param instanceof String && StringUtils.match((String) param, ServiceConstants.NULL)) {
							exps.add(Restrictions.eqOrIsNull(subPath.get(subPath.size() - 1).replace("+", ""), null));
						} else {
							exps.add(Restrictions.eq(subPath.get(subPath.size() - 1).replace("+", ""), param));
						}
					}
				}

			} else if (StringUtils.match(op, LT)) {
				for (Object param : params) {
					if (param instanceof String && StringUtils.match((String) param, ServiceConstants.NULL)) {
						exps.add(Restrictions.isNull(subPath.get(subPath.size() - 1).replace("+", "")));
					} else {
						exps.add(Restrictions.le(subPath.get(subPath.size() - 1).replace("+", ""), params[0]));
					}
				}

			} else if (StringUtils.match(op, GT)) {
				exps.add(Restrictions.gt(subPath.get(subPath.size() - 1).replace("+", ""), params[0]));
			} else if (StringUtils.match(op, BTW)) {
				exps.add(Restrictions.between(subPath.get(subPath.size() - 1).replace("+", ""), params[0], params[1]));
			} else if (StringUtils.match(op, LIKE)) {
				if (params.length > 0) {
					for (Object param : params) {
						exps.add(
								Restrictions.like(subPath.get(subPath.size() - 1).replace("+", ""), "%" + param + "%"));
					}
				}
			} else if (StringUtils.match(op, IN)) {
				exps.add(Restrictions.in(subPath.get(subPath.size() - 1).replace("+", ""), (ArrayList<?>) params[0]));
			} else if (StringUtils.match(op, SQL)) {
				exps.add(Restrictions.sqlRestriction(params[0].toString()));
			}
		}

	}

	public List<String> getSubPath() {
		return subPath;
	}

	public List<Criterion> getExps() {
		return exps;
	}

}
