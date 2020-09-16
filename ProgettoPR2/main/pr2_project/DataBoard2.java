package pr2_project;

import pr2_exceptions.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* Implementazione numero 2 */

public class DataBoard2<E extends Data> implements IDataBoard<E>{
	//OVERVIEW: Tipo di dato modificabile, previa autorizzazione, che descrive una bacheca contenente una lista di categorie elementi
	//          ed una lista di amici che possono accedere ai dati della categoria a cui sono autorizzati
	//AF: <elements.KeySet() , elements.Values()> con ogni elemento di elements.Values() associato ad un solo elemento di elements.KeySet()
	//    <friends.KeySet() , friends.Values()> con ogni elemento di friends.Values() associato ad un solo elemento di friends.KeySet()
	//IR: pwd!=null && elements!=null && friends!=null
	//	  friends e elements devono contenere le stesse chiavi (possono essere vuoti) => se elements.contains(Key) => friends.contains(Key)
	//    non possono esserci duplicati di chiavi o di valori per la stessa chiave, tutte le chiavi e i valori sono !=null 
	//    possonoe esserci duplicati di un valore se questo è stato associato a diverse di una categoria
	
	
	HashMap <String, Vector<E>> elements;
	HashMap <String, Vector<String>> friends;
	private String pwd;
	
	//costruttore
	public DataBoard2 (String passw) throws NullPointerException {
		if (passw==null) throw new NullPointerException();
		this.pwd= passw;
		this.elements= new HashMap<String, Vector<E>>();
		this.friends= new HashMap<String, Vector<String>>();		
	}
	
	/*REQUIRES: passw!=null
	 * THROWS: se passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * EFFECTS: Inizializza la Doard con password e HashMap di elementi e amici vuote
	 */
	
	/* Controlla le credenziali per metodi di modifica dati */
	public void isAuthorized (String passw) throws NullPointerException, InvalidPwdException{
		if(passw==null) throw new NullPointerException();
		if (!passw.equals(this.pwd)) throw new InvalidPwdException("Wrong password");
	}
	/*REQUIRES: passw!=null && passw valida
	 * THROWS: se passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * EFFECTS: Restituisce true se passw.equals(this.pwd)
	 * 			Restituisce false se !pass.equals(this.pwd)
	 */
	
	/* Crea una categoria di dati se vengono rispettati i controlli di identità */
	public void createCategory(String category, String passw) throws NullPointerException, InvalidPwdException, DuplicateException{
		if (category==null || passw== null) throw new NullPointerException();
		isAuthorized(passw);
		if (this.elements.containsKey(category) || this.friends.containsKey(category)) throw new DuplicateException("Category already in the collection: " + category);
		else  {
			this.elements.put(category, new Vector<E>());
			this.friends.put(category, new Vector<String>());
		}
	}
	/*REQUIRES: category!=null && passw!=null && passw valida && category non già presente
	 * THROWS: se category==null || passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se category è già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Aggiunge category come chiave in HashMap friends e elements, con values associati vuoti
	 */
		
		
	/* Rimuove una categoria di dati se vengono rispettati i controlli di identità */
	public void removeCategory(String category, String passw) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (category== null || passw==null) throw new NullPointerException();
		isAuthorized(passw);
		if (!this.elements.containsKey(category) || !this.friends.containsKey(category)) throw new NoDataFoundException("No such category in the collection: " + category);
		else {
			this.elements.remove(category);
			this.friends.remove(category);
		}
	}
	/*REQUIRES: category!=null && passw!=null && passw valida && category presente
	 * THROWS: se category==null || passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se category non è presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Rimuove category (chiave) in HashMap elements e friends, se presente, e valori associati 
	 */
		
		
	/* Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità */
	public void addFriend(String category, String passw, String friend) throws NullPointerException, InvalidPwdException, NoDataFoundException, DuplicateException {
		if (category== null || passw== null || friend== null) throw new NullPointerException();
		isAuthorized(passw);
		if (!this.friends.containsKey(category)) throw new NoDataFoundException("No such category: " + category);
		if (this.friends.get(category).contains(friend)) throw new DuplicateException("Friend already in the category: " + friend);
		this.friends.get(category).add(friend);
	}
	/*REQUIRES: category!=null && passw!=null && friend!=null && passw valida && friend non presente
	 * THROWS: se category==null || passw==null || friend==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se friend è già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Aggiunge friend in HashMap friends associato alla chiave category, se non già presente
	 */
		
		
	/* Rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità */
	public void removeFriend(String category, String passw, String friend) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (category== null || passw==null || friend== null) throw new NullPointerException();
		isAuthorized(passw);
		if (!this.friends.containsKey(category)) throw new NoDataFoundException ("No such category: " + category);
		if (!this.friends.get(category).contains(friend)) throw new NoDataFoundException("No such friend: " + friend);
		this.friends.get(category).remove(friend);
	}
	/*REQUIRES: category!=null && passw!=null && friend!=null && passw valida && friend presente
	 * THROWS: se category==null || passw==null || friend==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se friend non è presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Rimuove friend da HashMap friends associato alla chiave category, se presente
	 */
		
		
	/* Inserisce un dato in bacheca se vengono rispettati i controlli di identità */
	public boolean put(String passw, E dato, String categoria) throws NullPointerException, InvalidPwdException, NoDataFoundException, DuplicateException {
		if (passw== null || dato== null || categoria== null) throw new NullPointerException();
		isAuthorized(passw);
		if(!this.elements.containsKey(categoria)) throw new NoDataFoundException("No such category: " + categoria);
		if (this.elements.get(categoria).contains(dato)) throw new DuplicateException ("Data already in the category: " + dato.display());
		dato.setCategory(categoria);
		this.elements.get(categoria).add(dato);
		return true;
	}
	/*REQUIRES: passw!=null && dato!=null && categoria!=null && passw valida && categoria esistente && dato non presente
	 * THROWS: se passw==null || dato==null || categoria==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se categoria non esiste lancia una NoDataFoundException (non disponibile in Java, checked)
	 * 		   se dato già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Aggiunge data a HashMap elements con chiave categoria, se non presente
	 */
		
		
	/* Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità */
	public E get(String passw, E dato) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (passw== null || dato== null) throw new NullPointerException();
		isAuthorized(passw);
		boolean found= false;
		for (Vector<E> val: this.elements.values()) {
			if (val.contains(dato)) found= true;
		}
		if (!found) throw new NoDataFoundException("No such data: " + dato.display());
		E copy= (E)dato.createCopy();
		return copy;
	}
	/*REQUIRES: passw!=null && dato!=null && passw valida && dato presente
	 * THROWS: se passw==null || dato==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se dato non presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * EFFECTS: Restituisce una copia di data
	 */
		
		
	/* Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità */
	public E remove(String passw, E data) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (passw==null || data== null) throw new NullPointerException();
		isAuthorized(passw);
		E removed= null;
		for (Vector<E> val: this.elements.values()) {
			if (val.contains(data)) {
				removed= data;
				val.remove(data);
			}
		}
		if (removed== null) throw new NoDataFoundException("No such data: " + data.display());
		return removed;
	}
	/*REQUIRES: passw!=null && dato!=null && passw valida && dato presente
	 * THROWS: se passw==null || dato==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se dato non presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Rimuove data da HashMap elements, se presente
	 */
		
	
	/* Aggiunge un like al dato se rispettati i vincoli di identità */
	public void insertLike (String friend, E data) throws NullPointerException, UnAuthorizedLikeException, NoDataFoundException, DuplicateException {
		if (friend== null || data== null) throw new NullPointerException();
		String tmp= null;
		for (String cat: this.elements.keySet()) {
			if (this.elements.get(cat).contains(data)) tmp= cat;
		}
		if (tmp== null) throw new NoDataFoundException("No such data: " + data.display());
		if (data.getFriendsLiked().contains(friend)) throw new DuplicateException (friend + " already liked this element");
		if (this.friends.get(tmp).contains(friend)) data.addLike(friend);
		else throw new UnAuthorizedLikeException(friend + " cannot like this data");
	}
	/*REQUIRES: friend!=null && data!=null && friend ha accesso al dato && dato presente && like non presente
	 * THROWS: se friend==null || dato==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se friend non ha accesso al dato lancia una UnAuthorizedLikeException (non disponibile in Java, checked)
	 * 		   se dato non presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * 		   se like già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: this
	 * EFFECTS: Aggiunge un like a data per conto di friend, incrementando il contatore likes di 1
	 */
	
	
	/* Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità */
	public List<E> getDataCategory(String passw, String category) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (passw== null || category== null) throw new NullPointerException();
		isAuthorized(passw);
		if (!this.elements.containsKey(category)) throw new NoDataFoundException("No such category: " + category);
		Collection<E> values= this.elements.get(category);
		ArrayList<E> list= new ArrayList<E>(values);
		if (list.isEmpty()) System.out.println("\n :Nessun elemento nella categoria, lista vuota");
		for (int i=0; i<list.size(); i++) {
			System.out.println("\n :" + list.get(i).display());
		}
		return list;
	}
	/*REQUIRES: passw!=null && category!=null && passw valida && categoria esistente
	 * THROWS: se passw==null || category==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 	       se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se categoria inesistente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * EFFECTS: Restituisce una lista contenente tutti i dati relativi a category presenti in bacheca
	 * 			Restituisce all dato_i, forall i t.c dato_i presente in bacheca e dato_i.getCategory()= category
	 */
		
	/* Restituisce un iteratore (senza remove) che genera tutti i dati in
	*  bacheca ordinati rispetto al numero di like se vengono rispettati i controlli di identità
	*/
	public Iterator<E> getIterator(String passw) throws NullPointerException, InvalidPwdException {
		if (passw==null) throw new NullPointerException();
		isAuthorized(passw);
		TreeSet <E> data= new TreeSet<E>();
		for (Vector<E> val: this.elements.values()) {
			for (E e: val) {
				data.add(e);
			}
		}
		SortedSet<E> AllData = Collections.unmodifiableSortedSet(data);
		return AllData.iterator();
	}
	/*REQUIRES: passw!=null && passw valida
	 * THROWS: se passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * EFFECTS: Restituisce un iteratore, non modificabile e quindi senza remove, con tutti i dati presenti in bacheca 
	 * 			sorted per numero di like (senza criterio specificato: crescente/decrescente)
	 */
		
	/* Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi */
	public Iterator<E> getFriendIterator(String friend) throws NullPointerException {
		if (friend==null) throw new NullPointerException();
		ArrayList <E> data= new ArrayList<E>();
		for (String cat: this.elements.keySet()) {
			if (this.friends.get(cat).contains(friend)) {
				for (E e: this.elements.get(cat)) {
					data.add(e);
				}
			}
		}
		List<E> AllFriendData= Collections.unmodifiableList(data);
		return AllFriendData.iterator();
	}
	/*REQUIRES: friend!=null
	 * THROWS: se friend==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * EFFECTS: Restituisce un iteratore, non modificabile e quindi senza remove, con tutti i dati in bacheca condivisi da friend
	 */
}
