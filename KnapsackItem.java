/**
 * A helper class used to store information
 * for a knapsack item.
 * 
 * @author Brodie Abrew
 */

 public class KnapsackItem {
    private int weight;
    private int value;
  
    /**
     * Constructor that creates a new knapsack item.
     * 
     * @param weight The weight of the item
     * @param Value  The value of the item
     */
    public KnapsackItem(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
  
    /**
     * Gets the item weight.
     * 
     * @return The item weight
     */
    public int getWeight() {
      return weight;
    }
  
    /**
     * Gets the item value.
     * 
     * @return The item value
     */
    public int getValue() {
      return value;
    }
  
    /**
     * Creates a deep-copy clone of the item.
     * 
     * @return The deep-copy clone of the item
     */
    public KnapsackItem clone() {
      return new KnapsackItem(weight, value);
    }
  }