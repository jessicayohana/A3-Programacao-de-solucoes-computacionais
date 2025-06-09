# Sistema de Gerenciamento de Academia

Projeto desenvolvido como projeto da UC Programação de Soluções Computacionais pelas alunas:
- Bruna de Oliveira - RA: 122026787
- Jéssica Yohana Otto - RA: 122022722
- Suelen cristina da Rosa - RA: 122422589
Link do escopo do trabalho: [https://drive.google.com/file/d/1h72oH77rRf8Z3nVPzrTxlsM6kwsP4nHD/view?usp=sharing](https://drive.google.com/file/d/1h72oH77rRf8Z3nVPzrTxlsM6kwsP4nHD/view?usp=sharing)

### Módulo de Alunos
- Cadastro de aluno com nome, CPF, data de nascimento, telefone e e-mail
- Listagem de todos os alunos
- Edição (nome, telefone, e-mail)
- Exclusão de alunos
- Busca por nome, CPF ou e-mail

### Módulo de Treinos
- Cadastro de treino vinculado a um aluno
- Listagem dos treinos por aluno
- Edição de treino
- Exclusão de treino

## Tecnologias Utilizadas

- Java
- JDBC
- SQLite
- Interface em terminal (CLI)
- Padrão MVC com DAOs e Models


## Como Executar

1. Baixe o driver JDBC SQLite:
   [sqlite-jdbc-3.36.0.3.jar](https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar)

2. Coloque o `.jar` na pasta `lib/` do projeto.

3. Compile os arquivos:

```bash
cd caminho/do/projeto/src
javac -cp ".;../lib/sqlite-jdbc-3.36.0.3.jar" main\\App.java dao\\*.java model\\*.java util\\*.java

4. Compile os arquivos: Rode o projeto:

java -cp ".;../lib/sqlite-jdbc-3.36.0.3.jar" main.App

## Observações

- O CPF é validado com base nos dígitos verificadores.

- As datas devem ser inseridas no formato dd-MM-aaaa.

- O banco academia.db é criado automaticamente na raiz do projeto.
