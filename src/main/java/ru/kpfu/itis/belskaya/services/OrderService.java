package ru.kpfu.itis.belskaya.services;

import ru.kpfu.itis.belskaya.dao.OrderDao;
import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.util.List;

public class OrderService {
    private OrderDao dao;

    public OrderService(OrderDao dao) {
        this.dao = dao;
    }

    public void addOrder(Order order) throws DbException {
        dao.add(order);
    }

    public List<Order> getStudentOrders(int id) throws DbException {
        return dao.getStudentOrders(id);
    }

    public List<Order> getTutorOrders(int id) throws DbException {
        return dao.getTutorOrders(id);
    }

    public List<Order> getSuitableOrders(Tutor tutor) throws DbException {
        return dao.getSuitableOrders(tutor);
    }

    public List<Order> getUncompletedStudentOrders(int studentId) throws DbException {
        return dao.getUncompletedStudentOrders(studentId);
    }

    public void approveTutor(int orderId, int tutorId) throws DbException {
        dao.approveTutor(orderId, tutorId);
    }

    public void rejectTutor(int tutorId, int authorId) throws DbException {
        dao.rejectTutor(tutorId, authorId);
    }

    public void rejectOrder(int orderId) throws DbException {
        dao.rejectOrder(orderId);
    }

    public void deleteOrder(int orderId) throws DbException {
        dao.deleteOrder(orderId);
    }

    public boolean isCandidate(int tutorId, int orderId) throws DbException {
        return dao.isCandidate(tutorId, orderId);
    }

    public void takeOrder(int orderId, int tutorId) throws DbException {
        dao.takeOrder(orderId, tutorId);
    }
}
