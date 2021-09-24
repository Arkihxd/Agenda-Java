package trabalho3bimestre.modelo;

import java.sql.Date;
import java.sql.Time;


public class Compromisso {
    private int id;
    private String nome;
    private Date data;
    private Time hora;
    private String descricao;
    private Contato mandante;
    private boolean realizacao;
    private boolean atividade;
    
    public Compromisso(String nome, Date data, Time hora, String descricao, Contato mandante){
        this.nome = nome;
        this.data = data;
        this.hora=hora;
        this.descricao = descricao;
        this.mandante = mandante;
        this.atividade=true;
        this.realizacao=false;
    }
    
    public Compromisso(int id, String nome, Date data, Time hora, String descricao, Contato mandante, boolean realizacao, boolean atividade){
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.hora=hora;
        this.descricao = descricao;
        this.mandante = mandante;
        this.atividade=atividade;
        this.realizacao=realizacao;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the mandante
     */
    public Contato getMandante() {
        return mandante;
    }

    /**
     * @param mandante the mandante to set
     */
    public void setMandante(Contato mandante) {
        this.mandante = mandante;
    }

    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * @return the realizacao
     */
    public boolean isRealizacao() {
        return realizacao;
    }

    /**
     * @param realizacao the realizacao to set
     */
    public void setRealizacao(boolean realizacao) {
        this.realizacao = realizacao;
    }

    /**
     * @return the atividade
     */
    public boolean isAtividade() {
        return atividade;
    }

    /**
     * @param atividade the atividade to set
     */
    public void setAtividade(boolean atividade) {
        this.atividade = atividade;
    }
    
}
