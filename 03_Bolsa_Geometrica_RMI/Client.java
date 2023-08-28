
/** HelloClient.java **/
import java.io.*;
import java.util.*;
import java.rmi.registry.*;
import java.net.InetAddress;

public class Client {

  public static void main(String[] args) {
    String host = (args.length < 1) ? null : args[0];
    try {
      // Obtém uma referência para o registro do RMI
      Registry registry = LocateRegistry.getRegistry(host,4736);

      // Obtém a stub do servidor
      BolsaGeometrica stub= (BolsaGeometrica) registry.lookup("myRMIBG");

      InetAddress addr = InetAddress.getLocalHost();
      String hostname = addr.getHostName();
      System.out.println("##  Cliente ("+hostname+") "+args[1]+"  ##");

//      String 
      try {
        System.out.println("Status: atentido por " + args[0]);
      } catch (Exception ex){
        System.out.println("Status: nao atendido" + args[0]);
        ex.printStackTrace();
      }


      // Chama o método do servidor e imprime a mensagem
      
      
      
      Deposito itens = getItens();
      Vector<ClientObj> clientes = getPedidos();
      for(ClientObj cli: clientes){
        if (itens.verificaPeca((char) cli.getQtdCli(), cli.getpCli())){
            stub.getPecas(cli.getQtdCli(), cli.getpCli());
            System.out.println("pCli"+ cli.getNumCli() +": " + cli.getpCli());

            System.out.println("###########");
        }
      }

      stub.endClient();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  public static Deposito getItens(){
        File myObj = new File("ambiente.in");
        Scanner myReader;
        Deposito itens = null;
        try {
            myReader = new Scanner(myObj);
            String data = ""; 
            while (data.split(" ")[0] != "Npecas") {
                data = myReader.nextLine();
            }
            int np = Integer.parseInt(data.split(" ")[2]);
            data = myReader.nextLine();
            data.replace("pecas = ", "");
            String pe = data;
            itens = new Deposito(np, pe);
            myReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(myObj.getAbsolutePath());
        }
            
        return itens;
  }

  public static Vector<ClientObj> getPedidos() {
      File myObj = new File("ambiente.in");
        Scanner myReader;
        Deposito itens = null;
        Vector<ClientObj> clientes = new Vector<>();
        try {
            myReader = new Scanner(myObj);
            String data = ""; 
            while (data != "## Clientes ##") {
                data = myReader.nextLine();
            }
            while (true) {
                int cont = 0;
                if (myReader.hasNext()) {
                    ClientObj c = new ClientObj();
                    data = myReader.nextLine();
                    c.setNumCli(cont);
                    c.setpCli(data.split(" ")[2].charAt(0));
                    data = myReader.nextLine();
                    c.setQtdCli(Integer.parseInt(data.split(" ")[2]));
                    clientes.add(c);
                }else{
                    break;
                }
            }
            
            myReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(myObj.getAbsolutePath());
        }
        return clientes;
  }


}
