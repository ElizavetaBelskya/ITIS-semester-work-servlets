package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.dao.RatingDao;
import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.io.IOException;

public class RatingService {

    private RatingDao dao;


    public RatingService(RatingDao dao) {
        this.dao = dao;
    }

    public float insertRate(int tutorId, int studentId, int rate) throws DbException {
        return dao.insertRate(tutorId, studentId, rate);
    }




}
