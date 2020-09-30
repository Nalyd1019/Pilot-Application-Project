
package model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class License {
    public static final String MEDICAL = "Medical License";
    public static final String FLIGHT = "Flight License";

    private String licenseName;
    private String expirationDate;
    private boolean soonExpired = false;
    private boolean expired = false;

    LocalDate date = LocalDate.now();



    public License(String licenseName, LocalDate expirationDate){
        this.licenseName = licenseName;
        this.expirationDate = expirationDate.toString();
        checkIfExpiredSoon();
        checkIfExpired();
    }


    public void checkIfExpiredSoon(){
        for (int i = 0; i < 7; i++){
            if (date.plusDays(i).toString().equals(expirationDate)){
                soonExpired = true;
            }
        }
    }

    public void checkIfExpired(){
        if(date.isAfter(LocalDate.parse(expirationDate))) {
            expired = true;
        }
    }


    public boolean isSoonExpired() {
        return soonExpired;
    }

    public boolean isExpired() {
        return expired;
    }
}






