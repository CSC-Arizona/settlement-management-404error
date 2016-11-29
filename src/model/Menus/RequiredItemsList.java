package model.Menus;

import java.util.HashMap;
import java.util.LinkedList;

import model.Items.Item;

public class RequiredItemsList {
	
      private HashMap<String, Integer> reqItems;
      
      public RequiredItemsList() {
    	  reqItems = new HashMap<>();
      }
      
      public void addItem(Item item) {
    	  if (reqItems.containsKey(item.toString())) {
    		  int currVal = reqItems.get(item.toString());
    		  reqItems.put(item.toString(), currVal + 1);
    	  } else 
    		  reqItems.put(item.toString(), 1);
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
