package fr.eni.encheres.bo;

public class Category {
    private int id;
    private String wording;

    public Category(int id, String wording) {
        this.id = id;
        this.wording = wording;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
}
