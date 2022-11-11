package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.dao.TutorDao;
import ru.kpfu.itis.belskaya.exceptions.MatchingEmailException;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.DuplicateEntryException;
import ru.kpfu.itis.belskaya.exceptions.UserNotFoundException;

import java.util.List;

public class TutorService extends UserService<Tutor> {
    private TutorDao dao;

    public TutorService(TutorDao dao) {
        this.dao = dao;
    }


    public void add(Tutor tutor) throws DbException, DuplicateEntryException, MatchingEmailException {
        if (hasUserWithSuchEmail(tutor.getEmail())) {
            throw new MatchingEmailException("A user with this email already exists");
        } else if (hasTutorWithSuchPhone(tutor.getPhone())) {
            throw new DuplicateEntryException("A user with this phone already exists");
        } else {
            dao.add(tutor);
        }
    }

    private boolean hasTutorWithSuchPhone(String phone) throws DbException {
        if (dao.findUserByPhone(phone) > 0) {
            return true;
        }
        return false;
    }

    public List<Tutor> getAllTutors() throws DbException {
        return dao.getAll();
    }

    public boolean hasUserWithSuchEmail(String email) throws DbException {
        if (dao.findUserByEmail(email) > 0) {
            return true;
        };
        return false;
    }

    public int getCountOfTutors(String subject) throws DbException {
        return dao.getCountOfTutorsBySubject(subject);
    }

    public Tutor signIn(String email, String password) throws DbException, UserNotFoundException {
        Tutor signedInUser = null;
        if (hasUserWithSuchEmail(email)) {
            int id = dao.findUserByEmail(email);
            if (dao.verifyPassword(password, id)) {
                signedInUser = getTutor(id);
            } else {
                throw new UserNotFoundException("Password is wrong");
            }
        } else {
            throw new UserNotFoundException("We can not find tutor with such email");
        }
        return signedInUser;
    }

    public Tutor getTutor(int id) throws DbException {
        return dao.findById(id);
    }

    public List<Tutor> getApprovedTutors(int studentId) throws DbException {
        return dao.getApprovedTutorsOfThisStudent(studentId);
    }

    public List<Tutor> getCandidatesByOrderId(int orderId)  throws DbException {
        return dao.getCandidatesByOrderId(orderId);
    }

    public void setTutorRating(int tutorId, float newRating) throws DbException {
        dao.setTutorRating(tutorId, newRating);
    }

    public List<Tutor> getPage(int count, int page) throws DbException {
        return dao.getPage(count, page);
    }

    public List<Tutor> getPageOrderByRatingWithSubject(int count, int page, String subject) throws DbException {
        return dao.getPageOrderByRatingWithSubject(count, page, subject);
    }


    public List<Tutor> getPageWithSubject(int count, int page, String subject) throws DbException {
        return dao.getPageWithSubject(count, page, subject);
    }

    public List<Tutor> getPageOrderByRating(int count, int page) throws DbException {
        return dao.getPageOrderByRating(count, page);
    }


}
