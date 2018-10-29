package com.jaagro.component.web.filter;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.jaagro.utils.UuidUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * cat 监控数据库连接池
 *
 * @author tony
 */
public class DataSourceFilter extends FilterAdapter {

    @Override
    public DruidPooledConnection dataSource_getConnection(FilterChain chain, DruidDataSource dataSource,
                                                          long maxWaitMillis) throws SQLException {
        try {
            Map params = new HashMap();
            params.put("MaxActive", dataSource.getMaxActive());
            params.put("ActiveCount", dataSource.getActiveCount());
            params.put("PoolingCount", dataSource.getPoolingCount());
            params.put("ConnectCount", dataSource.getConnectCount());
            params.put("InitialSize", dataSource.getInitialSize());
            params.put("MaxIdle", dataSource.getMaxIdle());
            params.put("MinIdle", dataSource.getMinIdle());
            params.put("MaxWait", dataSource.getMaxWait());
            String eventName = "connectionId:(" + dataSource.getID() + ")-SerialID:(" + UuidUtils.getUuid() + ")";
            Cat.logEvent("DruidDataSource", eventName, Event.SUCCESS, JSONObject.toJSONString(params));
        } catch (Exception e) {

        }

        return chain.dataSource_connect(dataSource, maxWaitMillis);
    }
}
