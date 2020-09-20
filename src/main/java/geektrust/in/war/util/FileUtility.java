package geektrust.in.war.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * This class provides a public function to get a file as String,
 *
 * @author vimalk29
 * @version 1.0
 */
public class FileUtility {

  /**
   * Reads a file, return its content as String.
   *
   * @param filePath Path to file, can be absolute or relative.
   * @return String, Content of file. Encoded in UTF-8
   */
  public static String fileToString(String filePath) throws IOException {
    Charset encoding = Charset.defaultCharset(); // Getting default encoding.
    byte[] encoded = Files.readAllBytes(Paths.get(filePath)); // Reading all bytes.
    return new String(encoded, encoding); // Returning string.
  }

}
