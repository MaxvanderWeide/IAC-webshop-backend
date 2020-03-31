package com.persitence;

import java.sql.Connection;

public class IACDAOImpl extends BaseDAO implements IACDAO{
    @Override
    public String getThings() {
        Connection conn = getConnection();
        System.out.println(conn);
        return "hey";
    }
}
