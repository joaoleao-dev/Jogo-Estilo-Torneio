public class Arcanista extends Combatente {
    private int magia;

    public Arcanista(String nome, int XP) {
        super(nome, XP, 100);
        this.magia = 100;
    }
    public int getMagia(){
        return this.magia;
    }
    @Override
    public String atacar(Combatente alvo) {
        int danoMagico = 100;
        if(magia >= 20){ 
            this.magia -= 20;
            alvo.receberDano(danoMagico);
            return "✨ " + this.nome + " lança um feitiço em " + alvo.getNome() + "!\n";
        } else {
            alvo.receberDano(20); 
            this.magia += 20;
            if (this.magia > 100) {
                this.magia = 100; // GARANTIA: Nunca ultrapassa 100
            }
        }
        if (this.magia < 0) {
            this.magia = 0; // GARANTIA: Nunca fica menor que 0
        }
        return "🌀 " + this.nome + " está sem magia! Realizou um ataque fraco e meditou.\n";
    }
}

