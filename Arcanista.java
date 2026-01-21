public class Arcanista extends Combatente {
    private int magia;

    public Arcanista(String nome, int XP, int PV) {
        super(nome, XP, 100);
        this.magia = 100;
    }

    @Override
    public void atacar(Combatente alvo) {
        int danoMagico = 100;
        if(magia >= 20){ 
            System.out.println(this.nome + " lança um feitiço em " + alvo.getNome());
            this.magia -= 20;
            alvo.receberDano(danoMagico);
        } else {
            System.out.println(this.nome + " está sem magia! Realizou um ataque fraco e meditou.");
            alvo.receberDano(20); 
            this.magia += 20;
            
            // GARANTIA: Nunca ultrapassa 100
            if (this.magia > 100) {
                this.magia = 100;
            }
        }
        // GARANTIA: Nunca fica menor que 0
        if (this.magia < 0) {
            this.magia = 0;

        }

    }
}
