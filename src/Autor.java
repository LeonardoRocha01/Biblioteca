package PACKAGE_NAME;

  import java.time.LocalDate;

public class Autor {
    private static int contador = 1;
    private final int id;
    private String nomeDoAutor;
    private LocalDate dataNascimento;

    public Autor(String nomeDoAutor, LocalDate dataNascimento){
        this.nomeDoAutor = nomeDoAutor;
        this.dataNascimento = dataNascimento;
        this.id = contador++;
    }
    public String getNomeDoAutor() {
    return nomeDoAutor;
    }
    public int getId(){
        return id;
    }
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

}
