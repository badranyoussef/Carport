package app.services;

import app.exceptions.DatabaseException;
import app.model.dtos.DTOUserCarportOrder;
import app.model.entities.Carport;
import app.model.entities.Order;
import app.model.entities.Shed;
import app.model.entities.User;
import app.repository.ConnectionPool;
import app.repository.OrderRepository;
import app.utility.Calculator;
import io.javalin.http.Context;

public class FormService {



    public static DTOUserCarportOrder createOrder(Context ctx, User user, boolean loggedIn, ConnectionPool connectionPool) {
        int carportWidth = Integer.parseInt(ctx.formParam("carport_width"));
        int carportLength = Integer.parseInt(ctx.formParam("carport_length"));
        int carportHeight = Integer.parseInt(ctx.formParam("carport_height"));
        String note = ctx.formParam("note");

        Carport carport = new Carport(carportWidth, carportLength, carportHeight);
        Order order = new Order(note);

        String shedChoice = ctx.formParam("redskabsrum");
        if (shedChoice.equalsIgnoreCase("ja")) {
            int shedWidth = Integer.parseInt(ctx.formParam("shed_width"));
            int shedLength = Integer.parseInt(ctx.formParam("shed_length"));
            Shed shed = new Shed(shedWidth, shedLength);
            carport.setShed(shed);
        }

        DTOUserCarportOrder dto = new DTOUserCarportOrder(user, carport, order);
        float carportPrice = Calculator.carportPriceCalculator2(dto);

        try {
            if (loggedIn) {
                OrderRepository.addOrderToExistingUser(dto, carportPrice, connectionPool);
            } else {
                OrderRepository.addOrder(dto, carportPrice, connectionPool);
            }
        } catch (DatabaseException e) {
            // handle the error
        }
        return dto;
    }


}
