package app.services;

import app.controllers.EmailController;
import io.javalin.http.Context;

public class ContactService {

    public static void sendCustomerRequest(Context ctx, String name, String phone, String email, String message){
        try {
            EmailController.sendMessageToSalesTeam(ctx, name, phone, email, message);
            String confirmationMsg = "Din besked er sendt.";

            ctx.attribute("message", confirmationMsg);
            ctx.render("kontakt-indsendt.html");
        } catch (Exception e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("fejlside.html");
        }
    }

}
