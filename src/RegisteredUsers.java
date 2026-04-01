public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips;

    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                            long cardNumber2, String cardExpiryDate, String cardProvider,
                            int cvv2, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber2;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv2;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }

    public String getFullName() { return fullName; }
    public String getEmailAddress() { return emailAddress; }
    public String getDateOfBirth() { return dateOfBirth; }
    public long getCardNumber() { return cardNumber; }
    public String getCardExpiryDate() { return cardExpiryDate; }
    public String getCardProvider() { return cardProvider; }
    public int getCvv() { return cvv; }
    public String getUserType() { return userType; }
    public String[] getLastThreeTrips() { return lastThreeTrips; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setCardNumber(Long cardOfNumber) { this.cardNumber = cardOfNumber; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }
    public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }
    public void setCvv(int cvv2) { this.cvv = cvv2; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setLastThreeTrips(String[] lastThreeTrips) { this.lastThreeTrips = lastThreeTrips; }

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
