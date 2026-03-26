public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private String cvv;
    private String userType;
    private String[] lastThreeTrips;

    // Constructor
    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                            String cardNumber, String cardExpiryDate, String cardProvider,
                            String cvv, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }

    // Getters
    public String getFullName() { return fullName; }
    public String getEmailAddress() { return emailAddress; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getCardNumber() { return cardNumber; }
    public String getCardExpiryDate() { return cardExpiryDate; }
    public String getCardProvider() { return cardProvider; }
    public String getCvv() { return cvv; }
    public String getUserType() { return userType; }
    public String[] getLastThreeTrips() { return lastThreeTrips; }

    // Setters
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
    public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }

    // toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Full Name: ").append(fullName).append("\n");
        sb.append("Email: ").append(emailAddress).append("\n");
        sb.append("Date of Birth: ").append(dateOfBirth).append("\n");
        sb.append("Card Number: ").append(cardNumber).append("\n");
        sb.append("Card Expiry Date: ").append(cardExpiryDate).append("\n");
        sb.append("Card Provider: ").append(cardProvider).append("\n");
        sb.append("CVV: ").append(cvv).append("\n");
        sb.append("User Type: ").append(userType).append("\n");
        sb.append("Last Three Trips:\n");
        for (String trip : lastThreeTrips) {
            sb.append("  - ").append(trip).append("\n");
        }
        return sb.toString();
    }
}
