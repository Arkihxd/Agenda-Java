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
public interface CompromissoDAO {
	public void insere(Compromisso c) throws SQLException;
	public Compromisso busca(int id) throws SQLException;
	public void altera(Compromisso c) throws SQLException;
	public void deleta(Compromisso c) throws SQLException;
	public ArrayList<Compromisso> lista() throws SQLException;
}
