public class GuardiaoDeFerro extends Combatente {
    private int vigor;
    private int tempoProtecaoRestante = 0;

    public GuardiaoDeFerro(String nome, int XP, int vigor) {
        super(nome, XP, 200);
        this.vigor = vigor;
    }
    @Override
    public String atacar(Combatente alvo) {
        int danoFisico = 30; 
        alvo.receberDano(danoFisico);
        return "🛡️ " + this.nome + " golpeou " + alvo.getNome() + " com seu escudo.\n";
    }
    @Override
    public String receberDano(int dano) {
        if (this.tempoProtecaoRestante > 0) { // 1. Checa Invulnerabilidade
            return "🛡️ " + this.nome + " está invulnerável! Dano de " + dano + " anulado.\n";
        }
        if (this.vigor >= 20 && Math.random() > 0.5) { // 2. Tenta Bloqueio (Lógica + Log)
            this.vigor -= 20;
            return "🛡️ " + this.nome + " bloqueou o ataque!\n";
        }
        super.receberDano(dano); // 3. Se não bloqueou, chama o dano padrão da classe mãe
        return "";
    }
    public void recuperaVigor(){
        // Se a proteção estiver ativa, diminui o tempo a cada chamada
        if (this.tempoProtecaoRestante > 0) {
            this.tempoProtecaoRestante = this.tempoProtecaoRestante - 1;
        }
        this.vigor += 10; // Aumenta o vigor

        if (this.vigor > 100) {
        this.vigor = 100;
        }
        // REGRA: Quando chegar a 100, anula danos por 2 segundos
        if (this.vigor >= 100) {
            this.vigor = 0; // Reseta o vigor ao usar o especial
            this.tempoProtecaoRestante = 2; // Define o tempo de invulnerabilidade
        }
    }
    public String atualizarVigor() {
        this.recuperaVigor();
        if (this.getTempoProtecaoRestante() == 2) {
            return "🔥 ESPECIAL: " + this.nome + " ATIVOU INVULNERABILIDADE TOTAL!\n";
        }
        return this.nome + " recuperou vigor (Atual: " + this.vigor() + ")\n";
    }
    public int vigor(){
        return this.vigor;
    }
    public int getTempoProtecaoRestante(){
        return this.tempoProtecaoRestante;
    }
}
