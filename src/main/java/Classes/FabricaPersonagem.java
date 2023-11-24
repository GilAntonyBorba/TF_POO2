package Classes;

public interface FabricaPersonagem {
    Personagem criarPersonagem(String nome, String poder, boolean podeVoar);

    void criarPersonagem(Personagem p);
}
