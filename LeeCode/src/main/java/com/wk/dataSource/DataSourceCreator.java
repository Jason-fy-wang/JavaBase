package com.wk.dataSource;

import javax.sql.DataSource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class DataSourceCreator {
    private DataSourceWrapper dataSourceWrapper;



    public void scheduleCreateSource(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                // Create data source here
//                DataSource dataSource = DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver")
//                        .url("jdbc:mysql://127.0.0.1:3306/test_orders?useSSL=false&useUnicode=true&characterEncoding=UTF-8")
//                        .username("root")
//                        .password("admin").build();
//                dataSourceWrapper = new DataSourceWrapper(dataSource);
            }
        }, 0, java.util.concurrent.TimeUnit.SECONDS);
    }



    public DataSourceWrapper getDataSourceWrapper() {
        return dataSourceWrapper;
    }

    public void setDataSourceWrapper(DataSourceWrapper dataSourceWrapper) {
        this.dataSourceWrapper = dataSourceWrapper;
    }
}
