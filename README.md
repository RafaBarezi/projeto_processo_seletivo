# Projeto Java - Case de Processo Seletivo

Este código foi desenvolvido como parte de um **case em um processo seletivo**.  
A proposta era desenvolver um projeto Java com os seguintes requisitos:

---

## 1. Classe `Pessoa`

- Atributos:
  - `nome` (String)
  - `dataNascimento` (LocalDate)

---

## 2. Classe `Funcionario`

- Estende a classe `Pessoa`.
- Atributos adicionais:
  - `salario` (BigDecimal)
  - `funcao` (String)

---

## 3. Classe `Principal`

Responsável por executar as ações:

1. Inserir todos os funcionários na lista, mantendo a ordem e informações da tabela fornecida.  
2. Remover o funcionário **“João”** da lista.  
3. Imprimir todos os funcionários com todas as informações:
   - Datas no formato **dd/MM/aaaa**
   - Valores numéricos com separador de milhar como ponto e decimal como vírgula
4. Aplicar **10% de aumento de salário** e atualizar a lista.  
5. Agrupar os funcionários por função em um **MAP** (chave = função, valor = lista de funcionários).  
6. Imprimir os funcionários agrupados por função.  
7. Imprimir os funcionários que fazem aniversário nos meses **10 e 12**.  
8. Imprimir o funcionário com a **maior idade**, mostrando nome e idade.  
9. Imprimir a lista de funcionários por **ordem alfabética**.  
10. Imprimir o **total dos salários** dos funcionários.  
11. Imprimir quantos **salários mínimos** cada funcionário recebe, considerando que o salário mínimo é **R$ 1.212,00**.

---

> Desenvolvido em Java, utilizando boas práticas de programação, manipulação de datas e valores financeiros com `BigDecimal`.
