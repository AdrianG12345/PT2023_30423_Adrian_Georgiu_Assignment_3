package mvc.models;

public class Order {
    private int id;
    private int clientId;///foreign key
    private int productId;///foreign key
    private int quantity;

    public Order(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
