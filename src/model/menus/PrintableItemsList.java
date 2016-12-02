package model.menus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

import model.items.Item;

public class PrintableItemsList implements Serializable {
	
      /**
	 * 
	 */
	private static final long serialVersionUID = -7234719053724862161L;
	private HashMap<String, Integer> reqItems;
      
      public PrintableItemsList() {
    	  reqItems = new HashMap<>();
      }
      
      public void addItem(Item item) {
    	  if (reqItems.containsKey(item.toString())) {
    		  int currVal = reqItems.get(item.toString());
    		  reqItems.put(item.toString(), currVal + 1);
    	  } else 
    		  reqItems.put(item.toString(), 1);
      }
      
      public boolean removeItem(Item item) {
    	  if (reqItems.containsKey(item.toString())) {
    		  int newVal = reqItems.get(item.toString()) - 1;
    		  if (newVal == 0)
    			  reqItems.remove(item.toString());
    		  else
    		      reqItems.put(item.toString(), newVal);
    		  return true;
    	  }
    	  return false;
      }
      
      public String toString() {
    	  String result = "";
    	  for (String s : reqItems.keySet()) {
    		  int amount = reqItems.get(s);
    		  result = result + amount + " ";
    		  if (amount > 1) {
    			  result = result + s + "s, ";
    		  } else {
    			  result = result + s + ", ";
    		  }
    		  //result = result + reqItems.get(s) + " " + s + ", ";
    	  }
    	  if (!result.equals(""))
    	      result = result.substring(0, result.length() - 2);
    	  return result;
      }
}
