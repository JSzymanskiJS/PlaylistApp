import java.util.ArrayList;
import java.util.Scanner;

public class MusicLibrary {
    private ArrayList<Album> albums;
    private Scanner scanner;

    public MusicLibrary(ArrayList<Album> albums) {
        setAlbums(albums);
    }
    public MusicLibrary(){
        this(new ArrayList<Album>());
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
    public int getAlbumsSize(){
        return albums.size();
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum() {
        System.out.println("On which position should new Album appear? Write it down: ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice < albums.size()) {
            albums.add(choice, new Album());
        }
    }

    public Album getAlbum(int index) {
        return albums.get(index);
    }

    public void editAlbum() {
        int choice = findAlbum();
        if (choice >= 0 && choice < albums.size()) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            if (newName == null || newName.equals("")) {
                System.out.println("Incorrect attempt to change the name - parameter will be NOT changed.");
            } else {
                albums.get(choice).setName(newName);
                System.out.println("\nAlbum name has been successfully changed to '" + newName + "'.");
            }
        } else {
            System.out.println("Editing command was stopped. Incorrect Song title/index.");
        }
    }

    public void deleteAlbum() {
        int choice = findAlbum();
        if (choice >= 0 && choice < albums.size()) {
            System.out.println("Are you sure you want to DELETE this album? (enter 'y' to continue or anything else to stop).");
            String bool = scanner.nextLine();
            if (bool.equals("y")) {
                albums.remove(choice);
                System.out.println("Album has been successfully deleted.");
            } else {
                System.out.println("Deleting command was stopped.");
            }
        } else {
            System.out.println("Deleting command was stopped.");
        }
    }

    public void printAlbumsNames(boolean withSongs) {
        System.out.println("Printing songs in music Library:");
        for (int i = 0; i < albums.size(); i++) {
            System.out.printf("#####" + i + 1 + " -> " + albums.get(i).getName());
            if (withSongs) {
                System.out.printf("\n");
                albums.get(i).printSongsTiltes();
            }
            System.out.printf("\n");
        }
        System.out.println("========== End of album ==========");
    }
    public void printAlbumsNames() {
        printAlbumsNames(false);
    }


    public Song getSongFromAlbum() throws SongNotFoundExeption {
        System.out.print("Choose album to enter: ");
        int choice = findAlbum();
        if (choice >= 0 && choice < albums.size()) {
            Album album = albums.get(choice);
            return album.getSong();
        } else {
            System.out.println("Opening album command was stopped.");
            throw new SongNotFoundExeption();
        }
    }
    public void openAlbum() {
        System.out.print("Choose album to enter: ");
        int choice = findAlbum();
        if (choice >= 0 && choice < albums.size()) {
            Album album = albums.get(choice);
            album.enter();
        } else {
            System.out.println("Opening album command was stopped.");
        }
    }

    public int searchAlbum(String name) {
        for (int i = 0; i < albums.size(); i++) {
            if (name.equals(albums.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    public int findAlbum() {
        System.out.println("Write number of album to change (write '0' for searching by name):");
        int choice = scanner.nextInt();
        if (choice <= 0) {
            System.out.println("Write name of album: ");
            String nameToFind = scanner.nextLine();
            choice = searchAlbum(nameToFind);
        }
        return choice;
    }

    public void enter() {
        System.out.println("You've entered to music library. I'm going to list possible options for you:");
        boolean exit = false;
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Do you wanna list Albums with songs in it? (write 'y' for Yes or anything else for No): ");
                    String string = scanner.nextLine();
                    if (string.equals("y")) {
                        printAlbumsNames(true);
                    } else {
                        printAlbumsNames();
                    }
                    break;
                case 2:
                    openAlbum();
                    break;
                case 3:
                    addAlbum();
                    break;
                case 4:
                    editAlbum();
                    break;
                case 5:
                    deleteAlbum();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);
    }


    private void printReadMenu(int levelOfIndentation){
        String[] menu = {"1 -> List Albums.",
                "2 -> Open Album.",
                "3 -> Quit."};
        for (String string: menu) {
            for (int i = 0; i < levelOfIndentation; i++){
                System.out.print("\t");
            }
            System.out.print(string);
            System.out.print("\n");
        }
    }
    private void printReadMenu(){
        printReadMenu(0);
    }

    public void printMenu(int levelOfIndentation){
        String[] menu = {"1 -> List Albums.",
                "2 -> Open Album.",
                "3 -> Add Album.",
                "4 -> Edit Album.",
                "5 -> Delete Album.",
                "6 -> Quit."};
        for (String string: menu) {
            for (int i = 0; i < levelOfIndentation; i++){
                System.out.print("\t");
            }
            System.out.print(string);
            System.out.print("\n");
        }
    }
    public void printMenu(){
        printMenu(0);
    }
}


