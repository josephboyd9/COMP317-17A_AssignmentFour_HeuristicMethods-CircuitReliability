import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Joseph Boyd (1264974)
 * Amarjot Parmer (1255668)
 */
public class Solve
{
  private static final int NUM_STAGES = 1;
  
  private static float budget = 0f;         //total cost allowed to be used across all stages
  private static int limit = 0;             //max number devices that can be looked up
  private static LinkedList<Float> rs = new LinkedList<Float>();   //reliability of device n
  private static LinkedList<Float> cs = new LinkedList<Float>();    //cost of device n
  
  private static int[] devices = new int[ NUM_STAGES ];   //id of device used at stage n
  private static int[] num_d = new int[ NUM_STAGES ];     //num devices used at stage n
  
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

    //System.err.println(compare(0,1));


  }
  
  /*
  Simulated Annealing
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
  private static void parseArgs( String[] args ) throws Exception
  {
    budget = Float.parseFloat( args[ 1 ] );
    limit = Integer.parseInt( args[ 2 ] );
    BufferedReader br = new BufferedReader( new FileReader( args[  0 ] ) );
    String line;
    String[] tokens;

    while( ( line = br.readLine() ) != null )
    {
      tokens = line.split( " " );
      rs.add(Float.parseFloat( tokens[ 0 ] ));
      cs.add(Float.parseFloat( tokens[ 1 ] ));
    }
    br.close();
  }

  /**
   * Takes two indexes, works out which is better for price vs performace
   * and returns index of the better one
   * @param x index of circuit a
   * @param y index of circuit b
   */
  private static int compare(int x, int y)
  {
    float temp_budget = budget;
    float reliability = rs.get(x);
    float cost = cs.get(x);
    float x_total_reliability = 0;
    //what reliability we can get using multiple of x
    while(temp_budget >= cost)
    {
      x_total_reliability = x_total_reliability + (1 - reliability);
      temp_budget = temp_budget - cost;
    }

    //System.err.println("x Total Reliability : " + x_total_reliability);
    //System.err.println("leftover budget :" + temp_budget);
    reliability = rs.get(y);
     cost = cs.get(y);
    float y_total_reliability = 0;

    temp_budget = budget;
    //what reliability we can get using multiple of y
    while(temp_budget >= cost)
    {
      y_total_reliability = y_total_reliability + (1 - reliability);
      temp_budget = temp_budget - cost;
    }

    //System.err.println("y Total Reliability : " + y_total_reliability);
    //System.err.println("leftover budget :" + temp_budget);

    //Chossing one with most reliability
    if(y_total_reliability > x_total_reliability)
    {
      //System.err.println("y was picked");
      return y;
    }
    else
   {
     //System.err.println("x was picked");
     return x;
   }

  }
  
  
  
  
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
