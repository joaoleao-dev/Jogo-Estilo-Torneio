package com.mycompany.jogo.rpg.poo;

import javax.swing.*;
import java.awt.*;

public class InterfaceTorneio extends JFrame {
    private Arena arena;
    private JTextArea logCombate;
    private JPanel painelEquipeA, painelEquipeB;
    private JButton btnIniciar, btnReiniciar, btnCriar;
    private Timer timer;

    public InterfaceTorneio(Arena arena) {
        this.arena = arena;
        configurarJanela();
        inicializarComponentes();
    }
    
    private void configurarJanela() {
        setTitle("Grande Torneio: O Conflito Eterno");
        setSize(1000, 700); // Aumentei um pouco a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }
    
    private void inicializarComponentes() {
        // Área de Log (Centro)
        logCombate = new JTextArea();
        logCombate.setEditable(false);
        logCombate.setBackground(Color.BLACK);
        logCombate.setForeground(Color.GREEN);
        logCombate.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(logCombate), BorderLayout.CENTER);

        // Painéis das Equipes (Laterais)
        painelEquipeA = new JPanel(new GridLayout(0, 1));
        painelEquipeA.setBorder(BorderFactory.createTitledBorder("EQUIPE A"));
        
        painelEquipeB = new JPanel(new GridLayout(0, 1));
        painelEquipeB.setBorder(BorderFactory.createTitledBorder("EQUIPE B"));
        
        // Coloquei scroll nos painéis laterais caso tenha muitos guerreiros
        JScrollPane scrollA = new JScrollPane(painelEquipeA);
        scrollA.setPreferredSize(new Dimension(200, 0));
        add(scrollA, BorderLayout.WEST);
        
        JScrollPane scrollB = new JScrollPane(painelEquipeB);
        scrollB.setPreferredSize(new Dimension(200, 0));
        add(scrollB, BorderLayout.EAST);

        // Botões (Rodapé)
        JPanel painelBotoes = new JPanel();
        btnCriar = new JButton("Nova Batalha"); 
        btnIniciar = new JButton("Iniciar Combate");
        btnReiniciar = new JButton("Limpar Arena");
        
        btnIniciar.setEnabled(false); 
        btnReiniciar.setEnabled(false);

        btnCriar.addActionListener(e -> menuCriacao());
        btnIniciar.addActionListener(e -> iniciarCombateGrafico());
        btnReiniciar.addActionListener(e -> reiniciarJogo());

        painelBotoes.add(btnCriar);
        painelBotoes.add(btnIniciar);
        painelBotoes.add(btnReiniciar);
        add(painelBotoes, BorderLayout.SOUTH);

        renderizarEquipes();
    }
    
    // ---------------------------------------------------------
    // MENU DE CRIAÇÃO (PvE e PvP)
    // ---------------------------------------------------------
    private void menuCriacao() {
        // 1. Pergunta o Modo de Jogo
        String[] opcoes = {"Contra o Computador (PvE)", "Dois Jogadores (PvP)"};
        int modo = JOptionPane.showOptionDialog(this, "Escolha o modo de batalha:", "Configuração",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (modo == -1) return; // Fechou a janela

        // 2. Pergunta a quantidade
        String input = JOptionPane.showInputDialog(this, 
                "Quantos guerreiros para CADA equipe?", "Tamanho dos Exércitos", JOptionPane.QUESTION_MESSAGE);
        
        if (input == null || input.trim().isEmpty()) return;
        int qtd;
        try {
            qtd = Integer.parseInt(input);
            if (qtd <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número válido!");
            return;
        }

        // 3. Configuração das Equipes
        if (modo == 0) {
            // MODO PvE: Jogador cria Time A, Computador cria Time B
            configurarEquipeManual("A", "Jogador (Heróis)", qtd);
            popularInimigosAleatorios(qtd);
        } else {
            // MODO PvP: Jogador 1 cria Time A, Jogador 2 cria Time B
            JOptionPane.showMessageDialog(this, "Vez do Jogador 1 montar a Equipe A!");
            configurarEquipeManual("A", "Jogador 1", qtd);
            
            JOptionPane.showMessageDialog(this, "Vez do Jogador 2 montar a Equipe B!");
            configurarEquipeManual("B", "Jogador 2", qtd);
        }

        btnCriar.setEnabled(false);
        btnIniciar.setEnabled(true);
        renderizarEquipes();
        logCombate.append(">>> Batalha configurada: " + qtd + " vs " + qtd + "!\n");
    }

    // Método genérico para criar qualquer equipe manualmente
    private void configurarEquipeManual(String lado, String nomeDono, int qtd) {
        // Se for muita gente, oferece modo automático
        if (qtd > 5) {
            int resp = JOptionPane.showConfirmDialog(this, 
                nomeDono + ", você quer gerar seus " + qtd + " guerreiros aleatoriamente para agilizar?",
                "Modo Rápido", JOptionPane.YES_NO_OPTION);
            
            if (resp == JOptionPane.YES_OPTION) {
                popularEquipeAleatoria(lado, qtd);
                return;
            }
        }

        // Criação manual um por um
        for (int i = 1; i <= qtd; i++) {
            String nome = JOptionPane.showInputDialog(this, 
                "[" + nomeDono + "] Nome do Guerreiro " + i + ":");
            if (nome == null || nome.trim().isEmpty()) nome = "Guerreiro " + i;

            TipoClasse[] classes = TipoClasse.values();
            TipoClasse classe = (TipoClasse) JOptionPane.showInputDialog(this, "Classe de " + nome + ":", 
                    "Recrutamento", JOptionPane.QUESTION_MESSAGE, null, classes, classes[0]);

            if (classe != null) {
                arena.adicionarCombatente(classe.fabricar(nome, 10), lado);
            }
        }
    }

    // Gera inimigos (Time B - Vilões)
    private void popularInimigosAleatorios(int quantidade) {
        TipoClasse[] classes = TipoClasse.values();
        java.util.Random rand = new java.util.Random();
        String[] nomesViloes = {"Orc", "Troll", "Goblin", "Esqueleto", "Dragão", "Necromante"};

        for (int i = 1; i <= quantidade; i++) {
            TipoClasse classe = classes[rand.nextInt(classes.length)];
            String nome = nomesViloes[rand.nextInt(nomesViloes.length)] + " " + i;
            arena.adicionarCombatente(classe.fabricar(nome, 10), "B");
        }
    }

    // Gera aliados ou Time B aleatório (para PvP rápido)
    private void popularEquipeAleatoria(String lado, int quantidade) {
        TipoClasse[] classes = TipoClasse.values();
        java.util.Random rand = new java.util.Random();
        String base = lado.equals("A") ? "Aliado" : "Oponente";

        for (int i = 1; i <= quantidade; i++) {
            TipoClasse classe = classes[rand.nextInt(classes.length)];
            arena.adicionarCombatente(classe.fabricar(base + " " + i, 10), lado);
        }
    }
    
    private void renderizarEquipes() {
        painelEquipeA.removeAll();
        for (Combatente c : arena.getEquipeA()) {
            JLabel lbl = new JLabel(c.getNome() + " [" + c.getClass().getSimpleName().substring(0, 3) + "] PV:" + c.getPV());
            lbl.setForeground(c.estaVivo() ? new Color(0, 100, 0) : Color.RED); // Verde escuro ou Vermelho
            painelEquipeA.add(lbl);
        }
        
        painelEquipeB.removeAll();
        for (Combatente c : arena.getEquipeB()) {
            JLabel lbl = new JLabel(c.getNome() + " [" + c.getClass().getSimpleName().substring(0, 3) + "] PV:" + c.getPV());
            lbl.setForeground(c.estaVivo() ? Color.BLUE : Color.RED);
            painelEquipeB.add(lbl);
        }
        
        painelEquipeA.revalidate(); painelEquipeA.repaint();
        painelEquipeB.revalidate(); painelEquipeB.repaint();
    }
    
    private void iniciarCombateGrafico() {
        btnIniciar.setEnabled(false);
        btnCriar.setEnabled(false);
        
        timer = new Timer(1500, e -> { // Velocidade da batalha (1.5 segundos)
            if (arena.equipeViva(arena.getEquipeA()) && arena.equipeViva(arena.getEquipeB())) {
                String resultado = arena.executarRodada(); 
                logCombate.append(resultado + "\n--------------------------------------------------\n");
                logCombate.setCaretPosition(logCombate.getDocument().getLength()); // Auto-scroll
                renderizarEquipes();
            } else {
                ((Timer)e.getSource()).stop();
                anunciarVencedor();
                btnReiniciar.setEnabled(true);
            }
        });
        timer.start();
    }
    
    private void anunciarVencedor() {
        String vencedor = arena.equipeViva(arena.getEquipeA()) ? "EQUIPE A" : "EQUIPE B";
        logCombate.append("\n🏆 VITORIA DA " + vencedor + " 🏆\n");
        JOptionPane.showMessageDialog(this, "Batalha encerrada! Vencedor: " + vencedor);
    }
    
    private void reiniciarJogo() {
        arena.getEquipeA().clear();
        arena.getEquipeB().clear();
        logCombate.setText("");
        btnReiniciar.setEnabled(false);
        btnCriar.setEnabled(true);
        renderizarEquipes();
    }
}