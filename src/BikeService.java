import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BikeService {
    private String emailAddress;
    private LocalDateTime tripStartTime;
    private boolean locationValid;
    UserRegistration userRegistration = new UserRegistration();
    LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    
    private Stack<ERyderLog> systemLogs = new Stack<>();
    
    private Queue<BikeRequest> bikeRequest = new ArrayDeque<>();
    
    public void reserveBike(String bikeID, String userEmail, String location){
        if(bikeID!=null){
            for(Bike bike:BikeDatabase.bikes){
                if(bikeID.equals(bike.getBikeID())){
                    tripStartTime = LocalDateTime.now();
                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println(" Reserving the bike with the "+bikeID+". Please following the on-screen instructions." + 
                                        "to locate the bike and start your pleasant journey.");
                    ActiveRental activeRental = new ActiveRental(bikeID,userEmail,tripStartTime);
                    activeRentalsList.add(activeRental);
                    
                    String logId = "BR" + (int)(Math.random() * 900 + 100);
                    String eventDesc = "Bike " + bikeID + " rented by user from " + location;
                    ERyderLog log = new ERyderLog(logId, eventDesc, LocalDateTime.now());
                    systemLogs.push(log);
                    
                    String tripStartLogId = "TS" + (int)(Math.random() * 900 + 100);
                    String tripStartEvent = "Trip started - Bike " + bikeID + " departed from " + location;
                    ERyderLog tripStartLog = new ERyderLog(tripStartLogId, tripStartEvent, LocalDateTime.now());
                    systemLogs.push(tripStartLog);
                    
                    break;
                }
            }
        }
        else{
            System.out.println(" Sorry, we're unable to reserve a bike at this time. Please try again later.");
            BikeRequest request = new BikeRequest(userEmail, location, LocalDateTime.now());
            bikeRequest.offer(request);
            System.out.println("Your request has been added to the waiting queue.");
        }
    }
    
    public void reserveBike(String bikeID){
        reserveBike(bikeID, emailAddress, "Unknown");
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
        String location = "";
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while(iterator.hasNext()){
            ActiveRental rental = iterator.next();
            if(rental.getBikeID().equals(bikeID)){
                location = getBikeLocation(bikeID);
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
                
                String tripEndLogId = "TE" + (int)(Math.random() * 900 + 100);
                String tripEndEvent = "Trip ended - Bike " + bikeID + " returned at " + location;
                ERyderLog tripEndLog = new ERyderLog(tripEndLogId, tripEndEvent, LocalDateTime.now());
                systemLogs.push(tripEndLog);
                
                break;
            }
        }
        
        if(!bikeRequest.isEmpty()){
            BikeRequest nextRequest = bikeRequest.poll();
            System.out.println("Processing next request in queue for user: " + nextRequest.getUserEmail());
            String availableBikeId = validateLocation(nextRequest.getLocation());
            if(availableBikeId != null){
                reserveBike(availableBikeId, nextRequest.getUserEmail(), nextRequest.getLocation());
            }
        }
    }
    
    private String getBikeLocation(String bikeID) {
        for(Bike bike : BikeDatabase.bikes){
            if(bike.getBikeID().equals(bikeID)){
                return bike.getLocation();
            }
        }
        return "Unknown";
    }
    
    public void viewSystemLogs(){
        if(systemLogs.isEmpty()){
            System.out.println("No system logs available.");
        } else {
            System.out.println("=== System Logs ===");
            for(ERyderLog log : systemLogs){
                System.out.println(log.toString());
            }
        }
    }
    
    public void viewBikeRequestQueue(){
        if(bikeRequest.isEmpty()){
            System.out.println("No pending bike requests in the queue.");
        } else {
            System.out.println("=== Pending Bike Requests ===");
            for(BikeRequest request : bikeRequest){
                System.out.println(request.toString());
            }
        }
    }
    
    public void updateBikeRequestQueue(){
        if(bikeRequest.isEmpty()){
            System.out.println("Queue is empty. No requests to remove.");
        } else {
            BikeRequest removed = bikeRequest.poll();
            System.out.println("Removed request from queue: " + removed.toString());
        }
    }
    
    public Stack<ERyderLog> getSystemLogs(){
        return systemLogs;
    }
    
    public Queue<BikeRequest> getBikeRequest(){
        return bikeRequest;
    }
}
