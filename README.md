ManutriApp - Sistema de Atendimento Clínico

O **ManutriApp** é um sistema desenvolvido em **Java** focado na gestão de atendimentos clínicos e nutricionais. O projeto visa facilitar o cadastro de pacientes, realização de anamnese e cálculos antropométricos através de uma interface via console.

## 🚀 Funcionalidades Atuais

* **Cadastro de Pacientes:** Registro de dados pessoais (Nome, idade, contato).
* **Anamnese:** Coleta de histórico clínico e hábitos do paciente.
* **Cálculo de IMC:** Funcionalidade integrada para calcular o Índice de Massa Corporal automaticamente.
* **Menu Interativo:** Navegação via terminal para acesso rápido às funções.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 21+)
* **IDE:** IntelliJ IDEA
* **Controle de Versão:** Git & GitHub
* **Paradigma:** Programação Orientada a Objetos (POO)

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura organizada em pacotes para facilitar a manutenção:
* `application`: Contém a classe principal (`AtendimentoClinicoApp`) e o menu do sistema.
* `model`: Classes de domínio (Paciente, Anamnese, etc.).
* `service`: Lógica de negócios e cálculos.

## 🔧 Como Executar o Projeto

### Pré-requisitos
* Java JDK instalado.
* Git instalado.
* IntelliJ IDEA (recomendado) ou qualquer IDE Java.

### Passo a passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/1colossos/sistema-Manutri](https://github.com/1colossos/sistema-Manutri)
    ```

2.  **Abra o projeto na sua IDE.**

3.  **Localize a classe principal:**
    Vá até `src/application/AtendimentoClinicoApp.java`.

4.  **Execute o arquivo:**
    Clique em `Run` ou use o atalho `Shift + F10` (no IntelliJ).

## 🤝 Colaboração

Este projeto é desenvolvido com fins educacionais e práticos. Sugestões e *pull requests* são bem-vindos para melhorias como:
* Implementação de persistência de dados (Banco de Dados).
* Interface Gráfica.
* Relatórios em PDF.

---
Desenvolvido por Afonso Gabriel