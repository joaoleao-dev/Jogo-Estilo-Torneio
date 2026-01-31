import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Testes de Regras do RPG")
public class BatalhaTeste {
    
    private Arcanista mago;
    private Arcanista alvoTeste;
    private GuardiaoDeFerro guardiao;

    @BeforeEach
    public void setup() {
        // PREPARAÇÃO: Roda antes de cada teste
        mago = new Arcanista("Merlin", 10);      
        alvoTeste = new Arcanista("Boneco de Treino", 10); 
        
        // Guardião criado com 90 de vigor para testar a ativação do especial (que ocorre no 100)
        guardiao = new GuardiaoDeFerro("Goliath", 10, 90); 
    }

    @Test
    @DisplayName("Regra: Arcanista causa 100 de dano se tiver mana")
    public void testAtaqueComMana() {
        mago.atacar(alvoTeste);
        
        // Alvo tinha 100 PV. Tomou 100 de dano. Deve estar com 0.
        assertEquals(0, alvoTeste.getPV(), "O alvo deveria morrer com 1 ataque mágico");
        // Mago gasta 20 de mana. Tinha 100. Deve ter 80.
        assertEquals(80, mago.getMagia(), "O mago deveria gastar 20 de mana");
    }

    @Test
    @DisplayName("Regra: Sem mana, o ataque é fraco (20 dano)")
    public void testAtaqueSemMana() {
        // Esvazia a mana do mago (5 ataques * 20 mana = 100 gasto)
        for (int i = 0; i < 5; i++) {
            mago.atacar(new Arcanista("Clone", 10)); 
        }
        
        // Agora ataca o alvo real sem mana
        mago.atacar(alvoTeste);

        // Dano físico é 20. Alvo tinha 100. Deve sobrar 80.
        assertEquals(80, alvoTeste.getPV(), "Sem mana, o dano deve ser reduzido para 20");
    }

    @Test
    @DisplayName("Regra: Guardião ativa invulnerabilidade ao encher Vigor")
    public void testEspecialGuardiao() {
        // O guardião começou com 90 vigor. 
        // O método atualizarVigor() soma +10. Vai para 100.
        // Ao chegar em 100, deve ativar proteção.
        guardiao.atualizarVigor(); 

        assertEquals(2, guardiao.getTempoProtecaoRestante(), "Deveria ativar 2 turnos de proteção");
        assertEquals(0, guardiao.vigor(), "Vigor deve zerar após usar o especial");
    }
    
    @Test
    @DisplayName("Regra: Caçador causa dano base = XP * 3")
    public void testDanoCacador() {
        Cacador legolas = new Cacador("Legolas", 20); // XP 20
        Combatente orc = new GuardiaoDeFerro("Orc", 10, 0); // Alvo com 200 vida
        
        // Dano esperado: 20 XP * 3 = 60 dano.
        legolas.atacar(orc);
        
        // Vida do Orc: 200 - 60 = 140.
        assertEquals(140, orc.getPV(), "O dano deve respeitar a fórmula XP * 3");
    }
}


