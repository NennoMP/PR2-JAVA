package pr2_project;

/* Interfaccia DataBoards */

import pr2_exceptions.*;
import java.util.List;
import java.util.Iterator;

public interface IDataBoard<E extends Data> {
	//OVERVIEW: Tipo di dato modificabile, previa autorizzazione, che descrive una bacheca contenente una lista di categorie elementi
	//          ed una lista di amici che possono accedere ai dati della categoria a cui sono autorizzati
	//TYPICAL ELEMENT: {<password, <category_0, {SetOfElements_0} , {SetOfFriends_0}> , ... , <category_n, {SetOfElements_n}, {SetOfFriends_n}>}
	/*N.B: La presenza del campo passw in alcuni metodi garantisce la privacy dei dati
	 * 	   pertanto la loro esecuzione avviene solo se tale controllo di identità è rispettato */
	
	/*Crea una categoria di dati se vengono rispettati i controlli di identità */
	public void createCategory (String category, String passw) throws NullPointerException, InvalidPwdException, DuplicateException;
	
	/*Rimuove una categoria di dati se vengono rispettati i controlli di identità */
	public void removeCategory(String category, String passw) throws NullPointerException, InvalidPwdException, NoDataFoundException;
	
	/*Aggiunge un amico ad una categoria di dati se vengono rispettati i controlli di identità */
	public void addFriend(String category, String passw, String friend) throws NullPointerException, InvalidPwdException, NoDataFoundException, DuplicateException;
	
	/*Rimuove un amico da una categoria di dati se vengono rispettati i controlli di identità */
	public void removeFriend(String category, String passw, String friend) throws NullPointerException, InvalidPwdException, NoDataFoundException;
	
	/*Inserisce un dato in bacheca se vengono rispettati i controlli di identità*/
	public boolean put(String passw, E dato, String categoria) throws NullPointerException, InvalidPwdException, NoDataFoundException, DuplicateException;
	
	/*Restituisce una copia del dato in bacheca se vengono rispettati i controlli di identità */
	public E get(String passw, E dato) throws NullPointerException, InvalidPwdException, NoDataFoundException;
	
	/* Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità */
	public E remove(String passw, E dato) throws NullPointerException, InvalidPwdException, NoDataFoundException;
	
	/*Aggiunge un like a un dato se vengono rispettati i controlli di identità */
	void insertLike(String friend, E data) throws NullPointerException, UnAuthorizedLikeException, NoDataFoundException, DuplicateException;
	
	/*Crea la lista dei dati in bacheca di una determinata categoria se vengono rispettati i controlli di identità */
	public List<E> getDataCategory(String passw, String category) throws NullPointerException, InvalidPwdException, NoDataFoundException;
	
	/*Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like
	* se vengono rispettati i controlli di identità */
	public Iterator<E> getIterator(String passw) throws NullPointerException, InvalidPwdException;
	
	/*Restituisce un iteratore (senza remove) che genera tutti i dati in bacheca condivisi */
	public Iterator<E> getFriendIterator(String friend) throws NullPointerException;

}
