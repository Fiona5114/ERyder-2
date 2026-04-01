import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
public class BikeService {
    private String emailAddress;
    private LocalDateTime tripStartTime;
    private boolean locationValid;
    UserRegistration userRegistration = new UserRegistration();
    LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    RentalService rentalService = new RentalService();
    public void reserveBike(String bikeID){
        if(bikeID!=null){
            for(Bike bike:BikeDatabase.bikes){
                if(bikeID.equals(bike.getBikeID()))tripStartTime = LocalDateTime.now();
                bike.setAvailable(false);
                bike.setLastUsedTime(tripStartTime);
                System.out.println(" Reserving the bike with the "+bikeID+". Please following the on-screen instructions." + 
                                        "to locate the bike and start your pleasant journey.");
                ActiveRental activeRental = new ActiveRental(bikeID,emailAddress,tripStartTime);
                activeRentalsList.add(activeRental);
                break;
            }
        }
        else{
            System.out.println(" Sorry, we’re unable to reserve a bike at this time. Please try again later.");
        }
    }
    public void viewActiveRentals(){
        if(activeRentalsList.isEmpty())System.out.println("No active rentals at the moment.");
        else {
            for(ActiveRental activeRental:activeRentalsList){
                System.out.println(activeRental);
            }
        }
    }
    public String validateLocation(String location){
        for(Bike bike : BikeDatabase.bikes){
            if(location.equals(bike.getLocation()) && bike.isAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }

        }
        System.out.println("Sorry, no bikes are available at the location you" + 
                        "requested. Please try again later.");
        return null;
    }
    public void removeTrip(String bikeID){
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while(iterator.hasNext()){
            ActiveRental rental = iterator.next();
            if(rental.getBikeID().equals(bikeID)){
                iterator.remove();
                break;
            }
        }
        Iterator<Bike>bikeIterator = BikeDatabase.bikes.iterator();
        while(bikeIterator.hasNext()){
            Bike bike = bikeIterator.next();
            if(bike.getBikeID().equals(bikeID)){
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }
}
