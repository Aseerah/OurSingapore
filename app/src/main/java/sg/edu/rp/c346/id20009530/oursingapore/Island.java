package sg.edu.rp.c346.id20009530.oursingapore;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String name;
    private String description;
    private int square;
    private int stars;

//    public Island(String name, String description, int square, int stars) {
//        this.name = name;
//        this.description = description;
//        this.square = square;
//        this.stars = stars;
//    }

    public Island(int id, String name, String description, int square, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.square = square;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Island setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Island setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Island setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getSquare() {
        return square;
    }

    public Island setSquare(int square) {
        this.square = square;
        return this;
    }

    public int getStars() {
        return stars;
    }


    public Island setStars(int stars) {
        this.stars = stars;
        return this;
    }
}
