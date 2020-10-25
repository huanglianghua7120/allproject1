//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yhg.hibernate.core.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateViewObjectUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateViewObjectUtil.class);

    private HibernateViewObjectUtil() {
    }

    public static Query setParameter(Query query, Map<String, Object> parameterMap) {
        String[] namedParameters = query.getNamedParameters();
        if (null != namedParameters && namedParameters.length > 0) {
            if (null != parameterMap) {
                Iterator iterator = parameterMap.keySet().iterator();

                while(iterator.hasNext()) {
                    String key = (String)iterator.next();
                    query.setParameter(key, parameterMap.get(key));
                }
            }

            return query;
        } else {
            return query;
        }
    }

    public static Query setParameter(Query query, Object... params) {
        if (null != params) {
            for(int i = 0; i < params.length; ++i) {
                if (null != params[i]) {
                    query.setParameter(i, params[i]);
                }
            }
        }

        return query;
    }

    public static String parseSelectCount(String queryString) {
        String select_ = " select ";
        String from_ = " from ";
        String hql = queryString.toLowerCase();
        int noBlankStart = 0;

        int pair;
        for(pair = hql.length(); noBlankStart < pair && hql.charAt(noBlankStart) <= ' '; ++noBlankStart) {
            ;
        }

        pair = 0;
        if (!followWithWord(hql, select_, noBlankStart)) {
            pair = 1;
        }

        int fromPos = -1;
        int i = noBlankStart;

        while(i < hql.length()) {
            if (followWithWord(hql, select_, i)) {
                ++pair;
                i += select_.length();
            } else if (followWithWord(hql, from_, i)) {
                --pair;
                if (pair == 0) {
                    fromPos = i;
                    break;
                }

                i += from_.length();
            } else {
                ++i;
            }
        }

        if (fromPos == -1) {
            throw new IllegalArgumentException("parse count sql error, check your sql/hql");
        } else {
            String countHql = "select count(*) " + queryString.substring(fromPos);
            return countHql;
        }
    }

    public static Long object2Long(Object countResult) {
        Long rowsCount = 0L;
        if (countResult instanceof BigInteger) {
            rowsCount = ((BigInteger)countResult).longValue();
        } else if (countResult instanceof Long) {
            rowsCount = (Long)countResult;
        } else if(countResult instanceof BigDecimal){
            rowsCount = (Long)((BigDecimal) countResult).longValue();
        }

        return rowsCount;
    }

    private static boolean followWithWord(String s, String sub, int pos) {
        LOGGER.info(sub + "\t " + pos);

        int i;
        for(i = 0; pos < s.length() && i < sub.length(); ++i) {
            if (s.charAt(pos) != sub.charAt(i)) {
                return false;
            }

            ++pos;
        }

        if (i < sub.length()) {
            return false;
        } else if (pos >= s.length()) {
            return true;
        } else {
            return !isAlpha(s.charAt(pos));
        }
    }

    private static boolean isAlpha(char c) {
        return c == '_' || '0' <= c && c <= '9' || 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }

    public static void main(String[] args) {
        Object[] values = new Object[1];
    }
}
