package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.dao.StudentDao;
import ru.kpfu.itis.belskaya.entities.Student;
import ru.kpfu.itis.belskaya.exceptions.DbException;
import ru.kpfu.itis.belskaya.exceptions.DuplicateEntryException;
import ru.kpfu.itis.belskaya.exceptions.MatchingEmailException;
import ru.kpfu.itis.belskaya.exceptions.UserNotFoundException;

import java.util.List;

public class StudentService extends UserService<Student> {
    private StudentDao dao;

    public StudentService(StudentDao dao) {
        this.dao = dao;
    }


    public void add(Student student) throws DbException, DuplicateEntryException, MatchingEmailException {
        if (hasUserWithSuchEmail(student.getEmail())) {
            throw new MatchingEmailException("A user with this email already exists");
        } else if (hasStudentWithSuchPhone(student.getPhone())) {
            throw new DuplicateEntryException("A user with this phone already exists");
        } else {
            dao.add(student);
        }
    }

    public List<Student> getAllStudents() throws DbException {
        List<Student> userList = dao.getAll();
        return userList;
    }

    public boolean hasUserWithSuchEmail(String email) throws DbException {
        if (dao.findUserByEmail(email) > 0) {
            return true;
        };
        return false;
    }

    public boolean hasStudentWithSuchPhone(String phone) throws DbException {
        if (dao.findUserByPhone(phone) > 0) {
            return true;
        }
        return false;
    }

    public Student signIn(String email, String password) throws DbException, UserNotFoundException {
        Student signedInUser = null;
        if (hasUserWithSuchEmail(email)) {
            int id = dao.findUserByEmail(email);
            if (dao.verifyPassword(password, id)) {
                signedInUser = getStudent(id);
            } else {
                throw new UserNotFoundException("Password is wrong");
            }
        } else {
            throw new UserNotFoundException("We can not find student with such email");
        }
        return signedInUser;
    }

    public Student getStudent(int id) throws DbException {
        return dao.findById(id);
    }

    public List<Student> getTutorStudents(int tutorId) throws DbException {
        return dao.getStudentsOfThisTutor(tutorId);
    }

}
