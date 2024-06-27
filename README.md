# Hotel
Sistema de Reserva de Hotéis
 
Tecnologias Aplicadas no Projeto
Java e Spring Boot:

O projeto foi desenvolvido em Java, utilizando o framework Spring Boot. Spring Boot é escolhido por sua facilidade de configuração, robustez e ampla comunidade de suporte.
PostgreSQL:

Por que PostgreSQL?
PostgreSQL foi escolhido como banco de dados principal devido às suas características de robustez, confiabilidade, suporte a transações ACID (Atomicidade, Consistência, Isolamento, Durabilidade), e suporte a JSON nativo, o que facilita o armazenamento e consulta de dados estruturados e não estruturados.
Possui excelente desempenho em operações complexas e suporta grandes volumes de dados, essencial para um sistema de reserva de hotéis que lida com múltiplas operações de pesquisa, comparação e reserva simultaneamente.
Docker:

Implementações Docker:
Docker foi utilizado para a containerização da aplicação. Docker permite empacotar o aplicativo junto com suas dependências em um contêiner, garantindo que a aplicação seja executada de maneira consistente em diferentes ambientes.
Facilita a implantação, escalabilidade e gerenciamento do sistema, permitindo que cada componente (aplicação, banco de dados, serviços auxiliares) seja isolado e gerenciado de forma independente.
Resumo das Implementações
Java e Spring Boot: Utilizados para o desenvolvimento da lógica de negócios e criação dos endpoints RESTful para interação com o sistema.

PostgreSQL: Escolhido como banco de dados principal devido à sua robustez, escalabilidade e suporte a transações ACID, adequado para um sistema de reserva de hotéis que requer persistência confiável e alta disponibilidade.

Docker: Implementado para facilitar a distribuição e execução da aplicação e seus componentes em ambientes de desenvolvimento, teste e produção de maneira consistente e eficiente.

Essas escolhas tecnológicas foram feitas visando garantir a eficiência, escalabilidade e confiabilidade do sistema de reserva de hotéis, proporcionando uma base sólida para expansão e manutenção futuras.