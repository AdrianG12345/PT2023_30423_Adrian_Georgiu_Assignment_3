package mvc.models;

/**
 * Orders object that represents the orders table in my database
 */
public class Orders {
    private int id;
    private int clientId;///foreign key
    private int productId;///foreign key
    private int quantity;

    public Orders(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public Orders(int clientId, int productId, int quantity)
    {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public Orders()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
