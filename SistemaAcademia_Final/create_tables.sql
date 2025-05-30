CREATE TABLE IF NOT EXISTS aluno (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf TEXT NOT NULL,
    data_nascimento TEXT NOT NULL,
    telefone TEXT,
    email TEXT
);

CREATE TABLE IF NOT EXISTS treino (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    aluno_id INTEGER NOT NULL,
    tipo TEXT NOT NULL,
    descricao TEXT,
    duracao_minutos INTEGER,
    data_inicio TEXT,
    FOREIGN KEY(aluno_id) REFERENCES aluno(id)
);
