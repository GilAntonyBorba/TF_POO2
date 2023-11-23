package Classes;

public class FabricaVilao implements FabricaPersonagem {

    @Override
    public Personagem criarPersonagem(String nome, String poder, boolean podeVoar) {
        for (Vilao h : Principal.personagensViloes) {
            if (h.getNome().equals(nome)) {
                h.atualizar(poder, podeVoar);
                System.out.println("Já adicionado!");
                return null;
            }
        }
        Vilao i = new Vilao(nome, poder, podeVoar);
        Principal.personagensViloes.add(i);
        return i;
    }

    @Override
    public void criarPersonagem(Personagem p) {
        boolean podeVoar = p.getVoar() == "" ? false : true;
        criarPersonagem(p.getNome(), p.getPoder(), podeVoar);
    }
}
