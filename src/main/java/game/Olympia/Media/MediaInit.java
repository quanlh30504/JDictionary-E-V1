package game.Olympia.Media;

public class MediaInit {
    public String fileName;

    public void mediaInit(String fileName) {
        String path = getClass().getResource(fileName).getPath();
        System.out.println(path);
    }
}
