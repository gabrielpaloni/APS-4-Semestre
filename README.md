# APS 4º Semestre - PixelHaus



Projeto da Atividade Prática Supervisionada (APS) do 4º Semestre, focado na criação de um sistema de gestão de vendas de jogos em Java.



## 🎯 Objetivos do Projeto



Este trabalho foi desenvolvido para aplicar e aprimorar conhecimentos em:



* **Padrão de Projeto MVC:** Organização do código separando a lógica de negócio (Model), a interface (View) e o controle (Controller).

* **Padrão de Projeto DAO:** Abstração do acesso aos dados (`JogoDAO`, `UsuarioDAO`) para facilitar a manutenção da conexão com o banco de dados.

* **Java Swing:** Aprimoramento no uso de Layouts e componentes visuais para a criação de interfaces de usuário (`TelaLogin`, `TelaCadastro`, etc.).

* **Conexão com Banco de Dados:** Utilização de JDBC para conectar a aplicação a um banco de dados MySQL, com gerenciamento de conexão (Singleton).



## 📁 Estrutura do Projeto



O projeto segue a arquitetura MVC e DAO:



* `controller/`: Contém as classes que fazem a ponte entre a View e o Model (ex: `LoginController`, `CadastroController`).

* `model/bean/`: Contém as classes de entidade (POJOs) que representam os dados (ex: `Jogo`, `Usuario`).

* `model/dao/`: Contém as classes de Acesso a Dados (Data Access Object) responsáveis pela comunicação com o banco (ex: `UsuarioDAO`).

* `view/`: Contém as telas (JFrames/JPanels) da aplicação (ex: `TelaLogin`).

* `database/`: Contém a classe de gerenciamento da conexão com o MySQL.

* `resources/`: Contém arquivos não-Java, como imagens e o arquivo de configuração.



## 🚀 Como Executar



1.  **Clone o repositório:**

&nbsp;   ```bash

&nbsp;   git clone [https://github.com/gabrielpaloni/APS-4-Semestre.git](https://github.com/gabrielpaloni/APS-4-Semestre.git)

&nbsp;   ```

2.  **Configure o Banco de Dados:**

&nbsp;   * Abra seu gerenciador MySQL (Workbench, DBeaver, etc.).

&nbsp;   * Execute o script `database/pixelhaus\_script.sql` para criar o banco `pixelhaus` e todas as tabelas necessárias.

&nbsp;   * Vá até a pasta `/resources/`.

&nbsp;   * Renomeie o arquivo `config.properties.example` para `config.properties`.

&nbsp;   * Abra o `config.properties` e preencha com suas credenciais do MySQL.

3.  **Abra na IDE:**

&nbsp;   * Abra o projeto no IntelliJ IDEA.

&nbsp;   * Localize o arquivo `Main.java` e execute-o.



## 👨‍💻 Autores



* Gabriel S. B. Paloni

* Ana Paula Garbin

* Graziela Lopes Romualdo 

