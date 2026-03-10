public class Main {
    public static void main(String[] args) throws Exception {
        eRyder bike1 = new eRyder();
        bike1.printBikeDetails();
        eRyder bike2 = new eRyder("1455zz",89,true,47.6f);
        bike2.printRideDetails(77);
        eRyder bike3 = new eRyder("SH13",30,true,30,"Aaaa",1253467328);
        bike3.printRideDetails(22);
    }
}
