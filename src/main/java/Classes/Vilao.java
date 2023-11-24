package Classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "viloes")
public class Vilao implements Personagem {
    public Vilao() {
    };

    @Id
    private String nome;
    private String poder;
    private boolean podeVoar;

    public Vilao(String nome, String poder, boolean podeVoar) {
        this.nome = nome;
        this.poder = poder;
        this.podeVoar = podeVoar;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getPoder() {
        return poder;
    }

    @Override
    public String getVoar() {
        return podeVoar ? ", pode voar" : "";
    }

    @Override
    public void atualizar(String poder, boolean podeVoar) {
        this.poder = poder;
        this.podeVoar = podeVoar;
    }

    /*
     * public static Vilao getInstance(String nome, String poder, boolean podeVoar)
     * {
     * if(instance == null)
     * instance = new Vilao(nome, poder, podeVoar);
     * else{
     * instance.poder = poder;
     * instance.podeVoar = podeVoar;
     * }
     * return instance;
     * }
     */

}
