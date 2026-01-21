public class GuardiaoDeFerro extends Combatente {
    private int vigor;
    protected int tempoProtecaoRestante = 0;

    public GuardiaoDeFerro(String nome, int XP, int PV, int vigor) {
        super(nome, XP, 200);
        this.vigor = 50;
    }

    @Override
    public void atacar(Combatente alvo) {
        int danoFisico = 30; 
        alvo.receberDano(danoFisico);
    }

    @Override
    public void receberDano(int dano) {
        if (this.tempoProtecaoRestante > 0) {
            System.out.println(this.nome + " está invulnerável! Dano anulado.");
            return; 
        }
        if (this.vigor >= 20 && tentarBloquear()) {
            this.vigor -= 20;
            System.out.println(this.nome + " bloqueou o ataque!");
        } else {
            super.receberDano(dano);
        }
    }
    public void recuperaVigor(){
        // Se a proteção estiver ativa, diminui o tempo a cada chamada
        if (this.tempoProtecaoRestante > 0) {
            this.tempoProtecaoRestante = this.tempoProtecaoRestante - 1;
        }

        // Aumenta o vigor
        this.vigor += 10;

        if (this.vigor > 100) {
        this.vigor = 100;
        }
        System.out.println(this.nome + " recuperou vigor. Atual: " + this.vigor);

        // REGRA: Quando chegar a 100, anula danos por 2 segundos
        if (this.vigor >= 100) {
            this.vigor = 0; // Reseta o vigor ao usar o especial
            this.tempoProtecaoRestante = 2; // Define o tempo de invulnerabilidade
            System.out.println("!!! BLOQUEIO TOTAL ATIVADO POR 2 UNIDADES DE TEMPO !!!");
        }
    }
    private boolean tentarBloquear() {
        return Math.random() > 0.5;
    }
}
