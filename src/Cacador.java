import java.util.Random;

public class Cacador extends Combatente {
    private final Random random;
    private int contadorGolpe = 0;

    public Cacador(String nome, int XP) {
        super(nome, XP, 50);
        this.random = new Random();
    }
    public int getContadorGolpe(){
        return this.contadorGolpe;
    }
    @Override
    public String atacar(Combatente alvo) {
        int danoBase = this.XP * 3; // Exemplo de cálculo de dano
        // Verifica se houve acerto crítico (ex: 20% de chance)
        if (random.nextDouble() < 0.20 && this.contadorGolpe >= 3) {
            int danoCritico = danoBase * 2; // O impacto é dobrado 
            alvo.receberDano(danoCritico);
            this.contadorGolpe = 0;
            return "🎯 CRÍTICO! " + this.nome + " desferiu um acerto massivo em " + alvo.getNome() + " !!!\n";
        } else {
            alvo.receberDano(danoBase);
            this.contadorGolpe +=1;
            return "🏹 " + this.nome + " disparou contra " + alvo.getNome() + ".\n";
        }
    }

}
