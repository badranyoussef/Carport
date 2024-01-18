package app.services;

import app.exceptions.DatabaseException;
import app.model.dtos.DTOOrderCustomer;
import app.model.dtos.DTOStatus;
import app.model.entities.Order;
import app.model.entities.User;
import app.repository.ConnectionPool;
import app.repository.OrderRepository;
import app.repository.StatusRepository;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class OrderService {


    public static void deleteOrder (int orderId, Context ctx, ConnectionPool connectionPool){
        try {
            OrderRepository.deleteOrderById(orderId, connectionPool);
            ctx.attribute("message", "Ordre er nu slettet");
            ctx.render("ordre-side.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", "Fejl sletning af ordre " + e.getMessage());
            ctx.render("ordre-side.html");
        }
    }

    public static void getAllOrders(User user, Context ctx, ConnectionPool connectionPool) {

        try {
            if (user.getRole() == 1) {
                List<Order> orders = getAllOrdersbyUserId(user, connectionPool);
                ctx.attribute("orderlist", orders);
                ctx.render("ordre-side.html");
            } else {
                List<DTOStatus> statusList = StatusRepository.getAllStatuses(connectionPool);
                List<DTOOrderCustomer> allOrders = OrderRepository.getAllOrders(connectionPool);

                ctx.attribute("statusList", statusList);
                ctx.attribute("allOrders", allOrders);
                ctx.render("ordre-side.html");
            }
        } catch (DatabaseException e) {
            ctx.attribute("message", "Fejl indlæsning af alle ordrer " + e.getMessage());
            ctx.render("dashboard.html");
        }
    }

    public static List<Order> getAllOrdersbyUserId(User user, ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orders = OrderRepository.getAllOrdersByUser(user, connectionPool);
        return orders;
    }





}
