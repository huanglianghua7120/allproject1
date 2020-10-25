package saaf.common.fmw.genform.model.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.dao.CommonDAO_HI_DY;

import com.yhg.hibernate.core.dao.ViewObjectDynamicFormImpl;


@Component("genDynamicFormDAO")
public class GenDynamicFormDAO extends CommonDAO_HI_DY {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenDynamicFormDAO.class);

    public GenDynamicFormDAO() {
        super();
    }

    public String createOrAlterTableEntity(String tableName, String createTableSQL, String alterTableSql) {
        Connection conn = null;
        try {
            conn = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();

            boolean executeResult_ = true;
            if (isExistsTable(tableName)) {
                //表存在，更新表结构
                executeResult_ = conn.prepareStatement(alterTableSql).execute();
            } else {
                //表不存在，创建表
                executeResult_ = conn.prepareStatement(createTableSQL).execute();
            }

            if (!(executeResult_)) {
                return ViewObjectDynamicFormImpl.CREATE_UPDATE_TABLE_SUCCESS;
            }
            return ViewObjectDynamicFormImpl.CREATE_UPDATE_TABLE_FAIL;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return ViewObjectDynamicFormImpl.CREATE_UPDATE_TABLE_FAIL;
        //return createTableEntity(tableName, createTableSQL);
    }

    /**
     * 检查表是否存在
     * @param tableName 表名
     * @return
     */
    public boolean isExistsTable(String tableName) {
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = getHibernateTemplate().getSessionFactory().getCurrentSession().connection();
            DatabaseMetaData metaDate = conn.getMetaData();
            rs = metaDate.getTables(null, null, tableName, new String[] { "TABLE" });
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                ////e.printStackTrace();
                LOGGER.error("", e);
            }
        }
        return false;
    }
}
