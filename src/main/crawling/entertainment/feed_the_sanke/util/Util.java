package crawling.entertainment.feed_the_sanke.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Util {

    private static final String RESULT_FILE = "results.rslt";

    public static PlayerResult readFileToPlayerResult() {
        File file = new File(RESULT_FILE);
        if(file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RESULT_FILE))) {
                return (PlayerResult) inputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            return new PlayerResult();
        }
    }

    public static void writePlayerResultToFile(PlayerResult playerResult) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RESULT_FILE))) {
            outputStream.writeObject(playerResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
