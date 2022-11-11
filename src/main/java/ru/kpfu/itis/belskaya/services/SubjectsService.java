package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.dao.DatabaseConnectionService;
import ru.kpfu.itis.belskaya.dao.SubjectDao;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import java.sql.Connection;
import java.util.List;

public class SubjectsService {
    private SubjectDao dao;

    public SubjectsService(SubjectDao dao) {
        this.dao = dao;
    }

    public List<String> getSubjects() throws DbException {
        return dao.getAll();
    }

}
