package main;
/**
 * @author KIMOU
 * @version 18/01/2018
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/*
 */

public class CompteDistant extends UnicastRemoteObject implements Compte{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double solde ;
	
	public CompteDistant() throws RemoteException {
		super();
		solde=0;
		
	}

	@Override
	public synchronized boolean retrait(double montant) throws RemoteException {
		// TODO Auto-generated method stub
		if ((solde - montant)>=0)
		{
			solde -= montant;
			return true;
		}
		else return false;
	}

	@Override
	public  synchronized boolean depot(double montant) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			/*
			 * ce thread permet de prouver que les methodes depot et retrait sont synchronisé
			 * cet exemple montre que si un client est entrain de faire un depot qui va durer 30seconde
			 * alors aucun autre client ne peut faire de retrait ou de depot avant que la premiere
			 * operation ne soit terminé
			 **/
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			solde += montant;
			return true;
		}catch(Exception e)
		{
			return false;	
		}
		
		
	}

	@Override
	public double getSolde() throws RemoteException {
		// TODO Auto-generated method stub
		return solde;
	}

}