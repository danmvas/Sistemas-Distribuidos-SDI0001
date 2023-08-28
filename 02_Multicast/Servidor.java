import java.io.*;

public class Servidor {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String port, nro_geradores, classGer, maqGer, msgGer, nro_consumidores, classConsu, maqCons;
        
        port = input.readLine().split(" ")[1];
        nro_geradores = input.readLine().split(" ")[1];
        classGer = input.readLine().split(" ")[1];
        maqGer = input.readLine();
        msgGer = input.readLine();
        nro_consumidores = input.readLine().split(" ")[1];
        classConsu = input.readLine().split(" ")[1];
        maqCons = input.readLine();
        
        for (int i = 1; i <= Integer.parseInt(nro_consumidores); i++) {
            String[] maqConsArr = maqCons.split(" ");
            String maqct = String.valueOf(i + 1);
            String maqConsStr = maqConsArr[Integer.parseInt(maqct)];
            System.out.print("Lançando Consumidor em ");
            Runtime.getRuntime().exec("ssh " + maqConsStr + " hostname");
            String dir = System.getProperty("user.dir");
            String bas = dir.substring(dir.lastIndexOf("/") + 1);
            Runtime.getRuntime().exec("scp -r " + dir + " " + maqConsStr + ":/tmp/");
            Runtime.getRuntime().exec("ssh " + maqConsStr + " cd /tmp/" + bas + "; java " + classConsu + " " + port + " " + nro_geradores + " &");
        }
        
        Thread.sleep(500);
        
        for (int i = 1; i <= Integer.parseInt(nro_geradores); i++) {
            String[] maqGerArr = maqGer.split(" ");
            String[] msgGerArr = msgGer.split(" ");
            String maqct = String.valueOf(i + 1);
            String maqGerStr = maqGerArr[Integer.parseInt(maqct)];
            String msgGerStr = msgGerArr[Integer.parseInt(maqct)];
            System.out.print("Lançando Gerador em ");
            Runtime.getRuntime().exec("ssh " + maqGerStr + " hostname");
            String dir = System.getProperty("user.dir");
            String bas = dir.substring(dir.lastIndexOf("/") + 1);
            Runtime.getRuntime().exec("scp -r " + dir + " " + maqGerStr + ":/tmp/");
            Runtime.getRuntime().exec("ssh " + maqGerStr + " cd /tmp/" + bas + "; java " + classGer + " " + port + " " + msgGerStr);
        }        
    }
    
}
