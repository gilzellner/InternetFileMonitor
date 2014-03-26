package Homework.Trusteer.Utils;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class Inventory {
	HashMap<String, String> inventory = new HashMap<String, String>();
	   static Logger log = Logger.getLogger(Inventory.class);

	public String get(String Key)
	{
		if (this.inventory.containsKey(Key))
			return this.inventory.get(Key);
		return null;
	}
	
	public Boolean hasChanged(String key, String newValue)
	{
		if (this.inventory.containsKey(key))
		{
			if (newValue.equals(this.inventory.get(key)))
				return false;
			this.inventory.remove(key);
			this.inventory.put(key, newValue);
			return true;
		}
		this.inventory.put(key, newValue);
		return false;
	}
	

}
