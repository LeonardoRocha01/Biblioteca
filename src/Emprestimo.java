package PACKAGE_NAME;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
private static int contador = 1;
private final int id;
private Livro livro;
private String nomeCliente;
private LocalDate dataDeEmprestimo;
private LocalDate dataDevolucao;
private LocalDate dataPrevista;
protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

public Emprestimo(Livro livro, String nomeCliente, LocalDate dataPrevista) {
    this.id = contador++;
    this.livro = livro;
    this.nomeCliente = nomeCliente;
    this.dataDeEmprestimo = LocalDate.now();
    this.dataDevolucao = null;
    this.dataPrevista = dataPrevista;
}
public int getId() {
    return id;
}

public String getDataPrevista() {
    return dataPrevista.format(formatter);
}
public Livro getLivro(){
    return livro;
}
public String getNomeCliente(){
    return nomeCliente;
}
public LocalDate getDataDeEmprestimo(){
    return dataDeEmprestimo;
}
public LocalDate getDataDevolucao(){
    return dataDevolucao;
}
public void setDataDevolucao(LocalDate dataDevolucao){
    this.dataDevolucao = dataDevolucao;
}
}
