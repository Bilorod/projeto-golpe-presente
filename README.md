# Lógica Verificação e Backend - Projeto Verificador de Boletos

## 📝 Descrição

Esta branch implementa a parte do backend responsável pela validação do boleto bancário, com destaque para o **serviço mock** que simula a verificação do banco do destinatário.

Faz parte do projeto maior de verificação de boletos criado para o desafio técnico do Bradesco.

---

## 🚀 Funcionalidades

- Validação da estrutura do código de barras do boleto
- Serviço mock para validação do banco do destinatário
- Implementação REST API usando Spring Boot
- Tratamento básico de exceções para garantir respostas padronizadas

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot (Spring Web)
- Maven
- Docker (para empacotamento e execução da aplicação)

---

## 🔍 Detalhes Técnicos

- **VerificadorMockService**: simula a consulta externa para validação do nome do destinatário.
- **Controller REST**: recebe requisições para validação do boleto e retorna os resultados das validações.
- **Tratamento de erros**: respostas estruturadas com mensagens claras para casos de erro na validação.

---

## ✅ Testes

Os testes foram realizados manualmente, utilizando diferentes cenários de entrada para garantir o funcionamento correto das validações.

---

## 👨‍💻 Autor

Gustavo Cardoso dos Santos – responsável pela implementação da verificação mock e backend desta branch.

---

## ⚠️ Observações

Esta branch é parte integrante do projeto maior de verificação de boletos, e pode depender de outras funcionalidades presentes em branches ou repositórios relacionados.
