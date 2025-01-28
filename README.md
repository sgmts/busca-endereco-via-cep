# 📦 Busca Endereço Via CEP
Este projeto é uma API desenvolvida em Java com Spring Boot, que realiza consultas de endereços a partir de um CEP utilizando a API ViaCEP. 
O objetivo do projeto é demonstrar boas práticas de desenvolvimento, arquitetura limpa e tratamento de exceções.

---
## 🚀 Tecnologias Utilizadas
- Java 17 
- Spring Boot 3.x 
- RestTemplate para consumo de APIs externas 
- SpringDoc OpenAPI para documentação dos endpoints 
- Lombok para simplificar o código 
- Maven para gerenciamento de dependências

---
## ⚙️ Funcionalidades
1. Consulta de endereço pelo CEP. 
2. Tratamento de erros robusto:
   - **CEP inválido**: Quando o formato do CEP não é válido.
   - **CEP não encontrado**: Quando o CEP não está cadastrado na base de dados da API ViaCEP.
   - **Erros gerais**: Como problemas de comunicação com a API externa. 
3. Documentação automática via Swagger.

---
## 🔗 Endpoints
1. Buscar Endereço por CEP
    - Método: `GET` 
    - URL: `/api/buscar-cep/{cep}`
    - #### Respostas
    - 200 OK
    - ```
      {
      "cep": "01001-000",
      "logradouro": "Praça da Sé",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP"
      }
    - 400 Bad Request
    - ```
      {
      "code": "1001",
      "message": "CEP inválido. O formato esperado é '00000-000' ou '00000000'."
      }```
   - 404 Not Found:
   -  ```
      {
      "code": "1002",
      "message": "CEP válido, mas não encontrado na base de dados."
      }
      ```
   - 500 Internal Server Error
   - ```
     {
     "code": "1003",
     "message": "Erro Interno!"
     }
     ``` 
---
## 🛠️ Como Rodar o Projeto
1. Pré-requisitos
   - Java 17+ 
   - Maven 3.8+
2. Clone o Repositório
```
git clone https://github.com/seu-usuario/busca-endereco-via-cep.git
cd busca-endereco-via-cep
```
3. Execute a Aplicação
```
mvn spring-boot:run
```
---
## 🧪 Testes
1. Utilize o Swagger para explorar os endpoints:
   - Acesse: http://localhost:8080/swagger-ui.html.
2. Ou utilize ferramentas como Postman ou Insomnia.
---
## 🤝 Contribuições
Sinta-se à vontade para clonar este repositório e sugerir melhorias. 
Este projeto foi desenvolvido para demonstrar habilidades técnicas e está aberto a críticas e sugestões.

