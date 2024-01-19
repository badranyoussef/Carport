package app.model.dtos;

import app.model.entities.Carport;
import app.model.entities.Shed;
import app.model.entities.User;

public class OrderDTO {
    private User user;
    private Carport carport;
    private Shed shed;

    public OrderDTO(User user, Carport carport, Shed shed) {
        this.user = user;
        this.carport = carport;
        this.shed = shed;
    }

    public User getUser() {
        return user;
    }

    public Carport getCarport() {
        return carport;
    }

    public Shed getShed() {
        return shed;
    }
}
