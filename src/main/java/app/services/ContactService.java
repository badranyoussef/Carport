package app.services;

import app.controllers.EmailController;
import io.javalin.http.Context;

public class ContactService {

    public static void sendCustomerRequest(Context ctx, String name, String phone, String email, String message){
            EmailController.sendMessageToSalesTeam(ctx, name, phone, email, message);
    }

}
