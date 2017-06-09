import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Joseph Boyd (1264974)
 */
public class Solve
{
  private static final int NUM_STAGES = 1;
  
  private static double budget = 0;         //total cost allowed to be used across all stages
  private static int limit = 0;             //max number devices that can be looked up
  private static LinkedList<Device> devices = new LinkedList<Device>();
  
  private static Device[] stages = new Device[ NUM_STAGES ];   //device n used at stage n
  private static int[] num_d = new int[ NUM_STAGES ];     //num_d[ n ] devices used at stage n
  
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
      devices.add( new Device( Double.parseDouble( tokens[ 0 ] ), Double.parseDouble( tokens[ 1 ] ) ) );
    }
    br.close();
  }

  /**
   * Takes indexes for multiple devices, calculates and returns most reliable,
   * assuming as many of given device as budget allows are used
   * @param ds array containing devices to be compared
   * @return most reliable device
   */
  private static Device compare( Device[] ds )
  {
    //ds.length instead of hardcoded 2 allows for easy adjustment if needed
    double temp = 0;
    Device best = ds[ 0 ];
    double r_best = ds[ 0 ].rStage( (int) ( budget / ds[ 0 ].getCost() ) );
    for( int i = 1; i < ds.length; i++ )
    {
      temp = ds[ i ].rStage( (int) ( budget / ds[ i ].getCost() ) );
      if( temp > r_best )
      {
        best = ds[ i ];
        r_best = temp;
      }
    }
    return best;
  }
  
  /**
   *
   * @param old_d
   * @return
   */
  private static Device nextD( Device old_d )
  {
    int d_ind = devices.indexOf( old_d ) + ( (int) ( Math.random() * 2 ) == 0 ? -1 : 1 );
    //if ind is past edges of list, wrap around
    if( d_ind < 0 )
    {
      d_ind = devices.size() - 1;
    }
    d_ind %= devices.size();
    return devices.get( d_ind );
  }
  
  public static void main( String[] args )
  {
    int seen = 0;
    int new_m = 0;
    Device new_d;
    
    try
    {
      parseArgs( args );
    }
    catch( Exception e )
    {
      e.printStackTrace();
      return;
    }
    stages[ 0 ] = devices.get( (int) Math.random() * devices.size() );
    num_d[ 0 ] = (int) ( budget / stages[ 0 ].getCost() );
    seen++;
    System.out.println("Stage 1 v" + Integer.toString( seen ) + ": "
        + Double.toString( stages[ 0 ].rStage( num_d[ 0 ] ) ) );
    while( seen < limit )
    {
      new_d = nextD( stages[ 0 ] );
      new_m = (int) ( budget / new_d.getCost() );
      seen++;
      if( new_d.rStage( new_m ) >= stages[ 0 ].rStage( num_d[ 0 ] ) )
      {
        stages[ 0 ] = new_d;
        num_d[ 0 ] = new_m;
        System.out.println( "Stage 1 v" + Integer.toString( seen ) + ": "
            + Double.toString( stages[ 0 ].rStage( num_d[ 0 ] ) ) );
      }
    }
    System.out.println( "Limit reached\n" );
    System.out.println( "Stage 0: " + Integer.toString( num_d[ 0 ] ) + " x device "
        + Integer.toString( devices.indexOf( stages[ 0 ] ) ) );
    System.out.println( "Stage reliability = " + Double.toString( stages[ 0 ].rStage( num_d[ 0 ] ) ) );
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
