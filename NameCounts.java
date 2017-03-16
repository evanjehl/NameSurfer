import acm.program.*;
import java.util.*;

public class NameCounts extends ConsoleProgram {
	
	public void run() {
		HashMap<String, Integer> names = new HashMap<String, Integer>();
		while (true) {
			String name = readLine("Enter name: ");
			println(name);
			if (name == "") break;
			if (names.containsKey(name) == false) {
				names.put(name, 1);
			} else {
				names.put(name, names.get(name) + 1);
			}
		}
		Iterator<String> it = names.keySet().iterator();
		while (it.hasNext()) {
			String entry = it.next();
			println("Entry [" + entry + "] has count " + names.get(entry));
		}
	}
}