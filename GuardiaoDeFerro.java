public class GuardiaoDeFerro extends Combatente {

    public GuardiaoDeFerro(String nome, int XP, int PV) {
        super(nome, XP, PV);

    }

    @Override
    public void atacar(Combatente alvo) {
        int danoFisico = this.XP; 
        alvo.receberDano(danoFisico);
    }

    @Override
    public void receberDano(int dano) {
        if (tentarBloquear()) {
            System.out.println(this.nome + " bloqueou o ataque!");
        } else {
            super.receberDano(dano);
        }
    }
    
    private boolean tentarBloquear() {
        return Math.random() > 0.7; 
    }
}
