package lt.ehu.student.aliencreatures.entity;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

public class Alien extends AbstractEntity {
    private String name;
    private String lor;
    private byte[] image;
    private String base64Image;
    private int userId;

    public String getBase64Image() {
        return base64Image;
    }

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;

        if (image != null) {
            this.base64Image = Base64.getEncoder().encodeToString(image);
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alien)) return false;
        Alien alien = (Alien) o;
        return userId == alien.userId && Objects.equals(name, alien.name) && Objects.equals(lor, alien.lor) && Objects.deepEquals(image, alien.image) && Objects.equals(base64Image, alien.base64Image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lor, Arrays.hashCode(image), base64Image, userId);
    }
}
