package PACKAGE_NAME;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.dados();

        Scanner scanner = new Scanner(System.in);
        System.out.println("BEM-VINDO A BIBLIOTECA");

        boolean executar = true;

        while (executar) {
            System.out.println("\nMenu: ");
            System.out.println("1 - Ver a lista de livros disponiveis");
            System.out.println("2 - Processar devolução");
            System.out.println("3 - Listar empréstimos ativos");
            System.out.println("4 - Sair");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    boolean volta = fluxoEmprestimo(biblioteca, scanner);
                    if (!volta) executar = false;
                    break;
                    case "2":
                        fluxoDevolucao(biblioteca, scanner);
                        break;
                        case "3":
                            listarEmprestimosAtivos(biblioteca);
                            break;
                            case "4":
                                executar = false;
                                break;
                                default:
                                    System.err.println("Opção inválida (escolha entre 1 a 4)");
            }

            }
        encerramento();
            scanner.close();
    }

        private static boolean fluxoEmprestimo(Biblioteca biblioteca, Scanner scanner) {
        while (true) {
            List<Livro> disponiveis = biblioteca.listarLivrosDisponiveis();
            if (disponiveis.isEmpty()) {
                System.out.println("Nenhum livro diponível no momento!");
                return true;
            }
            System.out.println("\nLIVROS DISPONIVEIS: ");
            System.out.println("----------------------------------------------");
            System.out.printf("%s %s %s %s%n", "ID |", "TÍTULO |", "AUTOR |", "PREÇO");
            System.out.println("---------------------------------------------------------------");
            for (Livro livro : disponiveis) {
                System.out.printf("%d - %s | %s | %s%n", livro.getId(), livro.getTitulo(), livro.getAutor().getNomeDoAutor(),formatarPreco(livro.getPreco()));
            }
            System.out.println("-----------------------------------------------");

            System.out.println("Digite o ID do livro que deseja (ou digite 0 para encerrar ou voltar): ");
            String inicio = scanner.nextLine().trim();
            if (inicio.equals("0")) {
                System.out.println("Deseja encerrar o sistema? (SIM / NÃO): ");
                String resp = scanner.nextLine().trim();
                if (resp.equalsIgnoreCase("SIM")) {
                    return false;
                }else{
                    return true;
                }
            }
           int id;
        try {
            id = Integer.parseInt(inicio);
        }catch (NumberFormatException e) {
            System.err.println("Número invalido");
            continue;
        }
        Optional<Livro> optional = biblioteca.buscarLivroPorId(id);
        if (optional.isEmpty() || !optional.get().isDisponivel()) {
            System.err.println("ID inválido! Tente novamente!");
            continue;
        }
        Livro livro = optional.get();

            System.out.println("Informe seu nome: ");
            String cliente = scanner.nextLine().trim();
            if (cliente.isEmpty()) {
                System.err.println("Formato de nome inválido! Tente novamente");
                continue;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataPrevista;
            while (true) {
                System.out.println("Informe a data prevista de devolução ou ENTER para padrão 3 meses: ");
                String dataString = scanner.nextLine().trim();
                if (dataString.isEmpty()) {
                    dataPrevista = LocalDate.now().plusMonths(3);
                    System.out.println("Data prevista padrão: " + dataPrevista.format(formatter));
                    break;
                }
                try {
                    dataPrevista = LocalDate.parse(dataString, formatter);
                    if (dataPrevista.isBefore(LocalDate.now())) {

                    System.err.println("Data não pode ser anterior à data de hoje. Tente novamente.");
                }else{
                        System.out.println("Data prevista definida: " + dataPrevista.format(formatter));
                        break;
                    }
            }catch (DateTimeParseException e) {
                    System.err.println("Formato inválido! Tente novamente");
                }
            }
            Emprestimo emprestimo = biblioteca.emprestimoComDataPrevista(livro,cliente,dataPrevista);

            System.out.println("\nEmpréstimo realizado com sucesso!");
            System.out.printf("Empréstimo : %d%n", emprestimo.getId());
            System.out.printf("Livro: %s (ID %d)%n", livro.getTitulo(), livro.getId());
            System.out.printf("Autor: %s%n", livro.getAutor().getNomeDoAutor());
            System.out.printf("Preço do exemplar: %s%n", formatarPreco(livro.getPreco()));
            System.out.printf("Cliente: %s%n", emprestimo.getNomeCliente());
            System.out.printf("Data do Empréstimo: %s%n", emprestimo.getDataDeEmprestimo().format(formatter));
            System.out.printf("Data Prevista de Devolução: %s%n", emprestimo.getDataPrevista());

            while (true){
                System.out.println("\nDeseja realizar outro empréstimo agora? (SIM / NÃO)");
                String resp = scanner.nextLine().trim();
                if (resp.equalsIgnoreCase("SIM")) {
                    break;
                }else if (resp.equalsIgnoreCase("NÃO") || resp.equalsIgnoreCase("NAO")) {
                    return true;
                }
            }
                    }
        }
            private static void listarEmprestimosAtivos(Biblioteca biblioteca){
                List<Emprestimo> ativos =  biblioteca.listarEmprestimosAtivos();
                if(ativos.isEmpty()){
                    System.out.println("\nNão há empréstimos ativos.");
                    return;
                }
                System.out.println("\nEmpréstimos ativos:");
                System.out.println("-------------------------------------------------------------------------------");
                System.out.printf("%s %s %s %s%n", "ID |", "LIVRO |", "CLIENTE |", "DATA PREVISTA DE DEVOLUÇÃO");
                System.out.println("-------------------------------------------------------------------------------");
                for (Emprestimo emprestimo : ativos) {
                    System.out.printf("%d - %s | %s | %s%n", emprestimo.getId(),emprestimo.getLivro().getTitulo(), emprestimo.getNomeCliente(), emprestimo.getDataDeEmprestimo());
                }
                System.out.println("-------------------------------------------------------------------------------");

        }
    private static void fluxoDevolucao (Biblioteca biblioteca, Scanner scanner){
List<Emprestimo> ativos =  biblioteca.listarEmprestimosAtivos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
if(ativos.isEmpty()){
    System.out.println("\nNão há empréstimos ativos para processar devolução." );
            return;
}
        System.out.println("\nEmpréstimos ativos:");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%s %s %s %s%n", "ID |", "LIVRO |", "CLIENTE |", "DATA PREVISTA");
        System.out.println("---------------------------------------------------------------");
        for (Emprestimo emprestimo : ativos) {
            System.out.printf("%d -  %s | %s | %s%n", emprestimo.getId(), emprestimo.getLivro().getTitulo(), emprestimo.getNomeCliente(), emprestimo.getDataPrevista());
        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("Digite o ID do empréstimo que vai devolver (ou 0 para voltar): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty())
            return;
        int idEmprestimo;
        try {
            idEmprestimo = Integer.parseInt(id);
        }catch (NumberFormatException e) {
            System.err.println("ID inválido! ");
            return;
        }
        Optional<Emprestimo> optional = biblioteca.buscarEmprestimoPorId(idEmprestimo);
        if (optional.isEmpty()) {
            System.out.println("Emprestimo não encontrado.");
            return;
        }
        Emprestimo emprestimo = optional.get();
        if (emprestimo.getDataDevolucao() != null){
            System.out.println("Este empréstimo já foi devolvido");
            return;
        }
        LocalDate dataPrevista;
        while (true) {
            System.out.println("Informe a data de devolução ou ENTER para hoje: ");
            String dataString = scanner.nextLine().trim();
            if (dataString.isEmpty()) {
                dataPrevista = LocalDate.now();
                break;
            }try {
                dataPrevista = LocalDate.parse(dataString,  formatter);
                break;
            }catch (DateTimeParseException e){
                System.err.println("Formato inválido");
            }
        }
        if (!dataPrevista.isAfter(emprestimo.getDataDeEmprestimo())) {
            emprestimo.setDataDevolucao(dataPrevista);
            emprestimo.getLivro().setDisponivel(true);
            System.out.println("\nDevolução registrada com sucesso. Sem multas.");
            return;
        }
        System.out.println("\nDevolução atrasada!");
        double preco = emprestimo.getLivro().getPreco();
        double multa = preco * 0.20;
        System.out.printf("Opões: Pagar multa de 20%% = %s OU Comprar o livro por %s%n", formatarPreco(multa), formatarPreco(preco));

        while (true) {
            System.out.println("Digite 'M' para pagar multa ou 'C' para comprar o livro: ");
            String escolha = scanner.nextLine().trim();
            if (escolha.equalsIgnoreCase("M")) {
                emprestimo.setDataDevolucao(dataPrevista);
                emprestimo.getLivro().setDisponivel(true);
                System.out.printf("\nMulta paga: %s. Livro devolvido e disponível novamente.%n", formatarPreco(multa));
                return;
            }else if (escolha.equalsIgnoreCase("C")) {
                emprestimo.setDataDevolucao(dataPrevista);
                biblioteca.venderLivros(emprestimo.getLivro());
                System.out.printf("\nLivro comprado por %s.", formatarPreco(preco));
                return;
            }
        }
    }

    private static void encerramento(){
        System.out.println("\nObrigado por usar o sistema. Até logo.");
    }
    private static String formatarPreco(double preco){
        return String.format("R$ %.2f", preco);
    }
    }

