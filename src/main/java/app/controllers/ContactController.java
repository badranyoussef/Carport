package app.controllers;

import app.services.ContactService;
import io.javalin.http.Context;

public class ContactController {
    public static void contact(Context ctx){
        String name = ctx.formParam("name");
        String phone = ctx.formParam("phone");
        String email = ctx.formParam("email");
        String message = ctx.formParam("message");

        try {
            ContactService.sendCustomerRequest(ctx, name, phone, email, message);
            ctx.attribute("message", "Din besked er sendt.");
            ctx.render("kontakt-indsendt.html");
        }catch(Exception e){
            ctx.attribute("message", "Der opstod en fejl: " + e.getMessage());
            ctx.render("kontakt-indsendt.html");
        }
    }
}
