package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    public String name;
    public String email;
    private String password;
    private String role;
    public String token;

    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = "";
        this.token = "";
    }

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = generateJsonWebToken();
    }

    public User(String email, String password) {
        System.out.println("jwt User= " + email);
        this.email = email;
        this.password = password;
        this.token = generateJsonWebToken();
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private void setToken() {
        this.token = generateJsonWebToken();
        System.out.println("jwt this.token  = " + this.token);
    }

    public boolean checkJTW(String password, String JWT) {
        // en su aplicacion buscaamos User en la DB y username y password

        User testUser = new User(password, password);
        String token = testUser.token;
        if (token.equals(JWT)) {
            return true;
        }
        return false;
    }

    public String generateJsonWebToken() {
        try {
            String username = this.email;
            String password = this.password;
            // Secret key is hardcoded just for demonstration purposes
            String mySecreString = "NUestroPassword";
            // Concatenate username, password, and secret key
            String dataToHash = "" + username + password + mySecreString;
            System.out.println("jwt = " + username);

            // Use SHA-256 algorithm to hash the data
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            // Convert hashed bytes to a hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println("jwt = " + hexString.toString());

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the exception appropriately in a real-world scenario
        }
    }

    @Override
    public String toString() {
        return "User [name=" + this.name + ", email=" + this.email + ", password=" + this.password + ", token="
                + this.token + ", role="
                + this.role + "]";
    }

}
