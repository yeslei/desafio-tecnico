# Desafio Técnico Estágio - ANBIMA

Este repositório contém uma solução para o desafio.
O sistema demonstra o fluxo completo: **String Posicional (Frontend)** $\rightarrow$ **Gateway (Módulo A)** $\rightarrow$ **Fila de Mensagens (RabbitMQ)** $\rightarrow$ **Processador (Módulo B)** $\rightarrow$ **Persistência de Dados (PostgreSQL)**.

---

## 🚀 Estrutura do Projeto

O projeto é dividido em três componentes principais:

| Componente | Linguagem/Framework | Objetivo no Fluxo |
| :--- | :--- | :--- |
| **`frontend/`** | Angular | Interface para inserção da string posicional e consulta dos pedidos. |
| **`backend/modulo-a-gateway`** | Spring Boot | **Entrada de Pedidos:** Recebe a string, valida, calcula o valor, salva (**RECEBIDO**) e publica na fila. |
| **`backend/modulo-b-processor`** | Spring Boot | **Processamento de Entrega:** Consome a fila, atualiza o status para (**ENTREGUE**) e oferece endpoints de consulta (Listar/Detalhe). |

---

## ⚙️ Tecnologias Utilizadas

* **Backend:** Java, Spring Boot (Módulos A e B), Maven (ou Gradle).
* **Frontend:** Angular.
* **Banco de Dados:** PostgreSQL.
* **Mensageria:** RabbitMQ.
* **Controle de Versão:** Git e GitHub.