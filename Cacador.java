import java.util.Random;

public class Cacador extends Combatente {
    private Random random;

    public Cacador(String nome, int XP) {
        super(nome, XP, 50);
        this.random = new Random();
    }

    @Override
    public void atacar(Combatente alvo) {
        int danoBase = this.XP * 3; // Exemplo de cálculo de dano
        
        // Verifica se houve acerto crítico (ex: 20% de chance)
        if (random.nextDouble() < 0.20) {
            int danoCritico = danoBase * 2; // O impacto é dobrado 
            System.out.println(this.nome + " desferiu um ACERTO CRÍTICO em " + alvo.getNome() + "!");
            alvo.receberDano(danoCritico);
        } else {
            System.out.println(this.nome + " disparou contra " + alvo.getNome());
            alvo.receberDano(danoBase);
        }
    }

}

