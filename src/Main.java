package com.mycompany.jogo.rpg.poo; 
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Arena arena = new Arena(); 
        // Inicia a Interface Gráfica de forma segura
        SwingUtilities.invokeLater(() -> {
            InterfaceTorneio tela = new InterfaceTorneio(arena);
            tela.setVisible(true);
            tela.setLocationRelativeTo(null); // Centraliza na tela
        });
    }
}