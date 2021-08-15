import java.util.ArrayList;
import java.util.Scanner;

public class Album {
    private String name;
    private ArrayList<Song> songs;
    private static long numberOfCreatedAlbums = 0;
    private Scanner scanner;

    public Album(String name, ArrayList<Song> songs) {
        setName(name);
        setArrayList(songs);
    }

    public Album(String name) {
        this(name, new ArrayList<Song>());
    }

    public Album() {
        this("This is Album No." + numberOfCreatedAlbums + "!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals(null) || name.equals("")) {
            System.out.println("This name is incorrect. Name of Album has not been changed.");
        } else {
            this.name = name;
        }
    }

    //todo reconsider returning NEW ArrayList
    public ArrayList<Song> getArrayList() {
        return songs;
    }
    public int getAlbumSize(){
        return songs.size();
    }

    //todo                                          |
    //todo personally... idunno if this is correct |
    //todo method, but... anyway...               \/
    public void setArrayList(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void addNewSong(int index, String title, int duration) {
        try {
            songs.add(index, new Song(title, duration));
        } catch (InvalidSongFileExeption e) {
            System.out.println("Invalid try to add a new Song. Song were NOT created.");
        }
    }

    public void addNewSong(String title, int duration) {
        try {
            songs.add(new Song(title, duration));
        } catch (InvalidSongFileExeption e) {
            System.out.println("Invalid try to add a new Song. Song were NOT created.");
        }
    }

    public void addNewSong() {
        System.out.print("Write title of new Song: ");
        String title = scanner.nextLine();
        System.out.print("Write duration of new Song (in seconds): ");
        int duration = scanner.nextInt();
        addNewSong(title, duration);
    }

    public Song getSong(int index) throws SongNotFoundExeption {
        if (index < 0){
            System.out.println("You've entered to '" + name + "' album. I'm going to list possible options for you:");
            printSongsTiltes();
            index = findSong();
        }
        if (index <= 0) {
            throw new SongNotFoundExeption();
        } else {
            return songs.get(index);
        }
    }
    public Song getSong() throws SongNotFoundExeption{
        return getSong(-1);
    }



    public void editSong(String title, int duration) {
        int choice = findSong();
        try {
            songs.get(choice).setTitle(title);
        } catch (InvalidSongFileExeption e) {
            System.out.println("Incorrect attempt to change the title - parameter will be NOT changed.");
        }
        try {
            songs.get(choice).setDuration(duration);
        } catch (InvalidSongFileExeption e) {
            System.out.println("Incorrect attempt to change the duration - parameter will be NOT changed.");
        }
    }
    public void editSong() {
        System.out.print("Write new title of Song: ");
        String title = scanner.nextLine();
        System.out.print("Write new duration of Song (in seconds): ");
        int duration = scanner.nextInt();
        editSong(title, duration);
    }


    public void deleteSong() {
        int choice = findSong();
        if (choice >= 0) {
            songs.remove(choice);
        } else {
            System.out.println("Invalid song delete attempt.");
        }
    }

    public void enter() {
        boolean exit = false;
        int choice;
        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewSong();
                    break;
                case 2:
                    printSongsTiltes();
                    break;
                case 3:
                    editSong();
                    break;
                case 4:
                    deleteSong();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    break;
            }
        } while (!exit);

    }

    public void printSongsTiltes(boolean withDuration) {
        System.out.println("Printing songs in album '" + name + "':");
        for (int i = 0; i < songs.size(); i++) {
            System.out.print("#" + i + 1 + " -> " + songs.get(i).getTitle());
            if (withDuration) {
                System.out.print(" >>> " + songs.get(i).getDuration() / 3600 + ":" + (songs.get(i).getDuration() % 3600) / 60 + ":" + songs.get(i).getDuration() % 60);
            }
            System.out.print("\n");
        }
        System.out.println("========== End of album ==========");
    }

    public void printSongsTiltes() {
        printSongsTiltes(false);
    }

    public int searchSong(String title) {
        for (int i = 0; i < songs.size(); i++) {
            if (title.equals(songs.get(i).getTitle())) {
                return i;
            }
        }
        return -1;
    }

    public int findSong() {
        System.out.print("Enter song position (for searching by title enter '0'): ");
        int choice = scanner.nextInt();
        if (choice <= 0) {
            System.out.print("Enter song title: ");
            String titleToFind = scanner.nextLine();
            choice = searchSong(titleToFind);
        }
        return choice;
    }


    public void printMenu(int levelOfIndentation) {
        String[] menu = {"1 -> Add new Song.",
                "2 -> List Songs.",
                "3 -> Edit Song.",
                "4 -> Delete Song.",
                "5 -> Quit."};
        for (String string : menu) {
            for (int i = 0; i < levelOfIndentation; i++) {
                System.out.print("\t");
            }
            System.out.print(string);
            System.out.print("\n");
        }
    }
    public void printMenu() {
        printMenu(0);
    }


}
