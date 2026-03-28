import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.Iterator;

public class BikeRental {
private boolean isRegisteredUser;
private String emailAddress;
private String location;
private LocalDateTime tripStartTime;
private String bikeID;
private boolean locationValid;
private UserRegistration userRegistration;
private LinkedList<ActiveRental> activeRentalsList;
    
public BikeRental(boolean isRegisteredUser, String emailAddress, String location, LocalDateTime tripStartTime
        ,String bikeID,boolean locationValid,UserRegistration userRegistration,LinkedList<ActiveRental> activeRentalsList) {
        this.isRegisteredUser = isRegisteredUser;
        this.emailAddress = emailAddress;
        this.location = location;
        this.tripStartTime = tripStartTime;
        this.bikeID = bikeID;
        this.locationValid = locationValid;
        this.userRegistration = new UserRegistration();
        this.activeRentalsList = new LinkedList<>();
    }

    public void simulateApplicationInput() {
        Scanner scanner = new Scanner(System.in);
        // 1. 显示流程开始提示
        System.out.println("This is the simulation of the e-bike rental process.");
        // 2. 接收用户输入
        System.out.print("Is this user registered? (true/false): ");
        isRegisteredUser = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Please enter your email address: ");
        emailAddress = scanner.nextLine();
        System.out.print("Please enter your current location: ");
        location=scanner.nextLine();

        System.out.println(" Simulating the analysis of the rental request.");
        this.bikeID=analyseRequest(isRegisteredUser,emailAddress,location);
        if(locationValid){
            System.out.println("Simulating e-bike reservation…");
            reserveBike(bikeID);
            System.out.println("Displaying the active rentals…");
            viewActiveRentals();
            System.out.println("Simulating the end of the trip…");
            removeTrip(bikeID);
            System.out.println("Displaying the active rentals after trip end…");
            viewActiveRentals();
        }else return;
        bikeID=analyseRequest(isRegisteredUser,emailAddress,location);
    }


private String analyseRequest(boolean isRegistered,String emailAddress,String location) {
        if(isRegistered){
            System.out.println(" Welcome back, ("+emailAddress+")!");
        }else{
            System.out.println(" You’re not our registered user. Please consider registering.");
            userRegistration.registration();
        }
        return validate(location);
    }
    private String validate(String location) {
        return bikeID;
    }

private String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you\n" +
                "requested. Please try again later.");
        return null;
    }



private void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println("Reserving the bike with the ( " + bikeID + ")." +
                            "\n Please following the on-screen instructions to locate the bike and start your pleasant journey.");
                    ActiveRental activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        }else System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.");
    }

private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println(" No active rentals at the moment.");
        } else {
            System.out.println("Current active rentals:");
            for (ActiveRental activeRental : activeRentalsList) {
                System.out.println(activeRental);
            }
        }
     }


private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental activeRental = iterator.next();
            if (activeRental.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    bike.setAvailable(true);
                    bike.setLastUsedTime(LocalDateTime.now());
                    System.out.println(" Your trip has ended. Thank you for riding with us.");
                    break;
                }
            }
        }
    }

}
    
