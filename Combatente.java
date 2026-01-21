public abstract class Combatente{
    String nome;
    int XP;
    int PV;
    
    public Combatente (String nome, int XP, int PV) {
        this.nome = nome;
        this.XP = XP;
        this.PV = PV;
    }
    
    public abstract void atacar(Combatente alvo);

    public boolean estaVivo(){
        return this.PV > 0;
    }
    public void receberDano(int dano) {
        this.PV -= dano;
        if (this.PV < 0) {
            this.PV = 0;
        }
    }
    public String getNome(){
        return nome;
    }
    public int PV(){
        return PV;
    }

}