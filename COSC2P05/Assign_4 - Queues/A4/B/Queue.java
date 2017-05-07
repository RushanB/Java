package B;

public interface Queue { //interface supports queue ADT
	public void add(Request req);
	public Request leave();
	public boolean isEmpty();
	public int length();	
}