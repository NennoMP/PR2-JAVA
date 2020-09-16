package pr2_project;

import java.util.ArrayList;

/* UTILIZZATO IN DATABOARD (Implementazione 1) */

public class Category<E extends Data> {
	//OVERVIEW: Tipo di dato modificabile che descrive una categoria con la lista di elementi e amici ad essa associati 
	//AF: <category , {elements.get(i)}> t.c. 0<=i<elements.size()
	//    <category , {friends.get(i)}> t.c. 0<=i<friends.size()
	//IR: category!=null && elements!=null && friends!=null
    //		 forall i t.c 0<=i<elements.size() => elements.get(i)!=null && forall i t.c. 0<=i<friends.size() => friends.get(i)!=null
	//       forall i,j t.c. 0<=i<elements.size() && 0<=j<elements.size() => elements.get(i)!= elements.get(j)
	//		 forall i,j t.c. 0<=i<friends.size() && 0<=j<friendss.size() => friends.get(i)!= friends.get(j)
	
	private String category;
	private ArrayList<E> elements;
	private ArrayList<String> friends;
	
	
	//costruttore
	public Category (String c) {
		category= c;
		elements= new ArrayList<E>();
		friends= new ArrayList<String>();
	}
	/* EFFECTS: inizializza la categoria con lista dati e lista amici, che ne hanno accesso, vuote */
	
	/* Restituisce il nome della categoria */
	public String getName () {
		return category;
	}
	/* EFFECTS: restituisce this.category */
	
	/* Restituisce gli elementi della categoria */
	public ArrayList<E> getElements() {
		return elements;
	}
	/*EFFECTS: restituisce la lista this.elements */
	
	/* Restituisce gli amici autorizzati alla categoria */
	public ArrayList<String> getFriends() {
		return friends;
	}
	/*EFFECTS: restituisce la lista this.friends */
	
	/* Aggiunge un elemento alla lista dati */
	public void addData (E data) {
		elements.add(data);
	}
	/*MODIFIES: (this) => this.elements
	 * EFFECTS: Aggiunge data alla lista this.elements 
	 *			PRE: <data_0, ... , data_n>
	 *			POST: <data_0, ... , data_n, data>
	*/
	
	/* Rimuove un elemento dalla lista dati */
	public void removeData (E data) {
		elements.remove(data);
	}
	/*MODIFIES: (this) => this.elements
	 * EFFECTS: Rimuove data dalla lista this.elements 
	 * 			PRE: <data_0, ... , data_n, data>
	 * 			POST: <data_0, ... , data_n>
	 */
	
	/* Controlla se la categoria contiene un dato */
	public boolean containsData (E data) {
		return elements.contains(data);
	}
	/*EFFECTS: Restituisce true se la lista elements contiene data (exist i t.c 0<=i<elements.size() => elements.get(i)=data)
	 * 		   Restituisce false se la lista elements non contiene data (forall i t.c 0<=i<elements.size() => elements.get(i)!=data)
	 */
	
	/* Aggiunge un amico alla lista amici */
	public void addFriend (String friend) {
		friends.add(friend);
	}
	/*MODIFIES: (this) => this.friends
	 * EFFECTS: Aggiunge friend alla lista friends
	 * 			PRE: <friend_0, ... , friend_n>
	 * 			POST: <friend_0, ... , friend_n, friend>
	 */
	
	/* Rimuove un amico dalla lista amici */
	public void removeFriend (String friend) {
		friends.remove(friend);
	}
	/*MODIFIES: (this) => this.friends
	 * EFFECTS: Rimuove friend dalla lista friends
	 * 			PRE: <friend_0, ... , friend_n, friend>
	 * 			POST: <friend_0, ... , friend_n>
	 */
	
	/* Controlla se la categoria contiene un amico */
	public boolean containsFriends (String friend) {
		return friends.contains(friend);
	}
	/*EFFECTS: Restituisce true se la lista friends contiene friend (exist i t.c 0<=i<friends.size() => friends.get(i).equals(friend))
	 * 		   Restituisce false se la lista friends non contiene friend (forall i t.c 0<=i<friends.size() => !friends.get(i).equals(friend))
	*/
}
