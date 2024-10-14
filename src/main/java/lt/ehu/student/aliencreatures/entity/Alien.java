package lt.ehu.student.aliencreatures.entity;

public class Alien extends AbstractEntity {
    private String name;
    private String lor;

    public Alien() {}

    public Alien(String name, String lor) {
        this.name = name;
        this.lor = lor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLor() {
        return lor;
    }

    public void setLor(String lor) {
        this.lor = lor;
    }
}
