package main;

import dao.AlunoDAO;
import dao.TreinoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.Aluno;
import model.Treino;

public class App {

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma1 += digito * (10 - i);
                soma2 += digito * (11 - i);
            }

            int dig1 = 11 - (soma1 % 11);
            if (dig1 > 9) dig1 = 0;
            soma2 += dig1 * 2;
            int dig2 = 11 - (soma2 % 11);
            if (dig2 > 9) dig2 = 0;

            return dig1 == Character.getNumericValue(cpf.charAt(9)) &&
                   dig2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO();
        dao.criarTabela();
        TreinoDAO treinoDAO = new TreinoDAO();
        treinoDAO.criarTabela();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Atualizar aluno");
            System.out.println("4. Excluir aluno");
            System.out.println("5. Cadastrar treino");
            System.out.println("6. Listar treinos por aluno");
            System.out.println("7. Atualizar treino");
            System.out.println("8. Excluir treino");
            System.out.println("9. Buscar aluno por nome ou CPF");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF (000.000.000-00): ");
                    String cpf = sc.nextLine().replaceAll("[^0-9]", "");
                    if (!validarCPF(cpf)) {
                        System.out.println("CPF inválido!");
                        break;
                    }
                    System.out.print("Data de nascimento (dd-MM-aaaa): ");
                    String dataNasc = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    try {
                        Date data = new SimpleDateFormat("dd-MM-yyyy").parse(dataNasc);
                        String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(data);
                        Aluno aluno = new Aluno(nome, cpf, dataFormatada, telefone, email);
                        dao.inserir(aluno);
                        System.out.println("Aluno cadastrado!");
                    } catch (Exception e) {
                        System.out.println("Data inválida. Use o formato dd-MM-aaaa.");
                    }
                    break;
                case 2:
                    List<Aluno> alunos = dao.listar();
                    for (Aluno a : alunos) {
                        System.out.println(a);
                    }
                    break;
                case 3:
                    System.out.print("ID do aluno a atualizar: ");
                    int idAtt = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo telefone: ");
                    String novoTel = sc.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = sc.nextLine();
                    dao.atualizar(new Aluno(idAtt, novoNome, "", "", novoTel, novoEmail));
                    break;
                case 4:
                    System.out.print("ID do aluno a excluir: ");
                    int idExc = sc.nextInt();
                    dao.excluir(idExc);
                    break;
                case 5:
                    System.out.print("ID do aluno: ");
                    int alunoId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo do treino: ");
                    String tipo = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();
                    System.out.print("Duração (min): ");
                    int dur = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Data de início (dd-MM-aaaa): ");
                    String dataTreino = sc.nextLine();
                    try {
                        Date data = new SimpleDateFormat("dd-MM-yyyy").parse(dataTreino);
                        String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(data);
                        Treino treino = new Treino(alunoId, tipo, desc, dur, dataFormatada);
                        treinoDAO.inserir(treino);
                        System.out.println("Treino cadastrado!");
                    } catch (Exception e) {
                        System.out.println("Data inválida. Use o formato dd-MM-aaaa.");
                    }
                    break;
                case 6:
                    System.out.print("ID do aluno: ");
                    int idBusca = sc.nextInt();
                    List<Treino> treinos = treinoDAO.listarPorAlunoId(idBusca);
                    for (Treino t : treinos) {
                        System.out.println(t);
                    }
                    break;
                case 7:
                    System.out.print("ID do treino a atualizar: ");
                    int idT = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo tipo: ");
                    String tipoNovo = sc.nextLine();
                    System.out.print("Nova descrição: ");
                    String descNova = sc.nextLine();
                    System.out.print("Nova duração (min): ");
                    int novaDur = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nova data (dd-MM-aaaa): ");
                    String novaData = sc.nextLine();
                    try {
                        Date data = new SimpleDateFormat("dd-MM-yyyy").parse(novaData);
                        String dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(data);
                        treinoDAO.atualizar(new Treino(idT, 0, tipoNovo, descNova, novaDur, dataFormatada));
                    } catch (Exception e) {
                        System.out.println("Data inválida.");
                    }
                    break;
                case 8:
                    System.out.print("ID do treino a excluir: ");
                    int idDel = sc.nextInt();
                    treinoDAO.excluir(idDel);
                    break;
                case 9:
                    System.out.print("Digite nome, CPF ou email: ");
                    String termo = sc.nextLine();
                    List<Aluno> encontrados = dao.buscarPorTermo(termo);
                    for (Aluno a : encontrados) {
                        System.out.println(a);
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
