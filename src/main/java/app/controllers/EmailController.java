package app.controllers;

import app.services.EmailService;
import io.javalin.http.Context;

import java.io.IOException;

public class EmailController {
    public static void sendOrderQuestion(Context ctx) {

        String name = ctx.formParam("name");
        String customerMail = ctx.formParam("email");
        String message = ctx.formParam("message");
        String orderId = ctx.formParam("order_id");

        try {
            EmailService.sendOrderQuestion(name, orderId, customerMail, message);
            String confirmation = "Tak for din besked. Vi vender retur indenfor 24 timer.";
            ctx.attribute("confirmation", confirmation);
            ctx.render("kontakt-indsendt.html");
        } catch (IOException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("kontakt-indsendt.html");
        }

    }

    public static void sendBill(Context ctx) {

        String customerName = ctx.formParam("full_name");
        String price = ctx.formParam("total_price");
        String employeeName = ctx.formParam("employeeName");
        String orderId = ctx.formParam("orderID");

        try {
            EmailService.sendBill(customerName, orderId, price, employeeName);
        } catch (IOException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("ordre-side.html");
        }
    }

    public static void sendOrderToSalesTeam(Context ctx, String name, int id) {

        String customerName = name;
        int ID = id;
        String length = ctx.formParam("carport_length");
        String width = ctx.formParam("carport_width");
        String height = ctx.formParam("carport_height");
        try {
            EmailService.sendOrderToSalesTeam(customerName, length, width, height, ID);
        } catch (IOException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("ordre-side.html");
        }
    }

    public static void sendMessageToSalesTeam(Context ctx, String customerName, String customerPhone, String customerEmail, String message) {
        try {
            EmailService.sendMessageToSalesTeam(customerName, customerPhone, customerEmail, message);
        } catch (Exception e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("ordre-side.html");
        }
    }


    private static void handleEmailException(Context ctx, IOException e, String errorView) {
        ctx.attribute("message", "Der opstod en fejl under afsendelsen: " + e.getMessage());
        ctx.render(errorView);
    }

}
