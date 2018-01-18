package main;
/**
 * @author KIMOU
 * @version 18/01/2018
 * Interface correspondant au compte
 * Les differente methodes sont retrait , depot, getSolde
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compte extends Remote {

	/**
	 methode retrait permet au clien de faire des retraits
	 */
	public boolean retrait(double montant) throws RemoteException;
	
	/**
	 methode retrait permet au clien de faire des depot
	 */
	public boolean depot(double montant) throws RemoteException;

	/**
	 * retourne le solde
	 */
	public double getSolde() throws RemoteException;
}