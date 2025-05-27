
# Desafio Técnico – Recruitment Backend

## Visão Geral

Este projeto contém dois microsserviços desenvolvidos em **Kotlin com Spring Boot e Java 21**:

* `recruitment-enrollment-service` (porta `58080`)
* `recruitment-financial-service` (porta `58081`)

O desafio consiste na criação de um job agendado que coleta e processa informações entre esses dois serviços, respeitando as premissas de arquitetura e segurança.

---

### ⚠️ Windows (Ajuste Necessário)

Caso esteja utilizando **Windows**, execute o comando abaixo para evitar problemas com caminhos longos:

```bash
git config --global core.longpaths true
```

E ative a flag no registro do sistema:

```
Computador\HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\FileSystem -> LongPathsEnabled = 1
```
---

## 📦 Clonando o Repositório

```bash
git clone https://github.com/vcazelli/recruitment-backend-test.git
cd recruitment-backend-test
```

---

## ⚙️ Serviços

### recruitment-enrollment-service

* Porta: `58080`
* Banco: MongoDB (`users`) e MySQL (`enrollment`)

### recruitment-financial-service

* Porta: `58081`
* Banco: MongoDB (`coursePrice`) e MySQL (`instalment`)

---

## 🐳 Setup com Docker

Pré-requisitos:

* [Docker](https://www.docker.com/) instalado e rodando

### 1. Baixar os dados

Baixe e extraia os dados do banco no diretório raiz do projeto:

📥 [Download `data.zip`](https://kroton-my.sharepoint.com/:u:/r/personal/vinicius_c_ferreira_kroton_com_br/Documents/Banco%20de%20dados%20-%20teste%20recrutamento/data.zip?csf=1&web=1&e=fNNdya)

Após extrair, a estrutura de pastas deve ser:

```
recruitment-backend-test/
│
├── data/
│   ├── mongo-data/
│   ├── mysql-data/
│   └── redis-data/
```

### 2. Subir containers

Execute:

```bash
docker-compose up
```

**Portas padrão:**

* MongoDB: `27017`
* MySQL: `3306`
* Redis: `6379`

⚠️ **Atenção:** verifique se essas portas não estão em uso por outros serviços em sua máquina.

---

## 🧪 Desafio

Você deve criar um **job agendado no `recruitment-enrollment-service`** que será executado **diariamente durante a madrugada**.

### Funcionalidade do Job

1. **Selecionar alunos**: buscar todos os alunos cujo **dia da semana de nascimento** é igual ao dia atual de execução do job.

2. **Consultar matrículas**:

   * Para cada aluno, buscar todas as suas matrículas.

3. **Calcular valores**:

   * **Total Pago**: somar todos os valores das parcelas (`instalment`) com status `PAID` de todas as matrículas do aluno.
   * **Valor Restante**: somar (`duração` da matrícula × `valor do curso`) e subtrair o total pago.

4. **Gerar Relatório**: uma lista com:

   * ID do aluno
   * Nome do aluno
   * Total pago
   * Valor restante

---

## 🧩 Premissas

* Os serviços estão implantados em um **cluster Kubernetes** com mais de uma instancia em execução simultaneamente.
* Ambos os serviços possuem acesso a **recursos AWS**.
* Um serviço **não pode acessar diretamente o banco de dados do outro**.
* **Não é permitido modificar** a estrutura dos bancos de dados ou das tabelas existentes.

---

## 📄 Modelo de Relacionamento (ER)

![Modelo ER](./docs/er-diagram.svg)

> Estrutura completa entre `users`, `enrollment`, `course_price`, e `instalment`.

---

## ✅ Conclusão

Este desafio avalia sua habilidade com:

* Kotlin + Spring Boot
* Integração entre microsserviços
* MongoDB e MySQL
* Respeito a restrições de arquitetura
* Agendamento e manipulação de dados

Boa sorte! 🚀