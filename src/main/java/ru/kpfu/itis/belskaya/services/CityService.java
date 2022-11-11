package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.dao.CityDao;
import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.util.List;

public class CityService {
    private CityDao dao;

    public CityService(CityDao dao) {
        this.dao = dao;
    }

    public List<String> getCities() throws DbException {
        return dao.getAll();
    }

}
