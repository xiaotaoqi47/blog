package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Region;
import com.niit.web.blog.factory.DaoFactory;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class RegionDaoTest {
    private RegionDao regionDao = DaoFactory.getRegionDaoInstance();

    @Test
    public void selectAll() throws SQLException {
        List<Region> regionList = regionDao.selectAll();
        System.out.println(regionList.size());
    }
}