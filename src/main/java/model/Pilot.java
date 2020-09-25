package model;



import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Pilot {
   private Logbook logbook = new Logbook();
    private List<License> licenses = new ArrayList<>();
    private String password;
    private String name;
    private String email;
    //private PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

    //TODO - lägg till instansvariabel som är startHours?

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
        return logbook.getPilotNumberOfStarts(email);
    }


    public Logbook getLogbook() {
        return logbook;
    }


    /* TODO - Såhär kan metoden som ger flygtid se ut om man har en variabel starttid i Pilot
    public int getPilotFlightTime(){
       int startHoursToMinutes = startHours * 60;
       return logbook.getPilotTotalMinutes() + startHoursToMinutes;
    }

     */

    public void addLicense(String name, LocalDate expirationDate){
        License license = new License(name, expirationDate);
        licenses.add(license);
    }

    public void checkLicenseExpiration(){
        List<License> licenses = new ArrayList<>();

        for (License license : licenses){
            if (license.isSoonExpired()){
                System.out.println("Den här licensen kommer snart gå ut");
            } else if (license.isExpired()){
                System.out.println("Den här licensen har gått ut");
            } else {
                System.out.println("Den här licensen går inte ur snart");
            }
        }
    }

      public List<License> getLicenses() {
            return licenses;
    }

    public String getEmail() {
        return email;
    }
}

