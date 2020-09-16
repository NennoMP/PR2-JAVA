package pr2_project;

import pr2_exceptions.*;
import java.util.SortedSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.ArrayList;
import java.util.Collections;

/* Implementazione numero 1
 * N.B: class Category utilizzata in questa implementazione
*/

public class DataBoard<E extends Data> implements IDataBoard<E> {
	//OVERVIEW: Tipo di dato modificabile, previa autorizzazione, che descrive una bacheca contenente una lista di categorie elementi
	//          ed una lista di amici che possono accedere ai dati della categoria a cui sono autorizzati
	//AF: <pwd, {categories.get(i)}> con 0<=i<categories.size()
	//IR: pwd!=null && categories!=null
    //    forall i, 0<=i<categories.size() => categories.get(i)!=null e tipo String
	//	  forall i,j t.c 0<=i<categories.size() e 0<=j<categories.size() => categories.get(i)!=categories.get(j)
	
	private ArrayList<Category<E>> categories;
	private String pwd;
	
	//costruttore
	public DataBoard (String passw) throws NullPointerException {
		if (passw==null) throw new NullPointerException();
		this.pwd= passw;
		this.categories= new ArrayList<Category<E>>();
	}
	/*REQUIRES: passw!=null
	 * THROWS: se passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * EFFECTS: Inizializza la DataBoard con password e lista categorie vuota
	 */
	
	/* Controlla le credenziali per metodi di modifica ai dati */
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

	/* Crea una categoria di dati se vengono rispettati i controlli di identità e non è già presente */
	public void createCategory(String category, String passw) throws NullPointerException, InvalidPwdException, DuplicateException{
		if (category==null || passw==null) throw new NullPointerException();
		isAuthorized(passw);
		for (Category<E> cat: this.categories) {
			if (cat.getName().equals(category)) throw new DuplicateException("Category already in the collection: " + category);
		}
		Category<E> c= new Category<E>(category);
		this.categories.add(c);
	}
	/*REQUIRES: category!=null && passw!=null && passw valida && category non già presente
	 * THROWS: se category==null || passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se category è già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: (this) => this.categories
	 * EFFECTS: Aggiunge una categoria, se non già presente, inizializzata con lista elementi e lista amici che ne hanno accesso vuote
	 * 			PRE: <category_0, ... , category_n>
	 * 			POST: <category_0, ... , category_n, category>
	 */
	
	
	/* Rimuove una categoria di dati se vengono rispettati i controlli di identità */
	public void removeCategory(String category, String passw) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (category==null || passw==null) throw new NullPointerException();
		isAuthorized(passw);
		Category<E> tmp= null;
		for (Category<E> cat : this.categories) {
			if (cat.getName().equals(category)) {
				tmp= cat;
			}
		}
		if (tmp== null) throw new NoDataFoundException("No such category: " + category);
	}
	/*REQUIRES: category!=null && passw!=null && passw valida && category presente
	 * THROWS: se category==null || passw==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se category non è presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * MODIFIES: (this) => this.categories
	 * EFFECTS: Rimuove una categoria, se presente
	 * 			PRE: <category_0, ... ,category_n, category>
	 * 			POST: <category_0, ... , category_n>
	 */
	
	
	/* Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità */
	public void addFriend(String category, String passw, String friend) throws NullPointerException, InvalidPwdException, NoDataFoundException, DuplicateException {
		if (category==null || passw==null || friend==null) throw new NullPointerException();
		isAuthorized(passw);
		boolean found= false;
		for (Category<E> cat: this.categories) {
			if (cat.getName().equals(category)) {
				if (cat.containsFriends(friend)) throw new DuplicateException("Friend already in the category: " + friend);
				found= true;
				cat.addFriend(friend);
			}
		}
		if (!found) throw new NoDataFoundException("No such category: " + category);
		
	}
	/*REQUIRES: category!=null && passw!=null && friend!=null && passw valida && friend non presente
	 * THROWS: se category==null || passw==null || friend==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se friend è già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: (this) => category.friends
	 * EFFECTS: Aggiunge un amico alla categoria, se non già presente
	 * 			PRE: <category, {friend_0,  ... , friend_n}>
	 * 			POST: <category, {friend_0, ... , friend_n, friend}>
	 */
	
	
	/* Rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità */
	public void removeFriend(String category, String passw, String friend) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (category==null || passw==null || friend==null) throw new NullPointerException();
		isAuthorized(passw);
		Category<E> found= null;
		boolean friendfound= false;
		for (Category<E> cat: this.categories) {
			if (cat.getName().equals(category)) {
				found= cat;
			}
		}
		if (found== null) throw new NoDataFoundException("No such category: " + category);
		if (found.containsFriends(friend)) {
			friendfound= true;
			found.removeFriend(friend);
		}	
		if (!friendfound) throw new NoDataFoundException ("No such friend: " + friend);
	}
	/*REQUIRES: category!=null && passw!=null && friend!=null && passw valida && friend presente
	 * THROWS: se category==null || passw==null || friend==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se friend non è presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * MODIFIES: (this) => category.friends
	 * EFFECTS: Rimuove un amico dalla categoria, se presente
	 * 			PRE: <category, {friend_0,  ... , friend_n, friend}>
	 * 			POST: <category, {friend_0, ... , friend_n}>
	 */
	
	
	/* Inserisce un dato in bacheca se vengono rispettati i controlli di identità */
	public boolean put(String passw, E dato, String categoria) throws NullPointerException, InvalidPwdException, NoDataFoundException, DuplicateException {
		if (passw==null || dato==null || categoria==null) throw new NullPointerException();
		isAuthorized(passw);
		boolean found= false;
		for (Category<E> cat: this.categories) {
			if (cat.getName().equals(categoria)) {
				if (cat.containsData(dato)) throw new DuplicateException("Data already in the collection: " + dato.display());
				found= true;
				dato.setCategory(categoria);
				cat.addData(dato);
			}
		}
		if (!found) throw new NoDataFoundException("No such category: " + categoria);
		return true;
	}
	/*REQUIRES: passw!=null && dato!=null && categoria!=null && passw valida && categoria esistente && dato non presente
	 * THROWS: se passw==null || dato==null || categoria==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se categoria non esiste lancia una NoDataFoundException (non disponibile in Java, checked)
	 * 		   se dato già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: (this) => category.elements
	 * EFFECTS: Aggiunge un dato, se non presente e restituisce true, false altrimenti
	 * 			PRE: <category, {data_0, ... , data_n}>
	 * 			POST: <category, {data_0, ... , data_n, dato}>
	 */
	
	
	/* Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità */
	public E get(String passw, E dato) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (passw== null || dato== null) throw new NullPointerException();
		isAuthorized(passw);
		boolean found= false;
		for (Category<E> cat: this.categories) {
			if(cat.containsData(dato)) found= true;
		}
		if (!found) throw new NoDataFoundException ("No such data: " + dato.display());
		E copy= (E)dato.createCopy();
		return copy;
	}
	/*REQUIRES: passw!=null && dato!=null && passw valida && dato presente
	 * THROWS: se passw==null || dato==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se passw non valida lancia una InvalidPwdException (non disponibile in Java, checked)
	 * 		   se dato non presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * EFFECTS: Restituisce una copia del dato
	 */
	
	
	/* Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità */
	public E remove(String passw, E data) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (passw==null || data==null) throw new NullPointerException();
		isAuthorized(passw);
		E removed= null;
		for (Category<E> cat: this.categories) {
			if (cat.containsData(data)) {
				removed= data;
				cat.removeData(data);
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
	 * EFFECTS: Rimuove un dato, se presente
	 * 			PRE: <category, {data_0, ... , data_n, dato}>
	 * 			POST: <category, {data_0, ... , data_n}>
	 */
	
	
	/* Aggiunge un like al dato se rispettati i vincoli di identità */
	public void insertLike (String friend, E data) throws NullPointerException, UnAuthorizedLikeException, NoDataFoundException, DuplicateException {
		if (friend==null || data==null) throw new NullPointerException();
		Category<E> tmp= null;
		for (Category<E> cat: this.categories) {
			if (cat.containsData(data)) tmp= cat;
		}
		if (tmp== null) throw new NoDataFoundException("No such data: " + data.display());
		if (data.getFriendsLiked().contains(friend)) throw new DuplicateException (friend + " already liked this element");
		if (tmp.containsFriends(friend)) data.addLike(friend);
		else throw new UnAuthorizedLikeException (friend + " cannot like this data");
	}
	/*REQUIRES: friend!=null && data!=null && friend ha accesso al dato && dato presente && like non presente
	 * THROWS: se friend==null || dato==null lancia una NullPointerException (disponibile in Java, unchecked)
	 * 		   se friend non ha accesso al dato lancia una UnAuthorizedLikeException (non disponibile in Java, checked)
	 * 		   se dato non presente lancia una NoDataFoundException (non disponibile in Java, checked)
	 * 		   se like già presente lancia una DuplicateException (non disponibile in Java, checked)
	 * MODIFIES: (this) => data.likes
	 * EFFECTS: Incrementa il contatore like del dato e aggiunge "friend" alla lista amici che hanno messo like, se non già presente
	 * 			PRE: <data, {friend_0, ... , friend_n}> , data.likes= i
	 * 			POST: <data, {friend_0, ... , friend_n, friend}> , data.likes= i+1
	 */
	
	/*Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità */
	public List<E> getDataCategory(String passw, String category) throws NullPointerException, InvalidPwdException, NoDataFoundException {
		if (passw==null || category==null) throw new NullPointerException();
		isAuthorized(passw);
		ArrayList<E> list= new ArrayList<E>();
		boolean found= false;
		for (Category<E> cat: this.categories) {
			if (cat.getName().equals(category)) {
				found= true;
				list= cat.getElements();
			}
		}
		if (!found) throw new NoDataFoundException("No such category: " + category);
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
	
	/* Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca 
	 * ordinati rispetto al numero di like se vengono rispettati i controlli di identità
	 */
	public Iterator<E> getIterator(String passw) throws NullPointerException, InvalidPwdException {
		if (passw==null) throw new NullPointerException();
		isAuthorized(passw);
		TreeSet <E> data= new TreeSet<E>();
		for (Category<E> cat: this.categories) {
				for (E e: cat.getElements()) {
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
		for (Category<E> cat : this.categories) {
			if (cat.getFriends().contains(friend)) {
				for (E e: cat.getElements()) {
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
