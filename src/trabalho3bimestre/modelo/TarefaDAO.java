/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public interface TarefaDAO {
	public void insere(Tarefa t) throws SQLException;
	public Tarefa busca(int id) throws SQLException;
	public void altera(Tarefa t) throws SQLException;
	public void deleta(Tarefa t) throws SQLException;
	public ArrayList<Tarefa> lista() throws SQLException;
}
