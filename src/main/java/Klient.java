//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.Serializable;

public class Klient implements Serializable {
    private String name;
    private String surname;

    public Klient(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return "Klient: " + this.name+ " " + this.surname;
    }
}
