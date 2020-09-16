package pr2_project;

import pr2_exceptions.*;

import java.util.Iterator;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

/* Main*/

public class Test {
    public static void main(String[] args) {
    	Test.test1();
    	Test.test2();
    }
    
    /* Testing DataBoard */
    public static void test1() {
        try {
            System.out.println("\nBatteria di test implementazione DataBoard");

            // Passwords
            String PWD_Matteo= "Morosini629";
            String PWD_Stefano= "Topolino621";
            
            // Databoards
            DataBoard<Data> DATABOARD_vuota= new DataBoard<Data>("vuoto");
            DataBoard<Data> DATABOARD_illegal;
            DataBoard<Data> DATABOARD_Matteo =  new DataBoard<Data>(PWD_Matteo);
            DataBoard<Data> DATABOARD_Stefano = new DataBoard<Data>(PWD_Stefano);
            
            
            // Data
            Data ELEMENT_tmp= new Data("tmp");


            /*=================================================================================================
            * @ DATABOARDS TESTS @
            * =================================================================================================
            */
            // ------------------------------------------------------------------------------------------------
            /* CREAZIONE DATABOARD */
            // ------------------------------------------------------------------------------------------------
            System.out.println("\n: (1) TEST SULLE BOARD");
            try {
            	System.out.println("\n -->: Creazione collezione con password vuota(null) => NullPointerException");
            	DATABOARD_illegal= new DataBoard<Data>(null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            
            /*=================================================================================================
            * @ CATEGORIES TESTS @
            * =================================================================================================
            */
            System.out.println("\n: (2) TEST SULLE CATEGORIE");
            System.out.println("Aggiungo categorie alle DataBoard");
            DATABOARD_Matteo.createCategory("frutta", PWD_Matteo);
            DATABOARD_Matteo.createCategory("colori", PWD_Matteo);
            DATABOARD_Stefano.createCategory("frutta", PWD_Stefano);
            DATABOARD_Stefano.createCategory("colori", PWD_Stefano);
            DATABOARD_Stefano.createCategory("sports", PWD_Stefano);
            DATABOARD_Stefano.removeCategory("frutta", PWD_Stefano);
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO CATEGORIA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento categoria vuota(null) => NullPointerException");
            	DATABOARD_Matteo.createCategory(null, PWD_Matteo);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento categoria con password vuota(null) => NullPointerException");
            	DATABOARD_Matteo.createCategory("category", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento categoria con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.createCategory("category", PWD_Stefano);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento categoria già presente => DuplicateException ");
            	DATABOARD_Matteo.createCategory("frutta", PWD_Matteo);
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RIMOZIONE CATEGORIA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Rimozione categoria vuota(null) => NullPointerException");
            	DATABOARD_Matteo.removeCategory(null, PWD_Matteo);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione categoria con password vuota(null) => NullPointerException");
            	DATABOARD_Matteo.removeCategory("frutta", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione categoria con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.removeCategory("frutta", PWD_Stefano);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione categoria non esistente => NoDataFoundException");
            	DATABOARD_Matteo.removeCategory("nonpresente", PWD_Matteo);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            
            
            /*=================================================================================================
            * @ FRIENDS TESTS @
            * =================================================================================================
            */
            System.out.println("\n: (3) TEST SUGLI AMICI");
            DATABOARD_Matteo.addFriend("colori", PWD_Matteo, "Emilio");
            DATABOARD_Matteo.addFriend("frutta", PWD_Matteo, "Carlo");
            DATABOARD_Matteo.addFriend("colori", PWD_Matteo, "Carlo");
            DATABOARD_Matteo.removeFriend("colori", PWD_Matteo, "Carlo");
            
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO FRIEND */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento amico con categoria vuota(null) => NullPointerException");
            	DATABOARD_Matteo.addFriend(null, PWD_Matteo, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico con password vuota(null) => NullPointerException");
            	DATABOARD_Matteo.addFriend("frutta", null, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try  {
            	System.out.println("\n -->: Inserimento amico vuoto(null) => NullPointerException");
            	DATABOARD_Matteo.addFriend("frutta", "password", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico con password invalida: => InvalidPwdException");
            	DATABOARD_Matteo.addFriend("frutta", PWD_Stefano, "friend" );
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico con categoria inesistente => NoDataFoundException");
            	DATABOARD_Matteo.addFriend("nonpresente", PWD_Matteo, "friend");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico già presente => DuplicateException");
            	DATABOARD_Matteo.addFriend("colori", PWD_Matteo, "Emilio");
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RIMOZIONE FRIEND */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Rimozione amico con categoria vuota(null) => NullPointerException");
            	DATABOARD_Matteo.removeFriend(null, PWD_Matteo, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione amico con password vuota(null) => NullPointerException");
            	DATABOARD_Matteo.removeFriend("frutta", null, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try  {
            	System.out.println("\n -->: Rimozione amico vuoto(null) => NullPointerException");
            	DATABOARD_Matteo.removeFriend("frutta", "password", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione amico con password invalida: => InvalidPwdException");
            	DATABOARD_Matteo.removeFriend("frutta", PWD_Stefano, "friend" );
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione amico con categoria inesistente => NoDataFoundException");
            	DATABOARD_Matteo.removeFriend("nonpresente", PWD_Matteo, "friend");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico inesistente => NoDataFoundException");
            	DATABOARD_Matteo.removeFriend("frutta", PWD_Matteo, "nonpresente");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            
            /*=================================================================================================
            * @ DATA TESTS @
            * =================================================================================================
            */
            System.out.println("\n (4) TEST SUI DATI");
            Data ELEMENT_new= new Data("rosso");
            Data ELEMENT_new2= new Data("giallo");
            Data ELEMENT_new3= new Data("blu");
            DATABOARD_Matteo.put(PWD_Matteo, ELEMENT_new, "colori");
            DATABOARD_Matteo.put(PWD_Matteo, ELEMENT_new2, "colori");
            DATABOARD_Matteo.put(PWD_Matteo, ELEMENT_new3, "colori");
            DATABOARD_Matteo.get(PWD_Matteo, ELEMENT_new);
            DATABOARD_Matteo.remove(PWD_Matteo, ELEMENT_new2);
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO DATA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento dato con password vuota => NullPointerException");
            	DATABOARD_Matteo.put(null, ELEMENT_tmp, "colori");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato con dato vuoto => NullPointerException");
            	DATABOARD_Matteo.put(PWD_Matteo, null, "colori");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }try {
            	System.out.println("\n -->: Inserimento dato con categoria vuota => NullPointerException");
            	DATABOARD_Matteo.put(PWD_Matteo, ELEMENT_tmp, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.put(PWD_Stefano, ELEMENT_tmp, "colori");
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato con categoria inesistente => NoDataFoundException");
            	DATABOARD_Matteo.put(PWD_Matteo, ELEMENT_tmp, "nonpresente");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato già presente => DuplicateException");
            	DATABOARD_Matteo.put(PWD_Matteo, ELEMENT_new, "colori");
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RETURN COPIA DATA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorna copia dato con password vuota => NullPointerException");
            	DATABOARD_Matteo.get(null, ELEMENT_new);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorna copia dato vuoto => NullPointerException");
            	DATABOARD_Matteo.get(PWD_Matteo, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorna dato con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.get(PWD_Stefano, ELEMENT_new);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorna dato con dato inesistente => NoDataFoundException");
            	DATABOARD_Matteo.get(PWD_Matteo, ELEMENT_tmp);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RIMOZIONE DATA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Rimozione dato con password vuota => NullPointerException");
            	DATABOARD_Matteo.remove(null, ELEMENT_new);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione dato vuoto => NullPointerException");
            	DATABOARD_Matteo.remove(PWD_Matteo, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione dato con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.remove(PWD_Stefano, ELEMENT_new);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione dato inesistente => NoDataFoundException");
            	DATABOARD_Matteo.remove(PWD_Matteo, ELEMENT_tmp);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            
            
            /*=================================================================================================
            * @ LIKES TESTS @
            * =================================================================================================
            */
            System.out.println("\n (5): Test sui like");
            DATABOARD_Matteo.insertLike("Emilio", ELEMENT_new);
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO LIKE */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento like con friend vuoto => NullPointerException");
            	DATABOARD_Matteo.insertLike(null, ELEMENT_new);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like con data vuoto => NullPointerException");
            	DATABOARD_Matteo.insertLike("friend", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like con friend non autorizzato => UnAuthorizedLikeException");
            	DATABOARD_Matteo.insertLike("nonautorizzato", ELEMENT_new);
            }
            catch (UnAuthorizedLikeException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like con dato inesistente => NoDataFoundException");
            	DATABOARD_Matteo.insertLike("Emilio", ELEMENT_tmp);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like già esistente => DuplicateException");
            	DATABOARD_Matteo.insertLike("Emilio", ELEMENT_new);
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            
            /*=================================================================================================
            * @ LISTS TESTS @
            * =================================================================================================
            */
            System.out.println("\n (6): Test sulle liste");
            System.out.println("\n -->: Rappresentazione risultato getDataCategory su DATABOARD_Matteo e categoria 'colori'");
            DATABOARD_Matteo.getDataCategory(PWD_Matteo, "colori");
            System.out.println("\n -->: Rappresentazione risultato getDataCategory su DATABOARD_Matteo e categoria 'frutta'");
            DATABOARD_Matteo.getDataCategory(PWD_Matteo, "frutta");
            // ------------------------------------------------------------------------------------------------
            /* RETURN LISTA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorno lista con password vuota => NullPointerException");
            	DATABOARD_Matteo.getDataCategory (null, "colori");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno lista con categoria vuota => NullPointerException");
            	DATABOARD_Matteo.getDataCategory(PWD_Matteo, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno lista con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.getDataCategory(PWD_Stefano, "colori");
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno lista con categoria inesistente => NoDataFoundException");
            	DATABOARD_Matteo.getDataCategory(PWD_Matteo, "nonpresente");
            }
            catch (NoDataFoundException e) {  
            	System.out.println(e);
            }
             
            /*=================================================================================================
            * @ RETURN ITERATORS TESTS @
            * =================================================================================================
            */
            System.out.println("\n (7): Test sugli iteratori");
            Iterator<Data> iterator= DATABOARD_Matteo.getIterator(PWD_Matteo);
            System.out.println("\n -->: Rappresentazione risultato di getIterator su 'DATABOARD_Matteo'");
            while (iterator.hasNext()) {
            	Data el= iterator.next();
            	System.out.println(el.display());
            }
            System.out.println("\n -->: Rappresentazione risultato di getFriendIterator su 'Emilio'");
            iterator= DATABOARD_Matteo.getFriendIterator("Emilio");
            while (iterator.hasNext()) {
            	Data el= iterator.next();
            	System.out.println(el.display());
            }
            // ------------------------------------------------------------------------------------------------
            /* DATA ITERATOR */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorno iteratore data con password vuoto => NullPointerException");
            	DATABOARD_Matteo.getIterator(null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno iteratore data con password invalida => InvalidPwdException");
            	DATABOARD_Matteo.getIterator(PWD_Stefano);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* FRIEND ITERATOR */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorno iteratore con friend vuoto => NullPointerException");
            	DATABOARD_Matteo.getFriendIterator(null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
        }
        catch (Exception e) {
        	System.out.println("ERROR: " + e);
        }
}
    
    /* Testing DataBoard2 */
    public static void test2() {
        try {
            System.out.println("\nBatteria di test implementazione DataBoard2");

            // Passwords
            String PWD_Matteo= "Morosini629";
            String PWD_Stefano= "Topolino621";
            
            // Databoards
            DataBoard2<Data> DATABOARD2_vuota= new DataBoard2<Data>("vuoto");
            DataBoard2<Data> DATABOARD2_illegal;
            DataBoard2<Data> DATABOARD2_Matteo =  new DataBoard2<Data>(PWD_Matteo);
            DataBoard2<Data> DATABOARD2_Stefano = new DataBoard2<Data>(PWD_Stefano);
            
            // Categories

            
            // Elements
            Data ELEMENT_tmp= new Data("tmp");

            /*=================================================================================================
            * @ DATABOARDS TESTS @
            * =================================================================================================
            */
            // ------------------------------------------------------------------------------------------------
            /* CREAZIONE DATABOARD */
            // ------------------------------------------------------------------------------------------------
            System.out.println("\n: (1) TEST SULLE BOARD");
            try {
            	System.out.println("\n -->: Creazione collezione con password vuota(null) => NullPointerException");
            	DATABOARD2_illegal= new DataBoard2<Data>(null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            
            /*=================================================================================================
            * @ CATEGORIES TESTS @
            * =================================================================================================
            */
            System.out.println("\n: (2) TEST SULLE CATEGORIE");
            System.out.println("Aggiungo categorie alle DataBoard");
            DATABOARD2_Matteo.createCategory("frutta", PWD_Matteo);
            DATABOARD2_Matteo.createCategory("colori", PWD_Matteo);
            DATABOARD2_Stefano.createCategory("frutta", PWD_Stefano);
            DATABOARD2_Stefano.createCategory("colori", PWD_Stefano);
            DATABOARD2_Stefano.createCategory("sports", PWD_Stefano);
            DATABOARD2_Stefano.removeCategory("frutta", PWD_Stefano);
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO CATEGORIA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento categoria vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.createCategory(null, PWD_Matteo);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento categoria con password vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.createCategory("category", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento categoria con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.createCategory("category", PWD_Stefano);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento categoria già presente => DuplicateException ");
            	DATABOARD2_Matteo.createCategory("frutta", PWD_Matteo);
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RIMOZIONE CATEGORIA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Rimozione categoria vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.removeCategory(null, PWD_Matteo);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione categoria con password vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.removeCategory("frutta", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione categoria con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.removeCategory("frutta", PWD_Stefano);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione categoria non esistente => NoDataFoundException");
            	DATABOARD2_Matteo.removeCategory("sports", PWD_Matteo);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            
            
            /*=================================================================================================
            * @ FRIENDS TESTS @
            * =================================================================================================
            */
            System.out.println("\n: (3) TEST SUGLI AMICI");
            DATABOARD2_Matteo.addFriend("colori", PWD_Matteo, "Emilio");
            DATABOARD2_Matteo.addFriend("colori", PWD_Matteo, "Carlo");
            DATABOARD2_Matteo.removeFriend("colori", PWD_Matteo, "Carlo");
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO FRIEND */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento amico con categoria vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.addFriend(null, PWD_Matteo, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico con password vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.addFriend("frutta", null, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try  {
            	System.out.println("\n -->: Inserimento amico vuoto(null) => NullPointerException");
            	DATABOARD2_Matteo.addFriend("frutta", "password", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico con password invalida: => InvalidPwdException");
            	DATABOARD2_Matteo.addFriend("frutta", PWD_Stefano, "friend" );
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico con categoria inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.addFriend("nonpresente", PWD_Matteo, "friend");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico già presente => DuplicateException");
            	DATABOARD2_Matteo.addFriend("colori", PWD_Matteo, "Emilio");
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RIMOZIONE FRIEND */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Rimozione amico con categoria vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.removeFriend(null, PWD_Matteo, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione amico con password vuota(null) => NullPointerException");
            	DATABOARD2_Matteo.removeFriend("frutta", null, "friend");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try  {
            	System.out.println("\n -->: Rimozione amico vuoto(null) => NullPointerException");
            	DATABOARD2_Matteo.removeFriend("frutta", "password", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione amico con password invalida: => InvalidPwdException");
            	DATABOARD2_Matteo.removeFriend("frutta", PWD_Stefano, "friend" );
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione amico con categoria inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.removeFriend("nonpresente", PWD_Matteo, "friend");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento amico inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.removeFriend("frutta", PWD_Matteo, "nonpresente");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            
            /*=================================================================================================
            * @ DATA TESTS @
            * =================================================================================================
            */
            System.out.println("\n (4) TEST SUI DATI");
            Data ELEMENT_new= new Data("rosso");
            Data ELEMENT_new2= new Data("giallo");
            Data ELEMENT_new3= new Data("blu");
            DATABOARD2_Matteo.put(PWD_Matteo, ELEMENT_new, "colori");
            DATABOARD2_Matteo.put(PWD_Matteo, ELEMENT_new2, "colori");
            DATABOARD2_Matteo.put(PWD_Matteo, ELEMENT_new3, "colori");
            DATABOARD2_Matteo.get(PWD_Matteo, ELEMENT_new);
            DATABOARD2_Matteo.remove(PWD_Matteo, ELEMENT_new2);
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO DATA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento dato con password vuota => NullPointerException");
            	DATABOARD2_Matteo.put(null, ELEMENT_tmp, "colori");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato con dato vuoto => NullPointerException");
            	DATABOARD2_Matteo.put(PWD_Matteo, null, "colori");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }try {
            	System.out.println("\n -->: Inserimento dato con categoria vuota => NullPointerException");
            	DATABOARD2_Matteo.put(PWD_Matteo, ELEMENT_tmp, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.put(PWD_Stefano, ELEMENT_tmp, "colori");
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato con categoria inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.put(PWD_Matteo, ELEMENT_tmp, "nonpresente");
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento dato già presente => DuplicateException");
            	DATABOARD2_Matteo.put(PWD_Matteo, ELEMENT_new, "colori");
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RETURN COPIA DATA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorna copia dato con password vuota => NullPointerException");
            	DATABOARD2_Matteo.get(null, ELEMENT_new);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorna copia dato vuoto => NullPointerException");
            	DATABOARD2_Matteo.get(PWD_Matteo, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorna copia dato con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.get(PWD_Stefano, ELEMENT_new);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorna copia dato con dato inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.get(PWD_Matteo, ELEMENT_tmp);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* RIMOZIONE DATA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Rimozione dato con password vuota => NullPointerException");
            	DATABOARD2_Matteo.remove(null, ELEMENT_new);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione dato vuoto => NullPointerException");
            	DATABOARD2_Matteo.remove(PWD_Matteo, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione dato con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.remove(PWD_Stefano, ELEMENT_new);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Rimozione dato inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.remove(PWD_Matteo, ELEMENT_tmp);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            
            
            /*=================================================================================================
            * @ LIKES TESTS @
            * =================================================================================================
            */
            System.out.println("\n (5): Test sui like");
            DATABOARD2_Matteo.insertLike("Emilio", ELEMENT_new);
            // ------------------------------------------------------------------------------------------------
            /* INSERIMENTO LIKE */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Inserimento like con friend vuoto => NullPointerException");
            	DATABOARD2_Matteo.insertLike(null, ELEMENT_new);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like con data vuoto => NullPointerException");
            	DATABOARD2_Matteo.insertLike("friend", null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like con friend non autorizzato => UnAuthorizedLikeException");
            	DATABOARD2_Matteo.insertLike("nonautorizzato", ELEMENT_new);
            }
            catch (UnAuthorizedLikeException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like con dato inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.insertLike("Emilio", ELEMENT_tmp);
            }
            catch (NoDataFoundException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Inserimento like già esistente => DuplicateException");
            	DATABOARD2_Matteo.insertLike("Emilio", ELEMENT_new);
            }
            catch (DuplicateException e) {
            	System.out.println(e);
            }
            
            /*=================================================================================================
            * @ LISTS TESTS @
            * =================================================================================================
            */
            System.out.println("\n (6): Test sulle liste");
            System.out.println("\n -->: Rappresentazione risultato di getDataCategory su DATABOARD_Matteo e categoria 'colori'");
            DATABOARD2_Matteo.getDataCategory(PWD_Matteo, "colori");
            System.out.println("\n -->: Rappresentazione risultato di getDataCategory su DATABOARD_Matteo e categoria 'frutta'");
            DATABOARD2_Matteo.getDataCategory(PWD_Matteo, "frutta");
            // ------------------------------------------------------------------------------------------------
            /* RETURN LISTA */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorno lista con password vuota => NullPointerException");
            	DATABOARD2_Matteo.getDataCategory (null, "colori");
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno lista con categoria vuota => NullPointerException");
            	DATABOARD2_Matteo.getDataCategory(PWD_Matteo, null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno lista con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.getDataCategory(PWD_Stefano, "colori");
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno lista con categoria inesistente => NoDataFoundException");
            	DATABOARD2_Matteo.getDataCategory(PWD_Matteo, "nonpresente");
            }
            catch (NoDataFoundException e) {  
            	System.out.println(e);
            }
             
            /*=================================================================================================
            * @ RETURN ITERATORS TESTS @
            * =================================================================================================
            */
            System.out.println("\n (7): Test sugli iteratori");
            Iterator<Data> iterator = DATABOARD2_Matteo.getIterator(PWD_Matteo);
            System.out.println("\n -->: Rappresentazione risultato di getIterator su 'DATABOARD_Matteo'");
            while(iterator.hasNext()) {
               Data el = iterator.next();
               System.out.println(el.display());
            }
            System.out.println("\n -->: Rappresentazione risultato di getFriendIterator su 'Emilio'");
            iterator= DATABOARD2_Matteo.getFriendIterator("Emilio");
            while (iterator.hasNext()) {
            	Data el= iterator.next();
            	System.out.println(el.display());
            }
            // ------------------------------------------------------------------------------------------------
            /* DATA ITERATOR */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorno iteratore data con password vuoto => NullPointerException");
            	DATABOARD2_Matteo.getIterator(null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
            try {
            	System.out.println("\n -->: Ritorno iteratore data con password invalida => InvalidPwdException");
            	DATABOARD2_Matteo.getIterator(PWD_Stefano);
            }
            catch (InvalidPwdException e) {
            	System.out.println(e);
            }
            // ------------------------------------------------------------------------------------------------
            /* FRIEND ITERATOR */
            // ------------------------------------------------------------------------------------------------
            try {
            	System.out.println("\n -->: Ritorno iteratore con friend vuoto => NullPointerException");
            	DATABOARD2_Matteo.getIterator(null);
            }
            catch (NullPointerException e) {
            	System.out.println(e);
            }
        }
        catch (Exception e) {
        	System.out.println("ERROR: " +e);
        }
    }
}

    