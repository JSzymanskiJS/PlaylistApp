public class SongNotFoundExeption extends Exception {
    public SongNotFoundExeption() {
        System.out.println("ERROR >>> Song can NOT be found. :/ <<< ERROR");
    }
}
