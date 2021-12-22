package com.wp.model.sqlprovider;

import com.wp.entities.YuML;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Classname YuMLProvider
 * @Description TODO
 * @Date 2019/5/3 9:17
 * @Created by wangpeng116
 */
public class YuMLProvider {
    public String getByProvider(@Param("yuML") YuML yuML) {
        return new SQL() {
            {
                SELECT("*");
                FROM("YU_ML");
                WHERE("ID=#{yuML.id}");
            }
        }.toString();
    }
}
