package PACKAGE_NAME;

import java.time.LocalDate;
import java.util.Date;

public class Livro {
   private static int contador = 1;
   private final int id;
   private String titulo;
   private Autor autor;
   private boolean disponivel;
   private LocalDate dataCadastro;
   private LocalDate dataAtualização;
   private Double preco;

   public Livro(String titulo, Autor autor, double preco){
       this.titulo = titulo;
       this.autor = autor;
       this.disponivel = true;
       this.dataCadastro = LocalDate.now();
       this.dataAtualização = LocalDate.now();
       this.preco = preco;
       this.id = contador++;
   }
   public double getPreco(){
       return preco;
   }

    public int getId() {
       return id;
   }
    public String getTitulo() {
       return titulo;
   }
    public Autor getAutor() {
       return autor;
   }
    public boolean isDisponivel() {
       return disponivel;
   }
    public LocalDate getDataCadastro() {
       return dataCadastro;
   }
    public LocalDate getDataAtualização() {
       return dataAtualização;
   }
   public void setAutor(Autor autor) {
       this.autor = autor;
       this.dataAtualização = LocalDate.now();
   }
   public void setDisponivel(boolean disponivel) {
       this.disponivel = disponivel;
       this.dataAtualização = LocalDate.now();
   }
   public void setTitulo(String titulo) {
       this.titulo = titulo;
       this.dataAtualização= LocalDate.now();
   }
   public void setPreco(Double preco) {
       this.preco = preco;
       this.dataAtualização = LocalDate.now();
   }
}
