package mvc.models;

public class Client {
    private int idClient;
    private String name;
    private int age;
    private String address;

    public Client(int id, String name, int age, String address)
    {
        this.address = address;
        this.idClient = id;
        this.age = age;
        this.name = name;
    }
    public Client(String name, int age)
    {
        this.age = age;
        this.name = name;
    }
    public Client()
    {

    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
