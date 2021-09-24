/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public interface ContatoDAO {
	public void insere(Contato c) throws SQLException;
	public Contato busca(int id) throws SQLException;
	public void altera(Contato c) throws SQLException;
	public void deleta(Contato c) throws SQLException;
	public ArrayList<Contato> lista() throws SQLException;
}
