package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.util.List;

public interface InformationEntityDao {
    List<String> getAll() throws DbException;

}
