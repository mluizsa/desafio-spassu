CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(40) NOT NULL,
    editora VARCHAR(40),
    edicao INTEGER,
    ano_publicacao VARCHAR(4),
    valor DECIMAL(10,2) NOT NULL
);

CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL
);

CREATE TABLE assunto (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(20) NOT NULL
);

CREATE TABLE livro_autor (
    livro_id INTEGER REFERENCES livro(id) ON DELETE CASCADE,
    autor_id INTEGER REFERENCES autor(id) ON DELETE CASCADE,
    PRIMARY KEY (livro_id, autor_id)
);

CREATE TABLE livro_assunto (
    livro_id INTEGER REFERENCES livro(id) ON DELETE CASCADE,
    assunto_id INTEGER REFERENCES assunto(id) ON DELETE CASCADE,
    PRIMARY KEY (livro_id, assunto_id)
);