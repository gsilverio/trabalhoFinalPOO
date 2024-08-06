
INSERT INTO tb_pessoa(nome, telefone, email, senha) VALUES ('Guilherme Silverio', '6299999999','gg@gmail.com','1234');
INSERT INTO tb_pessoa(nome, telefone, email, senha) VALUES ('Nathan Silverio', '6299999999','nathan@gmail.com','4567');

INSERT INTO tb_doador(id, tipo) VALUES (1, 'Doador')cls

INSERT INTO tb_receptor(id, tipo) VALUES (2, 'Receptor');

INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted,pessoa_id) VALUES ('Rex', 1, 'Cachorro', true, true,false,1);
INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted, pessoa_id) VALUES ('Jack', 1, 'Cachorro', true, true,false,1);
INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted, pessoa_id) VALUES ('Torin', 1, 'Cachorro', true, true,false,1);

INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted) VALUES ('Carlos', 1, 'Cachorro', true, true,false);
INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted) VALUES ('Jose', 1, 'Gato', true, true,false);
INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted) VALUES ('Madalena', 1, 'Cachorro', true, true,false);
INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted) VALUES ('Geovana', 1, 'Gato', true, true,false);
INSERT INTO tb_animal(nome, idade, tipo_animal, vacinado, castrado,is_adopted) VALUES ('Milaide', 1, 'Cachorro', true, true,false);

INSERT INTO tb_endereco(rua, numero, bairro, ponte_de_referencia, pessoa_id) VALUES ('Rua MG16', 'sn', 'Madre Germana', 'Proximo ao posto de saude', 1);


