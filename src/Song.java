public class Song {
    private String title;
    private int duration;

    public Song(String title, int duration) throws InvalidSongFileExeption{
        //Jeśli masz wąty do tego wyjątku, to zobacz komentarz w klasie "InvalidSongFileExeption" :p.
        setTitle(title);
        setDuration(duration);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InvalidSongFileExeption {
        if (title == null || title.equals("")) {
            throw new InvalidSongFileExeption();
        } else {
            this.title = title;
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) throws InvalidSongFileExeption{
        if (duration <= 0) {
            throw new InvalidSongFileExeption();
        } else {
            this.duration = duration;
        }
    }

}
