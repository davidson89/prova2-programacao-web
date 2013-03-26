package br.edu.ufabc.progWeb.prova2.dao;

import java.util.List;

public interface GenericBaseDAO<T> {
	
	public void salve(T persistivel);
	
	public void delete(T persistivel);
	
	public T findByPk(Long id);
	
	public List<T> findAll();

}
