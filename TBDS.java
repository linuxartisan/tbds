import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


public class TBDS implements TBDSInterface {
  TBDSNode root;

  public TBDS() {
    this.root = new TBDSNode();
  }

  //Indirectly recursive method to meet definition of interface
  public void add(String key, String value) {
    if (key == null) {
      return;
    }

    this.add(this.root, key, value);

  }

  //Recursive method
  public void add(TBDSNode current, String curKey, String value) {

    // Reached the node that matches with the actual key;
    // so update the value
    if (curKey.isEmpty()) {
      current.setValue(value);
      return;
    }

    HashMap<Character, TBDSNode> children = current.getChildren();

    char firstChar = curKey.charAt(0);

    if (children.containsKey(firstChar)) {
      // The node with character firstChar is already present, go one level deep
      TBDSNode presentNode = children.get(firstChar);
      this.add(presentNode, curKey.substring(1), value);

    } else {
      // The node with character firstChar is not present, create a new one
      TBDSNode newNode = new TBDSNode();

      children.put(firstChar, newNode);

      if (curKey.length() == 1) {
        newNode.setValue(value);
      } else {
        this.add(newNode, curKey.substring(1), value);
      }

    }
  }

  //Indirectly recursive method to meet definition of interface
  public String get(String key) {
    if (key.isEmpty()) {
      return null;
    }

    return this.get(this.root, key);
  }

  //Recursive method
  public String get(TBDSNode current, String curKey) {
    // Reached the final leaf node, and it matches the actual key;
    // so return value
    if (curKey.isEmpty()) {
      return current.getValue();
    }

    HashMap<Character, TBDSNode> children = current.getChildren();

    char firstChar = curKey.charAt(0);

    if (children.containsKey(firstChar)) {
      // The node with character firstChar is already present, go one level deep
      TBDSNode presentNode = children.get(firstChar);
      return this.get(presentNode, curKey.substring(1));
    }

    return null;
  }

  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key) {
    if (key.isEmpty()) {
      return false;
    }

    return this.containsKey(this.root, key);
  }

  //Recursive method
  public boolean containsKey(TBDSNode current, String curKey) {
    // Reached the final leaf node, and it matches the actual key.
    if (curKey.isEmpty()) {
      // Also, the leaf node contains a value, so return true
      if (current.getValue() != null) {
        return true;
      }

      // false otherwise
      return false;
    }


    HashMap<Character, TBDSNode> children = current.getChildren();

    char firstChar = curKey.charAt(0);

    if (children.containsKey(firstChar)) {
      // The node with character firstChar is already present, go one level deep
      TBDSNode presentNode = children.get(firstChar);
      return this.containsKey(presentNode, curKey.substring(1));
    }

    return false;
  }

  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getKeysForPrefix(String prefix) {
    if (prefix.isEmpty()) {
      return new ArrayList<String>();
      // return null;
    }

    TBDSNode currentNode = this.findNode(this.root, prefix);

    if (currentNode == null) {
      return new ArrayList<String>();
      // return null;
    }

    return this.getSubtreeKeys(currentNode);
  }

  //Recursive helper function to find node that matches a prefix
  public TBDSNode findNode(TBDSNode current, String curKey) {
    // Reached the final leaf node, and it matches the actual key.
    if (curKey.isEmpty()) {
      return current;
    }

    HashMap<Character, TBDSNode> children = current.getChildren();

    char firstChar = curKey.charAt(0);

    if (children.containsKey(firstChar)) {
      // The node with character firstChar is already present, go one level deep
      TBDSNode presentNode = children.get(firstChar);
      return this.findNode(presentNode, curKey.substring(1));
    }

    return null;
  }

  //Recursive helper function to get all keys in a node's subtree
  public ArrayList<String> getSubtreeKeys(TBDSNode current) {
    ArrayList<String> keys = new ArrayList<String>();

    HashMap<Character, TBDSNode> children = current.getChildren();

    // Add the value of current node to the list
    if (current.getValue() != null) {
      keys.add(current.getValue());
    }

    if (children.isEmpty()) {
      return keys;
    }

    // Get a set of the entries
    Set set = children.entrySet();
    
    // Get an iterator
    Iterator i = set.iterator();
    
    // Display elements
    while(i.hasNext()) {
      Map.Entry entry = (Map.Entry) i.next();

      TBDSNode currentNode = (TBDSNode) entry.getValue();

      // Add value of the current node
      String value = currentNode.getValue();
      keys.add(value);

      // this may give duplicate entires, hence not used
      // keys.addAll(this.getSubtreeKeys(currentNode));

      // Create a HashSet from the ArrayList.
      // HashSet does not allow duplicate entries.
      Set<String> uniqueSet = new HashSet<>(keys); // Build a set from existing keys
      uniqueSet.addAll(this.getSubtreeKeys(currentNode)); // Merge with keys of subtrees

      // remove null entries
      uniqueSet.remove(null);

      keys = new ArrayList<>(uniqueSet); // convert back to ArrayList
    }


    return keys;
  }

  //Indirectly recursive method to meet definition of interface
  public void print() {
    System.out.println("The values in the tree:");
    this.print(this.root);
    System.out.println();
  }

  //Recursive method to print values in tree
  public void print(TBDSNode current) {
    HashMap<Character, TBDSNode> children = current.getChildren();

    if (children.isEmpty()) {
      return;
    }

    // Get a set of the entries
    Set set = children.entrySet();
    
    // Get an iterator
    Iterator i = set.iterator();
    
    // Display elements
    while(i.hasNext()) {
      Map.Entry entry = (Map.Entry) i.next();
      TBDSNode currentNode = (TBDSNode) entry.getValue();

      String val = currentNode.getValue();
      if (val != null) {
        System.out.print(val + "\t");        
      }

      this.print(currentNode);
    }

  }

  public static void main(String[] args) {
    // Sample usage of the TBDS

    TBDSInterface tbds = new TBDS();

    tbds.add("a", "a");
    tbds.add("an", "an");
    tbds.add("mam", "mam");
    tbds.add("and", "and");
    tbds.add("man", "man");
    tbds.add("ant", "ant");
    tbds.add("many", "many");
    tbds.add("zoom", "zoom");
    tbds.add("zoo", "zoo");

    tbds.print();
    System.out.println();

    tbds.add("anti", "anti");
    tbds.add("zone", "zone");
    tbds.add("mad", "mad");
    tbds.add("ann", "ann");
    tbds.add("anne", "anne");

    tbds.print();
    System.out.println();


    System.out.println("Checking for keys:");

    String key = "man";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "zone";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "m";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "ma";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "mam";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "mar";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "act";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "z";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "ann";
    System.out.println(key + ": " + tbds.containsKey(key));

    key = "anne";
    System.out.println(key + ": " + tbds.containsKey(key));

    System.out.println();


    System.out.println("Get values for keys:");

    key = "ma";
    System.out.println(key + ": " + tbds.get(key));

    key = "zoom";
    System.out.println(key + ": " + tbds.get(key));

    key = "act";
    System.out.println(key + ": " + tbds.get(key));

    key = "ant";
    System.out.println(key + ": " + tbds.get(key));

    key = "a";
    System.out.println(key + ": " + tbds.get(key));

    key = "kick";
    System.out.println(key + ": " + tbds.get(key));

    key = "an";
    System.out.println(key + ": " + tbds.get(key));

    key = "and";
    System.out.println(key + ": " + tbds.get(key));

    key = "anti";
    System.out.println(key + ": " + tbds.get(key));

    key = "antil";
    System.out.println(key + ": " + tbds.get(key));

    key = "ann";
    System.out.println(key + ": " + tbds.get(key));

    System.out.println();


    System.out.println("Keys for prefix:");

    String prefix = "mad";
    System.out.println("Keys (" + prefix + ") : " + tbds.getKeysForPrefix(prefix));

    prefix = "an";
    System.out.println("Keys (" + prefix + ") : " + tbds.getKeysForPrefix(prefix));

    prefix = "";
    System.out.println("Keys (" + prefix + ") : " + tbds.getKeysForPrefix(prefix));

    prefix = "cli";
    System.out.println("Keys (" + prefix + ") : " + tbds.getKeysForPrefix(prefix));

  }
}