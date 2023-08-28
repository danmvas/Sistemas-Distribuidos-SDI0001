
/** HelloWorld.java **/
import java.rmi.*;

public interface BolsaGeometrica extends Remote {
   // public pegar a peça, servidor retorna para o cliente -> o cliente vai usar, é chamado pelo cliente e desenvolvido pelo servidor
   public String getPecas(int qtdPecas, char peca) throws RemoteException;
   public void endClient() throws RemoteException;
}
