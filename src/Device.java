/**
 * Joseph Boyd (1264974)
 */
public class Device
{
  private final double relability;
  private final double cost;
  
  public Device( double reliability, double cost )
  {
    this.relability = reliability;
    this.cost = cost;
  }
  
  /**
   * @param m number of devices in stage
   * @return reliability of stage using this device
   */
  public double rStage( int m )
  {
    return  1 - Math.pow( 1 - relability, Math.floor( m ) );
  }
  
  public double getRelability()
  {
    return relability;
  }
  
  public double getCost()
  {
    return cost;
  }
}
