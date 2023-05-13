package mvc.models;

/**
 * Client object that represents the table client in my database
 */
public class Client {
    private int id;
    private String name;
    private int age;
    private String address;

    public Client(int id, String name, int age, String address)
    {
        this.address = address;
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Client(String name, int age, String address)
    {
        this.address = address;
        this.age = age;
        this.name = name;
    }
    public Client()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
