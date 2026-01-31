import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    //As duas equipes
    private List<Combatente> equipeA;
    private List<Combatente> equipeB;
    
    //Escolhe alvo aleatório 
    private Random geradorAleatorio;

    // Construtor: Inicializa as listas e o random
    public Arena() {
        this.equipeA = new ArrayList<>();
        this.equipeB = new ArrayList<>();
        this.geradorAleatorio = new Random();
    }
    // Método para adicionar combatentes
    public void adicionarCombatente(Combatente c, String lado) {
        if (lado.equalsIgnoreCase("A")) equipeA.add(c);
        else if (lado.equalsIgnoreCase("B")) equipeB.add(c);
    }
    public List<Combatente> getEquipeA() {
        return this.equipeA;
    }
    public List<Combatente> getEquipeB() {
        return this.equipeB;
    }
    // Lógica interna de uma rodada
    public String executarRodada() {
        StringBuilder relatorio = new StringBuilder();
    // Ataque Equipe A
        for (Combatente atacante : equipeA) {
            if (atacante.estaVivo()) {
                Combatente alvo = selecionarAlvoAleatorio(equipeB);
                if (alvo != null) {
                    relatorio.append(atacante.atacar(alvo));
                }
            }
        }
    // Ataque Equipe B (mesma lógica...)
        for (Combatente atacante : equipeB) { // Percorre a equipe B
            if (atacante.estaVivo()) {
                Combatente alvo = selecionarAlvoAleatorio(equipeA); // Alvo deve ser da A
                if (alvo != null) {
                    relatorio.append(atacante.atacar(alvo)); // Agora a B ataca a A
                }
            }
        }
    // Fim da rodada: Recuperação de Vigor
        for (Combatente c : equipeA) {
            if (c instanceof GuardiaoDeFerro && c.estaVivo()) relatorio.append(((GuardiaoDeFerro) c).atualizarVigor());
        }
        for (Combatente c : equipeB) {
            if (c instanceof GuardiaoDeFerro && c.estaVivo()) relatorio.append(((GuardiaoDeFerro) c).atualizarVigor());
        }
        return relatorio.toString();
    }
    // Seleciona um alvo vivo aleatoriamente na equipe inimiga
    private Combatente selecionarAlvoAleatorio(List<Combatente> equipeInimiga) {
        // Filtra apenas os vivos
        List<Combatente> vivos = new ArrayList<>();
        for (Combatente c : equipeInimiga) {
            if (c.estaVivo()) {
                vivos.add(c);
            }
        }
        // Se não tem ninguém vivo, retorna nulo (batalha acabou)
        if (vivos.isEmpty()) {
            return null;
        }
        // Sorteia um índice
        int indiceSorteado = geradorAleatorio.nextInt(vivos.size());
        return vivos.get(indiceSorteado);
    }
    public boolean equipeViva(List<Combatente> equipe) {
        for (Combatente c : equipe) {
            if (c.estaVivo()) return true;
        }
        return false;
    }
}

