public class Main {
    public static void main(String[] args) throws Exception {
        eRyder bike1 = new eRyder();
        bike1.printBikeDetails();
        eRyder bike2 = new eRyder("1455zz",89,true,47.6f);
        bike2.printRideDetails(77);
        eRyder bike3 = new eRyder("SH13",30,true,30,"Aaaa",1253467328);
        bike3.printRideDetails(22);


        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";

        Feedback feedback = new Feedback("Wang", "er", "11111@example.com");
        feedback.analyseFeedback(true, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback); 
    }





}
