import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY =25.0;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;
    
    private String fullName;          
    private String emailAddress;      
    private String dateOfBirth;       
    private long cardNumber;          
    private String cardProvider;      
    private String cardExpiryDate;    
    private double feeToCharge;      
    private int cvv;  
    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;  

    public boolean analyseCardExpiryDate(){
         return true;
    }

    
    public void registration() {
        System.out.println("Welcome to the ERyder Registration");
        System.out.println("1.Register as a Regular User");
        System.out.println("2.Register as a VIP User");
        System.out.print("Please enter your choice(1 or 2)");
        Scanner sc =new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine(); 

        if (choice ==1){
            userType="Regular User";
        }else{
            userType = "VIP User";
        }
        
        
        System.out.print("fullname:");
        fullName=sc.nextLine();

        System.out.print("email adress:");
        emailAddress = sc.nextLine();
        emailValid = analyseEmail();

        System.out.print("birthday(YYYY-MM-DD):");
        dateOfBirth =sc.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.print("card number(only VISA、MasterCard、American Express):");
        cardNumber = sc.nextLong();
        sc.nextLine();
        cardNumberValid = analyseCardNumber();

        System.out.print("card expiry date( MM/YY,eg. 12/25):");
        cardExpiryDate = sc.nextLine();
        cardStillValid = analyseCardExpiryDate();

        System.out.print("card CVV(3-4 words):");
        cvv = sc.nextInt();
        sc.nextLine();
        validCVV = analyseCVV();

        finalCheckpoint();

        sc.close();
    }


        private boolean analyseCVV() {
            return true;
        }

        private boolean analyseCVV(int cvv){
        String cvvStr = Integer.toString(cvv);
        if(cardProvider.equals("American Express")&&cvvStr.length()==4||cardProvider.equals("VISA")&&cvvStr.length()==3||cardProvider.equals("MasterCard")&&cvvStr.length()==3){
            System.out.println("Card CVV is valid.");
            validCVV=true;
        }else {
            System.out.println("Invalid CVV for the given card. Going back to the start of the registration process.");
            validCVV=false;
        }
        registration();
        return validCVV;
    }


        private boolean analyseEmail() {
        if (emailAddress.contains("@") && emailAddress.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration(); 
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate today = LocalDate.now();
        int age = Period.between(dob, today).getYears();
        boolean isBirthday = (dob.getMonth() == today.getMonth() && dob.getDayOfMonth() == today.getDayOfMonth());

        minorAndBirthday = false;
        minor = false;

        if (userType.equals("VIP user")) {
            if (isBirthday && age <= 18 && age > 12) {
                System.out.println("Happy Birthday!You get 25% discount on the VIP subscription fee for being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && age <= 18 && age > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }

         if (age <= 12 || age > 120) {
            System.out.println("Looks like you are either too young or already dead.Sorry,you cannot be our user.Have a nice day!");
            System.exit(0); 
        }

        return true;
    }

    private boolean analyseCardNumber() {
        String cardNumStr = String.valueOf(cardNumber);
        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        }
        
        else if (cardNumStr.length() == 16 &&
                ((firstTwoDigits >= 51 && firstTwoDigits <= 55) ||
                        (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        }
        
        else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        }

        else {
            System.out.println("Sorry,but we accept only VISA,MasterCard,or American Express cards.please try again with a valid card.Going back to the start of the registration.");
            registration(); 
            return false;
        }
    }

    private boolean analyseCardExpiryDate1() {
        int month = Integer.parseInt(cardExpiryDate.substring(0, 2));
        int year = 2000 + Integer.parseInt(cardExpiryDate.substring(3, 5));

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid.");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card. Going back to the start of the registration process...");
            registration();
            return false;
        }
    }


     private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("Sorry,your registration was unsuccessful due to the following reasons.");
            if (!emailValid) System.out.println("emailValid is false.");
            if (!ageValid) System.out.println("ageValid is false.");
            if (!cardNumberValid) System.out.println("cardNumberValid is false.");
            if (!cardStillValid) System.out.println("cardStillValid is false.");
            if (!validCVV) System.out.println("CVV is false.");
            System.out.println("Going back to the start.");
            registration();
        }
    }

     private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        System.out.println("Thank you for your payment.your card(last four digits" + lastFourDigits + ")value stored in feeToCharge " + feeToCharge );
    }

    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "Registration successful! Here are your details:\n" +
                "User Type: " + userType + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email Address: " + emailAddress + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Card Number: " + censoredNumber + "\n" +
                "Card Provider: " + cardProvider + "\n" +
                "Card Expiry Date: " + cardExpiryDate;
    }

}

    





















    
