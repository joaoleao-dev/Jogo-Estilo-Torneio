package com.mycompany.jogo.rpg.poo;

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
        if (lado.equalsIgnoreCase("A")) {
            equipeA.add(c);
        } else if (lado.equalsIgnoreCase("B")) {
            equipeB.add(c);
        } else {
            System.out.println("Lado inválido! Escolha 'A' ou 'B'.");
        }
    }

    //Loop principal que roda até alguém ganhar
    public void iniciarBatalha() {
        int rodada = 1;
        boolean batalhaAtiva = true;

        System.out.println("=== BATALHA INICIADA ===");

        while (batalhaAtiva) {
            System.out.println("\n--- Rodada " + rodada + " ---");
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
        

        for (Combatente atacante : equipeA) {
            if (atacante.estaVivo()) { 
                Combatente alvo = selecionarAlvoAleatorio(equipeB); 
                if (alvo != null) {
                    atacante.atacar(alvo); 
                }
            }
        }

        for (Combatente atacante : equipeB) {
            if (atacante.estaVivo()) {
                 Combatente alvo = selecionarAlvoAleatorio(equipeA);
                 if (alvo != null) {
                     atacante.atacar(alvo);
                 }
            }
        }
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

    // Verifica se alguma equipe foi totalmente derrotada
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


