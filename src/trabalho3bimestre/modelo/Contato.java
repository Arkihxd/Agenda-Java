/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.modelo;

/**
 *
 * @author aluno
 */
public class Contato {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private Integer ano;
    private boolean atividade;
    
    public Contato(String nome, String email, String telefone, int ano){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.ano=ano;
        this.atividade=true;
    }
    
    public Contato(int id, String nome, String email, String telefone, boolean atividade, int ano){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.atividade=atividade;
        this.ano=ano;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String toString(){
        return getNome();
    }

    /**
     * @return the ano
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Integer ano) {
        this.ano = ano;
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
