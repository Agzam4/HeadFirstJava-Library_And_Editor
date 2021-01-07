package debug;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class UIMan {

	public static void main(String[] args) {
		 List<String> colorKeys = new ArrayList<String>();
		 Set<Entry<Object, Object>> entries = UIManager.getLookAndFeelDefaults().entrySet();
		 for (Entry entry : entries)
		 {
		 if (entry.getValue() instanceof Color)
		 {
		 colorKeys.add((String) entry.getKey());
		 }
		 }

		 // sort the color keys
		 Collections.sort(colorKeys);
		 
		 // print the color keys
		 for (String colorKey : colorKeys)
		 {
		 System.out.println(colorKey);
		 }

		 }
}
