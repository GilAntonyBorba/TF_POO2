package Classes;

public class FabricaHeroi implements FabricaPersonagem {

    @Override
    public Personagem criarPersonagem(String nome, String poder, boolean podeVoar) {
        for (Heroi h : Principal.personagensHerois) {
            if (h.getNome().equals(nome)) {
                h.atualizar(poder, podeVoar);
                System.out.println("Já adicionado!");
                return h;
            }
        }
        Heroi i = new Heroi(nome, poder, podeVoar);
        Principal.personagensHerois.add(i);
        return i;
    }

    @Override
    public void criarPersonagem(Personagem p) {
        boolean podeVoar = p.getVoar() == "" ? false : true;
        criarPersonagem(p.getNome(), p.getPoder(), podeVoar);
    }
}
