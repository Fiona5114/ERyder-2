import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
public class UserService {
    List<RegisteredUsers> registeredUsers = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public void addNewUsers() {
        System.out.println("\n=== Add New Users ===");
        System.out.println("How many users would you like to add? ");
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            System.out.println("\n=== Adding User " + (i + 1) + " ===");
            
    
            System.out.print("Enter full name: ");
            String fullName = sc.nextLine();
            
            System.out.print("Enter email address: ");
            String emailAddress = sc.nextLine();
            
            System.out.print("Enter date of birth (YYYY-MM-DD): ");
            String dateOfBirth = sc.nextLine();
            
            System.out.println("Enter card number: ");
            long cardNumber = sc.nextLong();
            sc.nextLine();
            
            System.out.print("Enter card provider: ");
            String cardProvider = sc.nextLine();
            
            System.out.print("Enter card expiry date (MM/YY): ");
            String cardExpiryDate = sc.nextLine();
            
            System.out.println("Enter CVV: ");
            int cvv = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Enter user type: ");
            String userType = sc.nextLine();
            
            String[] lastThreeTrips = new String[3];
            
            for (int j = 0; j < 3; j++) {
                System.out.println("\n=== Trip " + (j + 1) + " ===");
                
                System.out.print("Enter date of trip (YYYY-MM-DD): ");
                String tripDate = sc.nextLine();
                
                System.out.print("Enter source: ");
                String source = sc.nextLine();
                
                System.out.print("Enter destination: ");
                String destination = sc.nextLine();
                
                System.out.println("Enter fare (€): ");
                double fare = sc.nextDouble();
                sc.nextLine();
                
                System.out.print("Enter feedback (or press Enter for NULL): ");
                String feedback = sc.nextLine();
                if (feedback.isEmpty()) {
                    feedback = null;
                }
                
                StringBuilder tripBuilder = new StringBuilder();
                tripBuilder.append("Date: ").append(tripDate)
                          .append(", Source: ").append(source)
                          .append(", Destination: ").append(destination)
                          .append(", Fare (€): ").append(fare)
                          .append(", Feedback: ").append(feedback);
                
                lastThreeTrips[j] = tripBuilder.toString();
            }
            

            RegisteredUsers newUser = new RegisteredUsers(fullName, emailAddress, dateOfBirth, 
                                                          cardNumber, cardProvider, cardExpiryDate, 
                                                          cvv, userType, lastThreeTrips);
            

            registeredUsers.add(newUser);
            System.out.println("User added successfully!");
        }
    }
    
    public void viewRegisteredUsers() {
        System.out.println("\n=== View Registered Users ===");
        
        if (registeredUsers.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        
        int userNumber = 1;
        for (RegisteredUsers user : registeredUsers) {
            System.out.println("\n=== User " + userNumber + " ===");
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Email: " + user.getEmailAddress());
            System.out.println("Date of Birth: " + user.getDateOfBirth());
            System.out.println("Card Number: " + user.getCardNumber());
            System.out.println("Card Provider: " + user.getCardProvider());
            System.out.println("Card Expiry: " + user.getCardExpiryDate());
            System.out.println("CVV: " + user.getCvv());
            System.out.println("User Type: " + user.getUserType());
            

            String[] trips = user.getLastThreeTrips();
            System.out.println("\nLast Three Trips:");
            for (int i = 0; i < trips.length; i++) {
                if (trips[i] != null) {
                    System.out.println("Trip " + (i + 1) + ": " + trips[i]);
                }
            }
            userNumber++;
        }
    }
    
    public void removeRegisteredUsers() {
        if(registeredUsers.isEmpty())System.out.println("No registered users to remove.");
        else{
            System.out.println("Please enter the email: ");
            String emailAddress = sc.nextLine();
            Iterator<RegisteredUsers> iterator = registeredUsers.iterator();
            boolean found=false;
            while(iterator.hasNext()){
                RegisteredUsers user = iterator.next();
                if(user.getEmailAddress().equals(emailAddress)){
                    iterator.remove();
                    found=true;
                    break;
                }
            }
            if(!found)System.out.println("No user found with this email address");
            
        }
    }
    
    public void updateRegisteredUsers() {
        if(registeredUsers.isEmpty())System.out.println("No registered users to remove");
        else{
            System.out.println("Please enter the email: ");
            String emailAddress = sc.nextLine();
            Iterator<RegisteredUsers> iterator = registeredUsers.iterator();
            boolean found = false;
            while(iterator.hasNext()){
                RegisteredUsers user = iterator.next();
                if(emailAddress.equals(user.getEmailAddress())){
                    System.out.println("Type new full name: (Press ENTER for no change)");
                    String fullName = sc.nextLine();
                    if(!fullName.isEmpty())user.setFullName(fullName);

                    System.out.println("Type new date of time: (Press ENTER for no change)");
                    String DoT = sc.nextLine();
                    if(!DoT.isEmpty())user.setDateOfBirth(DoT);

                    System.out.println("Type new card of number: (Press 0 for no change)");
                    Long cardOfNumber = sc.nextLong();
                    sc.nextLine();
                    if(cardOfNumber!=0)user.setCardNumber(cardOfNumber);

                    
                    System.out.println("Type new card provider: (Press ENTER for no change)");
                    String cardProvider = sc.nextLine();
                    if(!cardProvider.isEmpty())user.setCardProvider(cardProvider);

                    System.out.println("Type new card expiry: (Press ENTER for no change)");
                    String cardExpiryDate = sc.nextLine();
                    if(!cardExpiryDate.isEmpty())user.setCardExpiryDate(cardExpiryDate);

                    System.out.println("Type new CVV: (Press 0 for no change)");
                    int cvv = sc.nextInt();
                    sc.nextLine();
                    if(!cardExpiryDate.isEmpty())user.setCvv(cvv);
                    
                    System.out.println("Type new User Type: (Press ENTER for no change)");
                    String userType = sc.nextLine();
                    if(!userType.isEmpty())user.setUserType(userType);

                    found = true;
                    break;
                }
            } 
            if(!found)System.out.println("No user found with this email address");
        }
        sc.close();
    }
}
