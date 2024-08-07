# Sistema de Gestão de Bibliotecas

Este é um sistema de gestão de bibliotecas desenvolvido em Java. O objetivo do sistema é fornecer uma solução completa para o gerenciamento de bibliotecas, incluindo a gestão de livros, empréstimos, devoluções e usuários.

## Funcionalidades

- **Gerenciamento de Livros**: Adição, edição, remoção e consulta de livros.
- **Gerenciamento de Usuários**: Registro, edição, remoção e consulta de usuários.
- **Empréstimos e Devoluções**: Controle de empréstimos e devoluções de livros.
- **Notificações**: Sistema de notificações para lembretes de devolução.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Hibernate**: Framework de ORM para persistência de dados, utilizando SQLite como banco de dados.
- **Swing**: Biblioteca para a criação da interface gráfica do usuário (GUI).
- **Service Locator**: Padrão para gerenciar e fornecer dependências.
- **Injeção de Dependências**: Facilita a criação de componentes desacoplados.
- **Package by Features**: Organização do código baseada em funcionalidades.
- **Princípios SOLID**: Adotados para garantir um código robusto e manutenível.
- **Padrão Observer**: Utilizado para implementar o sistema de notificações.
- **Padrão MVC (Model-View-Controller)**: Separação das responsabilidades de apresentação, controle e acesso a dados.
- **Repository Design Pattern**: Abstração da camada de persistência para promover um design mais limpo e desacoplado.

## Arquitetura

A arquitetura do sistema é baseada em pacotes organizados por funcionalidades. Isso facilita a manutenção e a escalabilidade do sistema. A separação de responsabilidades é realizada através do padrão MVC, garantindo que a lógica de apresentação, controle e acesso a dados esteja devidamente segregada.

## Diagrama UML

O diagrama UML do projeto está disponível e pode ser encontrado ![aqui](https://drive.google.com/file/d/1K8ogWyEzIJ7PItW7miASz4Fei9j4mdok/view). Ele fornece uma visão detalhada da estrutura e dos relacionamentos entre as classes do sistema.

## Contribuição
Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou encontrar bugs, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença
Este projeto é licenciado sob a Licença GPL-3.0. Consulte o arquivo LICENSE para obter mais informações.
