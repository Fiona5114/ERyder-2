import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("How many users would you like to add? ");
        int numUsers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numUsers; i++) {
            System.out.println("\n--- Enter details for user " + (i + 1) + " ---");

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Card Number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Card Expiry Date: ");
            String cardExpiryDate = scanner.nextLine();

            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();

            System.out.print("CVV: ");
            String cvv = scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            // Collect last three trips
            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\n--- Enter details for trip " + (j + 1) + " ---");
                System.out.print("Trip Date (YYYY-MM-DD): ");
                String tripDate = scanner.nextLine();

                System.out.print("Source: ");
                String source = scanner.nextLine();

                System.out.print("Destination: ");
                String destination = scanner.nextLine();

                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();

                System.out.print("Feedback (can be NULL): ");
                String feedback = scanner.nextLine();

                StringBuilder tripSb = new StringBuilder();
                tripSb.append("Date: ").append(tripDate)
                      .append(", Source: ").append(source)
                      .append(", Destination: ").append(destination)
                      .append(", Fare (€): ").append(fare)
                      .append(", Feedback: ").append(feedback);

                lastThreeTrips[j] = tripSb.toString();
            }

            RegisteredUsers newUser = new RegisteredUsers(fullName, emailAddress, dateOfBirth,
                    cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
            registeredUsersList.add(newUser);
            System.out.println("User added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }

        System.out.println("\n--- Registered Users ---");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        System.out.print("Enter email address of user to remove: ");
        String email = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully!");
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }

        System.out.print("Enter email address of user to update: ");
        String email = scanner.nextLine();
        RegisteredUsers userToUpdate = null;

        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(email)) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\n--- Update user details (press ENTER to keep old value, enter 0 for no change to numbers) ---");

        System.out.print("Type new full name: (Press ENTER for no change) ");
        String fullName = scanner.nextLine();
        if (!fullName.isEmpty()) {
            userToUpdate.setFullName(fullName);
        }

        System.out.print("Type new email address: (Press ENTER for no change) ");
        String emailAddress = scanner.nextLine();
        if (!emailAddress.isEmpty()) {
            userToUpdate.setEmailAddress(emailAddress);
        }

        System.out.print("Type new date of birth: (Press ENTER for no change) ");
        String dateOfBirth = scanner.nextLine();
        if (!dateOfBirth.isEmpty()) {
            userToUpdate.setDateOfBirth(dateOfBirth);
        }

        System.out.print("Type new card number: (Enter 0 for no change) ");
        String cardNumber = scanner.nextLine();
        if (!cardNumber.equals("0")) {
            userToUpdate.setCardNumber(cardNumber);
        }

        System.out.print("Type new card expiry date: (Press ENTER for no change) ");
        String cardExpiryDate = scanner.nextLine();
        if (!cardExpiryDate.isEmpty()) {
            userToUpdate.setCardExpiryDate(cardExpiryDate);
        }

        System.out.print("Type new card provider: (Press ENTER for no change) ");
        String cardProvider = scanner.nextLine();
        if (!cardProvider.isEmpty()) {
            userToUpdate.setCardProvider(cardProvider);
        }

        System.out.print("Type new CVV: (Enter 0 for no change) ");
        String cvv = scanner.nextLine();
        if (!cvv.equals("0")) {
            userToUpdate.setCvv(cvv);
        }

        System.out.print("Type new user type: (Press ENTER for no change) ");
        String userType = scanner.nextLine();
        if (!userType.isEmpty()) {
            userToUpdate.setUserType(userType);
        }

        System.out.println("User updated successfully!");
    }

     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BikeRental bikeRental = new BikeRental(false, null, null, null, null, false, null, null);
    }



}
