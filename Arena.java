package com.mycompany.jogo.rpg.poo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    // Atributos: As duas equipes opostas [cite: 47]
    private List<Combatente> equipeA;
    private List<Combatente> equipeB;
    
    // Utilitário para escolher alvos aleatórios [cite: 52]
    private Random geradorAleatorio;

    // Construtor: Inicializa as listas e o random
    public Arena() {
        this.equipeA = new ArrayList<>();
        this.equipeB = new ArrayList<>();
        this.geradorAleatorio = new Random();
    }

    // Método para adicionar combatentes (setup inicial)
    // Permite adicionar quantos quiser (1v1 ou 100v100) [cite: 48]
    public void adicionarCombatente(Combatente c, String lado) {
        if (lado.equalsIgnoreCase("A")) {
            equipeA.add(c);
        } else if (lado.equalsIgnoreCase("B")) {
            equipeB.add(c);
        } else {
            System.out.println("Lado inválido! Escolha 'A' ou 'B'.");
        }
    }

    // O "Motor" do jogo: Loop principal que roda até alguém ganhar [cite: 57]
    public void iniciarBatalha() {
        int rodada = 1;
        boolean batalhaAtiva = true;

        System.out.println("=== BATALHA INICIADA ===");

        while (batalhaAtiva) {
            System.out.println("\n--- Rodada " + rodada + " ---");
            
            // Lógica da rodada (turno de cada um)
            executarRodada();

            // Verifica se alguém venceu após a rodada
            if (verificarVitoria()) {
                batalhaAtiva = false;
            } else {
                rodada++;
            }
        }
    }

    // Lógica interna de uma rodada
    private void executarRodada() {
        // Todo mundo ataca! (Aqui você precisará iterar sobre as duas equipes)
        // Dica: Crie uma lista unificada ou ordene por iniciativa se quiser ser criativo
        
        // Exemplo de lógica para a Equipe A atacar a B:
        for (Combatente atacante : equipeA) {
            if (atacante.estaVivo()) { // [cite: 50]
                Combatente alvo = selecionarAlvoAleatorio(equipeB); // [cite: 52]
                if (alvo != null) {
                    atacante.atacar(alvo); // Polimorfismo acontece aqui!
                }
            }
        }

        // Repita a lógica para a Equipe B atacar a A...
        for (Combatente atacante : equipeB) {
            if (atacante.estaVivo()) {
                 Combatente alvo = selecionarAlvoAleatorio(equipeA);
                 if (alvo != null) {
                     atacante.atacar(alvo);
                 }
            }
        }
    }

    // Seleciona um alvo vivo aleatoriamente na equipe inimiga [cite: 52]
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

    // Verifica se alguma equipe foi totalmente derrotada [cite: 58]
    private boolean verificarVitoria() {
        boolean aVivo = false;
        boolean bVivo = false;

        // Verifica se ainda tem alguém vivo na A
        for (Combatente c : equipeA) {
            if (c.estaVivo()) aVivo = true;
        }

        // Verifica se ainda tem alguém vivo na B
        for (Combatente c : equipeB) {
            if (c.estaVivo()) bVivo = true;
        }

        if (!aVivo) {
            System.out.println("A Equipe B venceu!");
            return true; // Fim de jogo
        } else if (!bVivo) {
            System.out.println("A Equipe A venceu!");
            return true; // Fim de jogo
        }

        return false; // Jogo continua
    }
}

