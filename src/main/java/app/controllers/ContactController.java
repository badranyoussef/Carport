package app.controllers;

import app.services.ContactService;
import io.javalin.http.Context;

public class ContactController {
    public static void contact(Context ctx){
        String name = ctx.formParam("name");
        String phone = ctx.formParam("phone");
        String email = ctx.formParam("email");
        String message = ctx.formParam("message");
        ContactService.sendCustomerRequest(ctx, name, phone, email, message);
    }

}
