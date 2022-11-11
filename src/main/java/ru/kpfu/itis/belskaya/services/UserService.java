package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.entities.Student;
import ru.kpfu.itis.belskaya.entities.User;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.UserNotFoundException;

public abstract class UserService<T extends User> {

    public abstract T signIn(String email, String password) throws DbException, UserNotFoundException;

    public abstract boolean hasUserWithSuchEmail(String email) throws DbException;

}
