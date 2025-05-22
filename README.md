# Spotifei

Spotifei é uma plataforma desktop desenvolvida em Java para gerenciamento de músicas e podcasts (sem reprodução de áudio). O sistema permite que usuários realizem buscas, curtam músicas, adicionem a playlists e acompanhem o histórico de interações.

## Tecnologias Utilizadas

- Java (Swing para interface gráfica)
- JDBC
- PostgreSQL
- Arquitetura MVC
- PgAdmin (administração do banco)

## Funcionalidades

- Cadastro e login de usuários
- Busca por músicas (nome, artista, gênero)
- Curtir e descurtir músicas
- Criação e gerenciamento de playlists
- Histórico de buscas
- Histórico de curtidas e descurtidas
- Interface gráfica com Java Swing

## Banco de Dados

- Estrutura relacional com tabelas como:
  - `usuarios`
  - `musicas`
  - `curtidas`
  - `playlists`
  - `playlist_musicas`
  - `historico_buscas`
  - `historico_curtidas`

O backup do banco de dados está disponível na raiz do projeto em formato `.backup` .

## Como Executar

O arquivo `.jar` gerado pode ser encontrado em:

`/executavel/Spotifei.jar`

Para executá-lo, é necessário ter o Java instalado. Você pode rodar o programa pelo terminal com o comando:

java -jar Spotifei.jar

ou:

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/spotifei.git
   ```

2. Importe o projeto em uma IDE Java (por exemplo, NetBeans).

3. Configure a conexão JDBC com o PostgreSQL no arquivo `Conexao.java`.

4. Restaure o banco de dados utilizando o arquivo de backup `.backup` no PgAdmin (modo "custom").

5. Execute a classe principal para iniciar o sistema.

## Observações

- O projeto **não possui reprodução de áudio**.
- Todo o **histórico de buscas e curtidas/descurtidas** é registrado no banco de dados.
- A **duração das músicas** é armazenada em **segundos**.

## Relatório

O relatório está no link abaixo:

https://docs.google.com/document/d/1Myvzgzh3_S2YQcZEDXrQQrQED5CQIL9C4ofki9bLTBY/edit?usp=sharing 

## Autor

Desenvolvido por **Leonardo Bezzi Elias** como parte de um projeto acadêmico da **FEI**.
