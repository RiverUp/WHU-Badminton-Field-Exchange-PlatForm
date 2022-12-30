package org.example.entities;

public class User {
    public int id;
    public String name;
    public String passwords;
    public int creditworthiness;
    public boolean identity;
    public boolean blocked;

    public User(int id, String name, String passwords, int creditworthiness, boolean identity, boolean blocked) {
        this.id = id;
        this.name = name;
        this.passwords = passwords;
        this.creditworthiness = creditworthiness;
        this.identity = identity;
        this.blocked = blocked;
    }

    public User() {
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

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public int getCreditworthiness() {
        return creditworthiness;
    }

    public void setCreditworthiness(int creditworthiness) {
        this.creditworthiness = creditworthiness;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwords='" + passwords + '\'' +
                ", creditworthiness=" + creditworthiness +
                ", identity=" + identity +
                ", blocked=" + blocked +
                '}';
    }
}
