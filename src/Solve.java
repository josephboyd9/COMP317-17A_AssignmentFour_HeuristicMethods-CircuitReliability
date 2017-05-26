import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Joseph Boyd (1264974)
 * Amarjot Parmer (1255668)
 */
public class Solve
{
  private static float budget = 0f;
  private static int limit = 0;
  
  public static void main( String[] args )
  {
    File file;
    try
    {
      file = parseArgs( args );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return;
    }
  }
  
  /*
  Sim Aneel 
  needs to gen next index to compare
  compare  
  */
  
  /**
   * Usage: java Solve <filename> <budget> <limit>
   *
   * @param args command-line arguments
   * @return File parsed from args[0]
   * @throws Exception
   */
  private static File parseArgs( String[] args ) throws Exception
  {
    budget = Float.parseFloat( args[ 1 ] );
    limit = Integer.parseInt( args[ 2 ] );
    return new File( args[ 0 ] );
  }
  
  /**
   * @param file file to read from
   * @param n    line number in file to be read, must be greater than 0
   * @return nth line from file as a String
   * @throws IOException
   */
  private static String getLine( File file, int n ) throws IOException
  {
    Stream<String> lines = Files.lines( file.toPath() );
    return lines.skip( n - 1 ).findFirst().get();
  }
}
