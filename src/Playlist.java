import java.util.LinkedList;
import java.util.Scanner;

public class Playlist {
    private LinkedList<Song> playlist;
    private int currentQueuePosition = 0;
    private MusicLibrary library;
    private Scanner scanner;

    public Playlist(LinkedList<Song> playlist, MusicLibrary musicLibrary) {
        this.playlist = playlist;
        this.library = musicLibrary;
    }
    public Playlist(MusicLibrary musicLibrary){
        this(new LinkedList<Song>(), musicLibrary);
    }

    public void setPlaylist(LinkedList<Song> playlist) {
        this.playlist = playlist;
    }

    public LinkedList<Song> getPlaylist() {
        return playlist;
    }

    public int getCurrentQueuePosition() {
        return currentQueuePosition;
    }

    public void setCurrentQueuePosition(int currentQueuePosition) {
        if (currentQueuePosition >= 0 && currentQueuePosition < playlist.size()){
            this.currentQueuePosition = currentQueuePosition;
        }
    }

    public void addSong(boolean fast){
        System.out.print("Do you want to search song by title ('y') or by albums (anything else)? Write it to me here: ");
        String choice = scanner.nextLine();
        Song song = null;
        if (choice.equals("y")){
            try {
                song = findSongByTitle();
            } catch (SongNotFoundExeption e){
                System.out.println("Song has NOT been found. Check your writing.");
            }
        } else {
            try {
                song = library.getSongFromAlbum();
            } catch (SongNotFoundExeption e){
                System.out.println("Song has NOT been found. Check your writing.");
            }
        }
        if (!fast){
            if (song != null){
                System.out.print("Where do you want to add this song? Write index number to me here: ");
                int index = scanner.nextInt();
                playlist.add(index, song);
                System.out.println("Song has been added to the " + index + " position.");
            } else {
                System.out.println("Song adding was NOT possible.");
            }
        } else {
            playlist.add(song);
            System.out.println("Song has been added to the last position.");
        }
    }
    public void addSong(){
        addSong(false);
    }
    public void addSongFast(){Music
        addSong(true);
    }

    private Song findSongByTitle() throws SongNotFoundExeption{
        System.out.print("Enter title of song: ");
        String title = scanner.nextLine();
        int index;
        for (int i = 0; i < library.getAlbumsSize(); i++){
            Album album = library.getAlbum(i);
             index = album.searchSong(title);
            if(index > 0){
                 return album.getSong();
             }
        }
        throw new SongNotFoundExeption();
    }

    public void printPlaylist(int levelOfIndentation){
        for (int i = 0; i < playlist.size(); i++) {
            for (int j = 0; j < levelOfIndentation; j++){
                System.out.print("\t");
            }
            System.out.print(playlist.get(i));
            System.out.print("\n");
        }
    }
    public void printPlaylist(){
        printPlaylist(0);
    }

    public void printMenu(int levelOfIndentation){
        String[] menu = {"1 -> Play / Stop.",
                "2 -> Next song.",
                "3 -> Previous song.",
                "4 -> Replay song.",
                "5 -> List songs.",
                "6 -> Add song at position.",
                "7 -> Quick song adding.",
                "8 -> Remove song from playlist.",
                "9 -> Enter to your music library.",
                "10 -> Quit."};
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
