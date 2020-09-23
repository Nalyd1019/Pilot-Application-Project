package model;



import java.util.ArrayList;
import java.util.List;

public class Pilot {
   private Logbook logbook = new Logbook();
    private List<License> licenses = new ArrayList<>();
    private String password;
    private String name;
    private String email;
    //private PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

    public Pilot(String password1, String password2, String name, String email) {
        if (password1.equals(password2)) {               //så att användaren skriver in rätt lösen båda gångerna
            password = password1;
       //     password = passwordAuthentication.hash(password1.toCharArray());
            this.name = name;
            this.email = email;
        }
    }

    /**
     * Tests if the instance variable "name" is set
     * @return true if it is set, else return false
     */
    protected boolean nameSet(){
        return name!=null;
    }

    /**
     *  Method that checks if a given combination of email and password matches the data in the instance
     * @param email the email the user tries to login with
     * @param password the password the user tries to login with
     * @return true if the given combination matches the data, else returns false
     */
    public boolean validateLogin(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
  //      return this.email.equals(email) && passwordAuthentication.authenticate(password.toCharArray(), this.password);
    }

    public int getTotalNStarts(){
        return logbook.getNumberOfStarts(email);
    }


    public Logbook getLogbook() {
        return logbook;
    }
}

