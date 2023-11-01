/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LE HUU HUY
 */
public class CartObject implements Serializable{
     private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public boolean addItemToCart(String itemID) {
        boolean result = false;
        //1. valid itemID
        if (itemID == null) {
            
        } else if (itemID.trim().isEmpty()) {
            //do nothing
        } else {//valid itemID
            //2. check existed items?
            if (this.items == null) {
                this.items = new HashMap<>();
            }//end items have not existed
            //3. check existed items?
            int quantity = 1;
            if (this.items.containsKey(itemID)) {
                quantity = this.items.get(itemID) + 1;
            }
            //4. update/create item in items
            this.items.put(itemID, quantity);
            result = true;
        }
        
        return result;
    }
    
    public boolean removeItemFromCart(String itemID) {
        boolean result = false;
        if (itemID == null) {
            //do nothing
        } else if (itemID.trim().isEmpty()) {
            //do nothing
        } else {//valid itemID
            //2. check existed items?
            if (this.items == null) {
                //do nothing
            } else {
                //3. check existed items?
                if (this.items.containsKey(itemID)) {
                    this.items.remove(itemID);
                    if (this.items.isEmpty()) {
                        this.items = null;
                    }
                    result = true;
                }
            }
        }
        
        return result;
    }
}
