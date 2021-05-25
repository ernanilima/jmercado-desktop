# jMercado
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/ernanilima/jmercado-desktop/blob/main/LICENSE)

# Sobre o projeto
Aplicação desktop desenvolvida com a linguagem Java.

jMercado foi construído com Java (back-end) e JavaFx (front-end) com o padrão MVC. Aplicação feita para simular o sistema de retaguarda de um Supermercado.

Esta aplicação utiliza **liberações** de acesso que podem ser definidas por **grupo de usuários** ou por **usuário**.

Alguns dados são gerados automaticamente e podem ser facilmente utilizados no banco de dados H2.

### A aplicação disponibiliza de:
- **Login de Usuário/Suporte**
    - Login utiliza código do usuário e senha.
    - **Suporte**
        - Login de suporte é possível utilizando o código **9999** e senha que pode ser calculada utilizando **dia * mês * ano**.
    - **Validações**
        - Usuário não pode estar bloqueado.
        - Mensagem de validação/erro é exibida abaixo de cada campo.
- **Mudar a Senha**
    - Necessário informar o código na tela de login para que possa mudar a senha.
    - Usuário novo é cadastrado sem senha, por esse motivo não é necessário informar senha atual, em validações é verificado se é um usuário novo.
    - **Validações**
        - É realizado login para que a senha possa ser alterada.
        - É verificado se o usuário existe, tenha senha ou não(usuário novo).
        - Senhas novas devem combinar.
- **Listagem**
    - Departamento, Grupo, Subgrupo, Produto, Grupo de Usuário e Usuário.
    - **Cadastro**
        - Todos possuem **Novo Cadastro**, **Editar**, **Excluir** e **Gravar**.
    - **Pesquisa**
        - Todos possuem pesquisa geral ou por coluna.
    - **Buscar Associação**
        - É exibido um dialog para que essa associação possa ser localizada.
    - **Validações**
        - Não pode gravar um código existente.
        - Não pode deixar campos importantes em branco.
        - Em associação, não pode informar um código que não esteja cadastrado, ex: todo grupo deve estar associado a um departamento.
- **Legenda/Mensagem**
    - Todos os campo possuem uma mensagem referente ao que deve ser preenchido no campo.
    - Ao selecionar um conteúdo em uma tabela, uma legenda/mensagem é exibida informando sobre o conteúdo selecionado.
- **Específicos**
    - **Produto**
        - Existem três associações para serem vinculadas, uma associação depende da outra para que seja devidamente exibida, ex: os grupos são exibidos com base no departamento informado, o mesmo acontece com os subgrupos que dependem do grupo.
        - **Validação**
            - Antes de gravar é validado se as associações pertencem uma a outra, ex: verifica se o grupo é realmente do departamento informado.
    - **Grupo de Usuário**
        - Possível informar liberações do grupo.
        - Possível igualar grupos e suas liberações.
    - **Usuário**
        - Possível remover senha.
        - Possível igualar usuário e suas liberacoes.
        - possível escolher o tipo de liberação que vai usar, se é por usuário ou por grupo de usuário.
        - **IMPORTANTE**
            - Ao cadastrar um novo usuário, o mesmo é gravado sem senha, para que uma senha seja atribuida a esse usuário que foi cadastrado, a solicitação deve ser feita na tela de login/mudar a senha, a tela de mudar a senha não exibe nada indicando que esse usuário existe, isso evita que alguém tente usar usuário sem senha.
    

PS: Essa aplicação foi apenas o contexto escolhido por mim já que trabalho a anos com sistemas do tipo, todo o código dentro deste projeto pode ser reutilizado para qualquer projeto

### Informações geradas automaticamente
- **2 Departamentos**
- **4 Grupos**
- **8 Subgrupos**
- **16 Produtos**

### Teclas de atalho
Tecla             | Função
------------------|-----------------------------
`ctrl` + `f`      | Ir para o campo pesquisar
`ctrl` + `n`      | Abrir novo cadastro
`ctrl` + `e`      | Editar cadastro selecionado
`ctrl` + `delete` | Excluir cadastro selecionado
`ctrl` + `s`      | Gravar Cadastro
`ctrl` + `esc`    | Cancelar sem gravar

# Mais
Este projeto faz parte do meu portfólio pessoal, então, ficarei feliz se você puder me fornecer algum feedback sobre o projeto, código, estrutura ou qualquer coisa que você possa relatar que possa me tornar um desenvolvedor melhor!

Contato através do [LinkedIn](https://www.linkedin.com/in/ernanilima)

Email: ernani.tecc@gmail.com

Além disso, você pode usar este projeto como quiser, seja para estudar ou fazer melhorias!

É grátis!

# Tecnologias utilizadas
- Java
- Javafx
- Lombok
- Spring Boot
- JPA / Hibernate

### Banco de dados
- PostgreSQL
- H2

## IDE utilizada
- IntelliJ IDEA ([Site Oficial](https://www.jetbrains.com/pt-br/idea/))
- Scene Builder ([Site](https://gluonhq.com/products/scene-builder/#download))

# Como executar o projeto

Pré-requisitos: Java 8

O projeto possui um arquivo [.jar](https://github.com/ernanilima/jmercado-desktop/blob/main/target/JMercado-0.1.0.jar) que pode ser executado localmente com o comando `java -jar JMercado-0.1.0.jar`

## Clonar repositório

```bash
git clone https://github.com/ernanilima/jmercado-desktop.git
```

# Autor

Ernani Lima - https://ernanilima.com.br/

