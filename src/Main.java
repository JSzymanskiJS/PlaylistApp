import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MusicLibrary library = new MusicLibrary();
        Playlist playlist = new Playlist(library);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int choice = 100;
        int levelOfIndentation = 0;

        System.out.println("Welcome in your playlist app!");
        while (!exit){
            playlist.printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        playlist.playStop();
                        break;
                    case 2:
                        playlist.next();
                        break;
                    case 3:
                        playlist.previous();
                        break;
                    case 4:
                        playlist.replay();
                        break;
                    case 5:
                        playlist.printPlaylist();
                        break;
                    case 6:
                        playlist.changeQueueTo();
                        break;
                    case 7:
                        playlist.addSong();
                        break;
                    case 8:
                        playlist.addSongFast();
                        break;
                    case 9:
                        playlist.removeSong();
                        break;
                    case 10:
                        library.enter();
                    case 11:
                        exit = true;
                        break;
                    default:
                        break;
                }
        }

    }
}
