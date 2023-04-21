package co.app.mercaditodesofi.model;

public class City {

    private int id;
    private String departament, city;

    public City(int id, String departament, String city) {
        this.id = id;
        this.departament = departament;
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
