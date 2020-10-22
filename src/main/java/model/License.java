
package model;

import java.time.LocalDate;


/**
 * @author Malin Rosén
 * License represents a license that a pilot has to have in order to fly an airplane.
 */

public class License {

    private String licenseName;
    private String expirationDate;
    private boolean soonExpired = false;
    private boolean expired = false;

    LocalDate date = LocalDate.now();

    /**
     * The constructor that creates a license.
     * @param licenseName The name of the license.
     * @param expirationDate The day the license expires.
     */

    License(String licenseName, LocalDate expirationDate){
        this.licenseName = licenseName;
        this.expirationDate = expirationDate.toString();
        checkIfExpiredSoon();
        checkIfExpired();
    }

    /**
     * A method that checks if a license is about to expire.
     */
    private void checkIfExpiredSoon(){
        for (int i = 0; i < 7; i++){
            if (date.plusDays(i).toString().equals(expirationDate)){
                soonExpired = true;
            }
        }
    }

    /**
     * A method that checks if a license is expired.
     */
    private void checkIfExpired(){
        if(date.isAfter(LocalDate.parse(expirationDate))) {
            expired = true;
        }
    }

    /**
     * A boolean that confirms whether a license is soon to be expired.
     * @return Returns true if checkIfExpiredSoon set the variable soonExpired to true.
     */
    public boolean isSoonExpired() {
        checkIfExpiredSoon();
        return soonExpired;
    }

    /**
     * A boolean that confirms whether a license is expired.
     * @return Returns true if checkIfExpired set the variable expired to true.
     */
    public boolean isExpired() {
        checkIfExpired();
        return expired;
    }


    //Getters and setters

    public String getLicenseName() {
        return licenseName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}






