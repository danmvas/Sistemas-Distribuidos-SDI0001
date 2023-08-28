import java.util.List;
import java.util.Map;

public class Deposito {
    private int nPecas;
    private Map<Character, Integer> pecas;
    
    public Deposito(int nPecas, String pecas){
        this.nPecas = nPecas;
        
        
        for (String peca : pecas.split(" ")) {
            if (this.pecas.containsKey(peca)){
                this.pecas.put(peca.toCharArray()[0], this.pecas.get(peca) + 1);
            }
        }
    }

    public boolean verificaPeca(char peca, int qntPeca){
        if (this.pecas.get(peca) >= qntPeca) {
            this.pecas.put(peca, this.pecas.get(peca) - qntPeca);
            return true;
        }
        return false;
    }
//        int cont = 0;
//        for (int i = 0, i < peca.length, i++){
//            if (pecas[i] == peca){
//                cont = cont + 1;
//            }
//        }
//        if (cont =< qntPeca){
//            return true;
//        } else {
//            return false;
//        }
//      }

    public int getnPecas() {
        return nPecas;
    }

    public void setnPecas(int nPecas) {
        this.nPecas = nPecas;
    }

    public Map<Character, Integer> getPecas() {
        return pecas;
    }

    public void setPecas(Map<Character, Integer> pecas) {
        this.pecas = pecas;
    }


}