import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Joseph Boyd (1264974)
 */
public class Solve
{
  private static final int NUM_STAGES = 1;
  
  private static double budget = 0f;         //total cost allowed to be used across all stages
  private static int limit = 0;             //max number devices that can be looked up
  private static LinkedList<Device> devices = new LinkedList<Device>();
  
  private static int[] stages = new int[ NUM_STAGES ];   //id of device used at stage n
  private static int[] num_d = new int[ NUM_STAGES ];     //num devices used at stage n
  
  /*
  Simulated Annealing
  needs to gen next index to compare
  compare  
  */
  
  private static void parseArgs( String[] args ) throws Exception
  {
    budget = Double.parseDouble( args[ 1 ] );
    limit = Integer.parseInt( args[ 2 ] );
    BufferedReader br = new BufferedReader( new FileReader( args[  0 ] ) );
    String line;
    String[] tokens;

    while( ( line = br.readLine() ) != null )
    {
      tokens = line.split( " " );
      rs.add(Double.parseDouble( tokens[ 0 ] ));
      cs.add(Double.parseDouble( tokens[ 1 ] ));
    }
    br.close();
  }

  /**
   * Takes indexes for 2 devices, calculates and returns most reliable,
   * assuming as many of given device as budget allows are used
   * @param a index of device a
   * @param b index of device b
   * @return index of most reliable device
   */
  private static int compare(device a, int b)
  {
    devices[] ds = { a, b };
    //ds.length instead of hardcoded 2 allows for easy adjustment if needed
    double[] r_stages = new double[ ds.length ];
    for( int i = 0; i < ds.length; i++ )
    {
      r_stages[ i ] = devices.get( ds[ i ] ).rStage(
          Math.floor( budget / cs.get( a ) ) );
    }
    //comparision of individual elements of array does NOT allow for easy adjustment :(
    return ds[ r_stages[ 0 ] > r_stages[ 1 ] ? 0 : 1 ];
  }
  
  private static void iterate()
  {
  
  }
  
  public static void main( String[] args )
  {
    try
    {
      parseArgs( args );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return;
    }
    //budget -= 1 whenever iterate looks at a device
    while( budget > 0 )
    {
      iterate();
    }
  }
  
  
  
  /*  Unused Functions  */
  
  /**
   * This is impractical for a file of such a small set of devices,
   * however it would scale well if we needed to look at a very large number of items.
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
