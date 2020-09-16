package pr2_project;

import java.util.ArrayList;

/* Tipo di Dato presente in bacheca */

public class Data implements Comparable<Data> {
	//OVERVIEW: Tipo di dato modificabile e accessibile previa autorizzazione che descrive un elemento della bacheca,
	//		   la lista di persone vi hanno messo mi piace, il numero di like ottenuti
	//		   e la categoria (settata quando viene inserito in bacheca)
	//AF: <text, category, likes, {friendsliked.get(i)}> con forall i, 0<=i<friendsliked.size()
	//IR: text!=null  && friendsliked!=null && likes>=0 e likes= friendsliked.length()
	//	  category!=null se è stato inserito in bacheca
	//	  && forall i,j t.c. 0<=i<likes e 0<=j<likes => friendsliked(i)!=friendsliked(j)
	//    && forall i t.c. 0<=i<likes => friendsliked(i)!=null
	
	private String category;
	private String text;
	private int likes;
	private ArrayList<String> friendsliked;
	
	//costruttore
	public Data (String text) throws NullPointerException {
		if (text==null) throw new NullPointerException();
		this.text= text;
		this.likes= 0;
		friendsliked= new ArrayList<String>();
	}
	/* REQUIRES: text!=null
	 * THROWS: se text==null lancia una IllegalDataException (non disponibile, unchecked)
	 * EFFECTS: inizializza il dato con numero likes a 0 e lista di amici che hanno messo like vuota
	 */
	
	/* Restituisce la categoria associata al dato */
	public String getCategory () {
		return category;
	}
	/* Restituisce this.category */
	
	/* Setta la categoria quando il dato viene inserito in bacheca */
	public void setCategory (String category) {
		this.category= category;
	}
	/*MOFIESI: (this) => this.category
	 * EFFECTS: Aggiorna il campo this.category del dato
	 */
	
	/* Restituisce il contenuto del dato */
	public String getData () {
		return text;
	}
	/*EFFECTS: Restituisce this.text */
	
	/* Restituisce il numero di likes associato al dato */
	public int getLikes () {
		return likes;
	}
	/*EFFECTS: Restituisce this.likes */
	
	/* Restituisce la lista di friends che hanno messo mi piace al dato */
	public ArrayList<String> getFriendsLiked () {
		return friendsliked;
	}
	/* Restituisce this.friendsliked, lista String con i nomi dei friends */
	
	/* Aggiunge un like al dato per conto di friend */
	public void addLike (String friend) {
		likes+= 1;
		friendsliked.add(friend);
	}
	/*MODIFIES: (this) => this.friendsliked && this.likes
	 * EFFECTS: Aggiunge friend alla lista friendsliked e incrementa di 1 il contatore likes
	 * 			PRE: <friend_0, ... , friend_n> && likes
	 * 			POST: <friend_0, ... , friend_n, friend> && likes= likes+1
	 */
	
	/* Inizializza i campi della copia dato a quelli del dato fornito*/
	public Data (Data data) {
		this.category= data.getCategory();
		this.text= data.getData();
		this.likes= data.getLikes();
		this.friendsliked= data.getFriendsLiked();
	}
	/*EFFECTS: Associa ai campi del dato copia quelli del dato originale
	
	/* Restituisce una copia del dato*/
	public Data createCopy() {
		return new Data(this);
	}
	/*EFFECTS: Restituisce una copia del dato;
	
	/*Metodo rappresentazione testuale oggetto */
    public String display() {
    	return "|| Dato: " + this.getData() + " ||";
    }
    /*EFFECTS: Restituisce la rappresentazione dell'oggetto (testo) e il numero di likes che ha ottenuto */
    
    
    /* Implementazione Comparable<Data>, controlla prima il numero di like e poi il text
     * necessaria per gli Iterator */
    public int compareTo(Data dato) throws NullPointerException {
    	if (dato==null) throw new NullPointerException();
        if(this.equals(dato)) return 0;
        if(this.likes == dato.getLikes()) {
            if(dato.getCategory() != null && this.getCategory() != null) {
                if(this.category.equals(dato.getCategory())) return this.text.compareTo(dato.getData());
                return this.category.compareTo(dato.getCategory());
            } 
            else {
                return this.text.compareTo(dato.getData());
            }
        }
        return dato.getLikes() - this.likes;
    }
    /*REQUIRES: dato!=null
     * THROWS: se dato==null lancia una NullPointerException
     * EFFECTS: Effettua una comaprazione lexicografica
     */
}

