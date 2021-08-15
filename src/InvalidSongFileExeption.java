public class InvalidSongFileExeption extends Exception {
    /*Wiem, że to jest paskudnie nie potrzebne i mogłem to zrobić normalnie poprzez if'a,
    ale po prostu chciałem potrenować wyjątki w Javie, żeby zrozumieć jak one działają ;D.
    */
    public InvalidSongFileExeption() {
        System.out.println("ERROR >>> Song can NOT be successfully added to Album. :/ <<< ERROR");
    }
}
