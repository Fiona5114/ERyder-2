import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class RentalService {
    BikeService bikeService = new BikeService();
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private String bikeID;
    private boolean locationValid;
    UserRegistration userRegistration = new UserRegistration();
    LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    
    private static final double BASE_FARE = 3.0;
    
    public void simulateApplicationInput(RegisteredUsers user){
        System.out.println("This is the simulation of the e-bike rental process.");
        Scanner sc = new Scanner(System.in);
        isRegisteredUser = sc.nextBoolean();
        sc.nextLine();
        emailAddress = sc.nextLine();
        location = sc.nextLine();
        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser,emailAddress,location);
        if(!locationValid)
        {
            sc.close();
            return;
        }
        System.out.println("Simulating e-bike reservation…");
        bikeService.reserveBike(bikeID, emailAddress, location);
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        bikeService.removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
        sc.close();

    }
    private String analyseRequest(boolean isRegistered,String emailAddress,String location){
        if(isRegistered)System.out.println("Welcome back, "+emailAddress+"!");
        else{
            System.out.println(" You’re not our registered user. Please consider registering.");
            userRegistration.registration();
            
        }
        return bikeService.validateLocation(location);
    }
    private void viewActiveRentals(){
        if(activeRentalsList.isEmpty())System.out.println("No active rentals at the moment.");
        else {
            for(ActiveRental activeRental:activeRentalsList){
                System.out.println(activeRental);
            }
        }
    }
}