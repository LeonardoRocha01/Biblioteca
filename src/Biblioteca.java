package PACKAGE_NAME;

import java.time.LocalDate;
import java.util.*;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void  adicionarAutor(Autor autor){
        autores.add(autor);
    }
    public void  adicionarLivro(Livro livro){
        livros.add(livro);
    }
    public List<Livro> listarLivrosDisponiveis(){
    List<Livro> disponiveis = new ArrayList<>();
    for(Livro livro : livros){
        if(livro.isDisponivel()){
            disponiveis.add(livro);
        }
    }
    return disponiveis;
    }
    public Optional<Livro> buscarLivroPorId(int id){
        return livros.stream().filter(livro -> livro.getId() == id).findFirst();
    }
    public Emprestimo emprestimoComDataPrevista(Livro livro, String nomeCliente,LocalDate dataPrevista){
        livro.setDisponivel(false);
        Emprestimo emprestimo = new Emprestimo(livro, nomeCliente, dataPrevista);
        emprestimos.add(emprestimo);
        return emprestimo;
    }
    public List<Emprestimo> listarEmprestimosAtivos(){
        List<Emprestimo> ativos = new ArrayList<>();
        for(Emprestimo emprestimo : emprestimos){
            if (emprestimo.getDataDevolucao() == null) ativos.add(emprestimo);
        }
        return ativos;
    }
    public Optional<Emprestimo> buscarEmprestimoPorId(int id){
        return emprestimos.stream().filter(emprestimo -> emprestimo.getId() == id).findFirst();
    }
    public void venderLivros(Livro livro){
        livro.setDisponivel(false);
        Iterator<Livro> iterator = livros.iterator();
        while (iterator.hasNext()){
            Livro livro1 = iterator.next();
            if (livro.getId() == livro.getId()){
                iterator.remove();
                break;
            }
        }
    }

    public void dados(){
        Autor a1 = new Autor("Machado de Assis", LocalDate.of(1839, 6, 21));
        Autor a2 = new Autor("Clarice Lispector", LocalDate.of(1920, 12, 10));
        Autor a3 = new Autor("George Orwell", LocalDate.of(1903, 6, 25));
        Autor a4 = new Autor("J. K. Rowling", LocalDate.of(1965, 7, 31));
        Autor a5 = new Autor("Yuval Noah Harari", LocalDate.of(1976, 2, 24));
        Autor a6 = new Autor("Stephen King", LocalDate.of(1947, 9, 21));

        adicionarAutor(a1);
        adicionarAutor(a2);
        adicionarAutor(a3);
        adicionarAutor(a4);
        adicionarAutor(a5);
        adicionarAutor(a6);

        adicionarLivro(new Livro("Dom Casmurro", a1, 39.90));
        adicionarLivro(new Livro("Memórias Póstumas de Brás Cubas", a1, 34.50));
        adicionarLivro(new Livro("A Hora da Estrela", a2, 29.90));
        adicionarLivro(new Livro("1984", a3, 44.90));
        adicionarLivro(new Livro("Harry Potter e a Pedra Filosofal", a4, 59.90));
        adicionarLivro(new Livro("Sapiens: Uma Breve História da Humanidade", a5, 69.90));
        adicionarLivro(new Livro("It: A Coisa", a6, 54.00));
        adicionarLivro(new Livro("O Cortiço", new Autor("Aluísio Azevedo", LocalDate.of(1857, 4, 14)), 27.00));
        adicionarLivro(new Livro("O Pequeno Príncipe", new Autor("Antoine de Saint-Exupéry", LocalDate.of(1900, 6, 29)), 24.90));
        adicionarLivro(new Livro("Crime e Castigo", new Autor("Fiódor Dostoiévski", LocalDate.of(1821, 11, 11)), 49.90));
        adicionarLivro(new Livro("Orgulho e Preconceito", new Autor("Jane Austen", LocalDate.of(1775, 12, 16)), 39.00));
        adicionarLivro(new Livro("O Senhor dos Anéis: A Sociedade do Anel", new Autor("J. R. R. Tolkien", LocalDate.of(1892, 1, 3)), 89.90));
    }
}



