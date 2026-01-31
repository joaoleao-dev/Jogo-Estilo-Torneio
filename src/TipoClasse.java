import java.util.function.BiFunction;

public enum TipoClasse {
    // Cada constante define como sua respectiva classe deve ser instanciada
    GUARDIAO("Guardião de Ferro", (nome, xp) -> new GuardiaoDeFerro(nome, xp, 50)),
    ARCANISTA("Arcanista", (nome, xp) -> new Arcanista(nome, xp)),
    CACADOR("Caçador", (nome, xp) -> new Cacador(nome, xp));

    private final String descricao;
    private final BiFunction<String, Integer, Combatente> criador;

    // Construtor do Enum recebe a descrição e a função de criação (Lambda)
    TipoClasse(String descricao, BiFunction<String, Integer, Combatente> criador) {
        this.descricao = descricao;
        this.criador = criador;
    }
    // Método que a Interface chama para criar o objeto real
    public Combatente fabricar(String nome, int xp) {
        return criador.apply(nome, xp);
    }
    @Override
    public String toString() {
        return descricao;
    }

}
