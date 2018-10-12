package test;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import servidor.Server;

public class IniciarServidor {

	public static void main(String[] args) {
		try {
			new Server();
		} catch (RemoteException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "No pude arrancar el servidor");
		}
	}

}
