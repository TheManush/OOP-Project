package airlinemanagementsystem;

import javax.swing.*;

public interface ReservationAction {
    void fetchReservationDetails(String pnr); // Fetch reservation details
    void performAction(); // Perform the main action 
}