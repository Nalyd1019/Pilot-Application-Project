package model;

import java.util.ArrayList;
import java.util.List;

public class Pilot {
    private Logbook logbook = new Logbook();
    private List<License> licenses = new ArrayList<>();
    private String password;
    private String name;
    private String email;

    public Pilot(String password1, String password2, String name, String email) {
        if (password1.equals(password2)) {               //så att användaren skriver in rätt lösen båda gångerna
            password = password1;
            this.name = name;
            this.email = email;
        }
    }
    protected boolean nameSet(){
        return name!=null;
    }

    public boolean validateLogin(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }
}

