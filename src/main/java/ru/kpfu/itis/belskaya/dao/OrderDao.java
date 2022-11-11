package ru.kpfu.itis.belskaya.dao;

import ru.kpfu.itis.belskaya.entities.Order;
import ru.kpfu.itis.belskaya.entities.Tutor;
import ru.kpfu.itis.belskaya.exceptions.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private  Connection conn;

    public OrderDao(Connection conn) {
        this.conn = conn;
    }

    public void add(Order order) throws DbException {
        String request = "INSERT INTO orders(subject, author_id, description, gender, minrating, creationDate, tutor_id, online, price) Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(request)) {

            preparedStatement.setString(1, order.getSubject());
            preparedStatement.setInt(2, order.getAuthorId());
            preparedStatement.setString(3, order.getDescription());
            if (order.isTutorGender() == null) {
                preparedStatement.setNull(4, Types.BOOLEAN);
            } else {
                preparedStatement.setBoolean(4, order.isTutorGender());
            }
            preparedStatement.setFloat(5, order.getMinRating());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(order.getCreationDate()));
            if (order.getTutorId() == null) {
                preparedStatement.setNull(7, Types.INTEGER);
            } else {
                preparedStatement.setInt(7, order.getTutorId());
            }
            if (order.getOnline() == null) {
                preparedStatement.setNull(8, Types.BOOLEAN);
            } else {
                preparedStatement.setBoolean(8, order.getOnline());
            }
            preparedStatement.setInt(9, order.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error writing to the database", e);
        }

    }

    public List<Order> getStudentOrders(int studentId) throws DbException {
        List<Order> orders = new ArrayList<>();
        String request = "select * from orders where author_id = ?";
        try(PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = createOrderInstance(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DbException("Error getting order data from the database", e);
        }
    }

    public List<Order> getUncompletedStudentOrders(int studentId) throws DbException {
        List<Order> orders = new ArrayList<>();
        String request = "select * from orders where author_id = ? and tutor_id is null";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = createOrderInstance(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
    }

    public void approveTutor(int order_id, int tutor_id) throws DbException {
        String request = "update orders set tutor_id = ? where id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setInt(1, tutor_id);
            statement.setInt(2, order_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }

    }

    public List<Order> getTutorOrders(int tutorId) throws DbException {
        List<Order> orders = new ArrayList<>();
        String request = "select * from orders where tutor_id = ?";
        try(PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, tutorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = createOrderInstance(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            throw new DbException("Error getting data from the database", e);
        }
    }

    public List<Order> getSuitableOrders(Tutor tutor) throws DbException {
        String request = "select * from orders where (gender = ? or gender is null) and minrating <= ? and subject = ? and (tutor_id is null) and (((not online or online is null) and ? and ? = (select city from students where id = author_id)) or ((online is null or online) and ?))";
        List<Order> orders = new ArrayList<>();
        try {
            for (String subject: tutor.getSubjects()) {
                PreparedStatement statement = conn.prepareStatement(request);
                statement.setBoolean(1, tutor.isGender());
                statement.setFloat(2, tutor.getRating());
                statement.setString(3, subject);
                statement.setBoolean(4, !tutor.isWorkingOnline());
                statement.setString(5, tutor.getCity());
                statement.setBoolean(6, tutor.isWorkingOnline());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Order order = createOrderInstance(rs);
                    orders.add(order);
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private Order createOrderInstance(ResultSet rs) throws SQLException {
        Order order = new Order(
                rs.getInt("id"),
                rs.getString("subject"),
                rs.getInt("author_id"),
                rs.getString("description"),
                (rs.getObject("gender")) == null ? null : rs.getBoolean("gender"),
                rs.getFloat("minrating"),
                rs.getTimestamp("creationDate").toLocalDateTime(),
                rs.getObject("tutor_id") == null? null: rs.getInt("tutor_id"),
                rs.getObject("online") == null ? null : rs.getBoolean("online"),
                rs.getInt("price")
        );
        return order;
    }

    public void rejectTutor(int tutorId, int authorId) throws DbException {
        String request = "update orders set tutor_id = null where author_id = ? and tutor_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setInt(1, authorId);
            statement.setInt(2, tutorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
    }


    public void rejectOrder(int orderId) throws DbException {
        String request = "update orders set tutor_id = null where id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
    }

    public void deleteOrder(int id) throws DbException {
        String request = "delete from orders_to_tutors where order_id = ?; delete from orders where id = ?";
        try(PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error deleting data from the database", e);
        }
    }

    public boolean isCandidate(int tutorId, int orderId) throws DbException {
        String request = "select * from orders_to_tutors where order_id = ? and tutor_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(request)){
            statement.setInt(1, orderId);
            statement.setInt(2, tutorId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DbException("Error getting data from the database", e);
        }
    }

    public void takeOrder(int id, int tutorId) throws DbException {
        String request = "insert into orders_to_tutors(order_id, tutor_id) values (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(request)) {
            statement.setInt(1, id);
            statement.setInt(2, tutorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error writing to the database", e);
        }
    }


}
