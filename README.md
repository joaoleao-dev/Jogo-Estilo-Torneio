# 🏆 Grande Torneio: O Conflito Eterno

Um simulador de combate em turnos desenvolvido em Java, utilizando a biblioteca **Swing** para a interface gráfica. O projeto aplica conceitos fundamentais de Programação Orientada a Objetos para gerenciar diferentes classes de combatentes e mecânicas de batalha.

## 👥 Desenvolvedores
* **João Gustavo Bittencourt Costa**
* **João Vitor Vital Leão**

---

## 🛠️ Tecnologias e Conceitos de POO
Este projeto foi desenvolvido como parte de estudos em Ciência da Computação, aplicando:
- **Herança e Polimorfismo:** Todos os combatentes herdam da classe abstrata `Combatente`, mas cada um possui sua própria implementação do método `atacar()`.
- **Encapsulamento:** Atributos protegidos e métodos de acesso (getters) para garantir a integridade dos dados.
- **Interface Gráfica:** Uso de `JFrame`, `JPanel` e `Timer` para uma experiência visual e em tempo real.

---

## 🏗️ Como Compilar

```bash
javac *.java
```

---

## 🎮 Como Executar o Jogo

Após a compilação, para iniciar a interface gráfica do torneio e ver os combates acontecerem, execute:

```bash
java Main.java
```

---

## 💡 Prompt com IA

* **Arquitetura e Lógica de Combate**:
  "Como posso implementar um sistema de combate em turnos em Java onde diferentes classes (Arcanista, Caçador e Guardião) possuem comportamentos únicos ao atacar e receber dano, garantindo que eu use polimorfismo para facilitar a expansão futura do jogo?"

* **Interface Gráfica e Gerenciamento de Estado**:
  "Estou desenvolvendo uma interface Swing para o meu torneio. Como posso usar um javax.swing.Timer para atualizar o log de combate e as barras de vida dos personagens em tempo real, sem travar a interface principal (EDT) durante as rodadas?"

* **Automação de Testes Unitários**:
  "Crie uma classe de teste JUnit 5 para validar a lógica de invulnerabilidade do GuardiaoDeFerro. O teste deve garantir que, quando o vigor atingir 100, o personagem não receba dano pelos próximos 2 ataques, resetando o vigor para 0 após o uso."

* **Deploy e Padronização de Repositório**:
  "Preciso organizar meu projeto para o GitHub. Como estruturo um comando de compilação via terminal que separe os arquivos .class em uma pasta bin e inclua o JAR do JUnit no classpath, para que qualquer pessoa consiga rodar o projeto e os testes sem usar uma IDE?"

