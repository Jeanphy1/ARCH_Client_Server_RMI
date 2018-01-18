package main;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Serveur qui cree un calendrier distant et le met a disposition des clients.
 * @author Cyril Rabat
 * @version 25/10/13
 */
public class LancerServeur {

    /**
     * Methode principale.
     * @param args inutilise
     */
	 
    public static void main(String[] args) {
	// Creation du RMI registry
    	Registry registry=null;
	try {
		registry=LocateRegistry.createRegistry(2018);
	} catch(RemoteException e) {
	    System.err.println("Erreur lors de la recuperation du registry : " + e);
	    System.exit(-1);
	}
	
	// Creation de l'objet distant
	CompteDistant compte=null;
	try {
	    compte = new CompteDistant();
	} catch(RemoteException e) {
	    System.err.println("Erreur lors de la creation de l'objet : " + e);
	    System.exit(-1);
	}

	// Enregistrement aupres du Registry
	try {
	    // Enregistrement de l'objet sur le Registry
	    registry.rebind("moncompte", compte);
	} catch(RemoteException e) {
	    System.err.println("Probleme avec l'URL : " + e);
	    System.exit(-1);
	}

	System.out.println("Serveur pret");
    }

}
