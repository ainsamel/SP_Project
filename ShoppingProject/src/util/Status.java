package util;

import java.util.ArrayList;
import java.util.Iterator;

//ArrayList  -> ArrayList<Exception>
//exceptions -> execList

public class Status {

	private ArrayList<Exception> execList;

	public void status() {
		execList = new ArrayList<Exception>();
	}

	public boolean isSuccessful() {
		return execList.size() == 0;
	}

	public void addException(Exception ex) {
		execList.add(ex);
	}

	public Iterator<Exception> getExceptions() {
		return execList.iterator();
	}
}
