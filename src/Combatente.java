public abstract class Combatente{
    protected String nome;
    protected int XP;
    protected int PV;
    protected int pvMaximo;
    
    public Combatente (String nome, int XP, int pvMaximo) {
        this.nome = nome;
        this.XP = XP;
        this.pvMaximo = pvMaximo;
        this.PV = pvMaximo;
    }
    
    public abstract String atacar(Combatente alvo);

    public boolean estaVivo(){
        return this.PV > 0;
    }
    public String receberDano(int dano) {
        if (dano < 0) return"";
        this.PV -= dano;
        if (this.PV < 0) this.PV = 0;
            return"";
    }
    public String getNome(){
        return this.nome;
    }
    public int getPV(){
        return this.PV;
    }
    public int getPvMaximo() {
        return this.pvMaximo;
    }

}
