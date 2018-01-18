package main;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;

/**
 * Client permettant d'interroger un serveur distant.
 * @author Cyril Rabat
 * @version 25/10/13
 */
public class LancerClient {

    /**
     * Methode principale.
     * @param args inutilise
     */
	private static Compte compte = null;
	private static Registry registry =null;
	static Scanner sc = null;
	static boolean trouver=false;
	
    public static void main(String[] args) {

	//Recuperation du registry
	try {
		registry = LocateRegistry.getRegistry(2018);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.exit(-1);
	}
	String ID;
	System.out.println("Donner l'identifiant du compte: ou \" q \" pour quitter");
	do{
    	sc  = new Scanner(System.in);
    	ID = sc.nextLine();
    	
    }while(!TrouverCompte(ID) && !ID.equals("q"));
	
		//Menu gestion du compte
		if(trouver)
		MenuGestionCompte(compte);

    }
    
    
    public static void MenuGestionCompte(Compte compte) {
    	try {

    	    System.out.print("Le solde du compte est : " + compte.getSolde()+"\n");
    		boolean continuer =true;
    		String choix="";
    		double montant;
    		do{
    			
    		    System.out.println("***************   1- Faire un Depot   **************");
    		    System.out.println("***************   2- Faire un Retrait **************");
    		    System.out.println("***************   q- Quitter          **************");
    		    choix = String.valueOf(sc.nextLine());
    		    switch(choix) {
    		    	
    		    case "1":
    		    	System.out.println("Donner le montant à deposer");
    		    	montant = Double.valueOf(sc.nextLine());
    		    	if(compte.depot(montant))
    		    		System.out.print("Votre nouveau solde est : " + compte.getSolde()+"\n\n");
    		    	break;
    		    case "2":
    			    	System.out.println("Donner le montant à retirer");
    			    	montant=Double.valueOf(sc.nextLine());
    			    	if(compte.retrait(montant))
    			    		System.out.print("Votre nouveau solde est : " + compte.getSolde()+"\n\n");
    			    	else
    			    		System.out.print("Retrait impossible car vottre solde est inssufisant \n\n");
    		    	break;
    		    case "q":	
    		    	System.out.print("Au revoir\n");
    		    	continuer =false;
    		    }
    		    		
    		}while(continuer);
    		sc.close();
    		
    	} catch(RemoteException e) {
    	    System.out.println("Erreur lors de l'acces aux methodes : " + e);
    	    System.exit(-1);
    	}
    }
    
    public static boolean TrouverCompte(String ID) {
    	// Récupération du compte distant
    	
    	try {
    		compte = (Compte)registry.lookup(ID);
    		trouver=true;
    		return true;
		    } catch(RemoteException e) {
			  
		    	System.out.println("Impossible d'accerder au compte distant");
			} catch(NotBoundException e) {
	
				System.out.println("Aucun compte ne correspond à l'identifiant saisi");
			}
    	System.out.println("Ressayez: ");
    	return false;
    }

}
