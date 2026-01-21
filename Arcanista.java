public class Arcanista extends Combatente {

    public Arcanista(String nome, int XP, int PV) {
        super(nome, XP, PV);
        
    }

    @Override
    public void atacar(Combatente alvo) {
        int danoMagico = this.XP * 2; // Regra específica do Arcanista
        System.out.println(this.nome + " lança um feitiço em " + alvo.getNome());
        alvo.receberDano(danoMagico);
    }

}
