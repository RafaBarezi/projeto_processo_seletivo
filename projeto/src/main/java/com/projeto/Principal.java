package com.projeto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {

        Locale localeBR = new Locale("pt", "BR");
        // Configurando data 
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Configurando salário para deixar 2 casas decimais na moeda
        NumberFormat formatoMoeda = NumberFormat.getInstance(localeBR);
        formatoMoeda.setMinimumFractionDigits(2);
        formatoMoeda.setMaximumFractionDigits(2);

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("1101.85"), "atendente."));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("1101.85"), "atendente."));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1961, 5, 2), new BigDecimal("2203.65"), "supervisor."));
        funcionarios.add(new Funcionario("Ana", LocalDate.of(1988, 10, 14), new BigDecimal("3305.45"), "gerente."));
        funcionarios.add(new Funcionario("José", LocalDate.of(1995, 1, 5), new BigDecimal("1101.85"), "recepcionista."));

        // Removendo o João
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        System.out.println("\n********************");

        // Mostrando lista de funcinários
        System.out.println("\nLista de Funcionários:\n");
        for (Funcionario f : funcionarios) {
            String dataFormatada = f.getDataNascimento().format(formatoData);
            String salarioFormatado = formatoMoeda.format(f.getSalario());
            System.out.println(f.getNome() + " nasceu em " + dataFormatada + ". Antes do aumento de 10%, recebia  R$ " + salarioFormatado + " sendo " + f.getFuncao());
        }

        // Aumento de 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));
        System.out.println("\n********************");

        // Reunindo por tipo de trabalho
        Map<String, List<Funcionario>> agrupados = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\nFuncionários por função:\n");
        agrupados.forEach((funcao, lista) -> {

            lista.forEach(f -> System.out.print(f.getNome()));
            System.out.println(" trabalha como " + funcao);
        });
        System.out.println("\n********************");

        // Aniversariantes em outubro E dezembro
        System.out.println("\nAniversariantes de outubro e dezembro: \n");

        DateTimeFormatter formatoMesDia = DateTimeFormatter.ofPattern("dd/MM");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(f -> System.out.println(f.getNome() + " faz aniversário em " + f.getDataNascimento().format(formatoMesDia)));
        System.out.println("\n********************");

        // Pessoa mais nova e mais velha
        System.out.println("\nFuncionário mais novo e mais velho: \n");
        Funcionario maisNovo = funcionarios.stream()
                .max(Comparator.comparing(Funcionario::getDataNascimento))
                .get();
        int idadeNovo = Period.between(maisNovo.getDataNascimento(), LocalDate.now()).getYears();
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .get();
        int idadeVelho = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.print("O funcionário mais novo é " + maisNovo.getNome() + ", pois  nasceu em " + maisNovo.getDataNascimento().format(formatoData) + " e tem " + idadeNovo + " anos.");
        System.out.print("\nO funcionário mais velho é " + maisVelho.getNome() + ", pois nasceu em " + maisVelho.getDataNascimento().format(formatoData) + " e tem " + idadeVelho + " anos.\n");
        System.out.println("\n********************");

        // Colocando em ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética: \n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()+(" é ")+f.getFuncao()));
        System.out.println("\n********************\n");

        System.out.println("total de salários após aumento:");

        // Depois do aumento de 10% 
        for (Funcionario f : funcionarios) {
            String salarioFormatado = formatoMoeda.format(f.getSalario());
            System.out.println(f.getNome() + " e agora recebe R$ " + salarioFormatado + " por mês sendo " + f.getFuncao());
        }

        // Total de salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: R$ " + formatoMoeda.format(totalSalarios));
        System.out.println("\n********************");

        //Quantidade de salários minimos
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantos salários mínimos cada funcionário recebe:\n");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(salarioMinimo, 0, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " recebe " + qtd + " salário(s) mínimo(s).");
        });
    }
}
