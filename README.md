> ⚠️ **Este teste não é eliminatório**  
> Ele será utilizado como base para perguntas técnicas durante a entrevista.

# 🧪 Desafio Técnico – Backend (1 a 4 horas estimadas)

🎯 Este teste tem como objetivo avaliar seu raciocínio técnico, clareza de pensamento e capacidade de propor soluções viáveis diante de problemas reais.

💡 Sinta-se livre para usar ferramentas e abordagens com as quais se sinta confortável. Você pode sugerir melhorias, simplificações ou até apresentar alternativas à proposta original.

🏆 Independentemente do resultado, todos os participantes recebem feedback técnico com sugestões de melhoria após a avaliação.

🙏 Agradecemos muito pelo seu tempo e dedicação.  Estamos ansiosos para conhecer melhor seu trabalho na próxima etapa! 🚀


## Esforço estimado 

| Etapa                     | Obrigatório | Tempo estimado |
| ------------------------- | ----------- | -------------- |
| Clonar repositório        | ✅           | 2 min          |
| Instalar Docker           | ✅           | 5 min          |
| Executar app com Docker   | ✅           | 10 min         |
| Analisar código existente | ✅           | 20 min         |
| Documentar solução        | ✅           | 20 min         |
| Codificar solução         | ✨ Opcional  | \~3 h          |


> ###  ℹ️ O teste foi elaborado para rodar localmente, não necessita de infraestrutura cloud

## Visão Geral

Este projeto contém dois microsserviços desenvolvidos em **Kotlin com Spring Boot e Java 21**:

* `recruitment-enrollment-service` (porta `58080`)
* `recruitment-financial-service` (porta `58081`)

O desafio consiste na criação de um job agendado que coleta e processa informações entre esses dois serviços.

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

📥 [Download dos volumes do docker (onedrive) ~ 2.3GB](https://kroton-my.sharepoint.com/:u:/g/personal/vinicius_c_ferreira_kroton_com_br/EbFLyYBAFl5BjH-jGpJ6V7QBmW5UTZPs2qSS3KnG9zonWA)

Após extrair, a estrutura de pastas deve ser:

```
recruitment-backend-test/
│
├── data/
│   ├── mongo-data/
│   ├── mysql-data/
│   └── redis-data/
├── ...
```

### 2. Subir containers

Execute:

```bash
docker-compose up
```

**Portas:**

* MySQL: `58082`
* Redis: `58083`
* MongoDB: `58084`

---

## 🧪 Desafio

Você deve criar um **job agendado no `recruitment-enrollment-service`** que será executado **diariamente durante a madrugada**.

### Funcionalidade do Job

1. **Selecionar alunos**: buscar todos os alunos cujo **dia da semana de nascimento** é igual ao dia da semana da execução do job. 💡 *Exemplo:* se o usuário nasceu em 18/12/1996 (quarta-feira) e o job estiver sendo executado numa quarta-feira, ele deverá ser processado.


2. **Consultar matrículas**:

   * Para cada aluno, buscar todas as suas matrículas.

3. **Calcular valores**:

   * **Total Pago**: somar todos os valores `instalment.paid_amount` das parcelas com status `PAID` de todas as matrículas dos alunos elegíveis.
   * **Valor Restante**: somar (duração da matrícula `enrollment.duration` × valor do curso `coursePrice.price`) e subtrair o total pago.

4. **Gerar Relatório**: uma lista com:

   * ID do aluno
   * Nome do aluno
   * Data de nascimento
   * Total pago
   * Valor restante

---

## 🧩 Premissas

* Os serviços estão implantados em um **cluster Kubernetes** com mais de uma instância(s) em execução simultaneamente.
* Ambos os serviços possuem acesso a **recursos AWS**.
* É permitido o uso de soluções computacionais (como filas, cache, etc.)
* Um serviço **não pode acessar diretamente o banco de dados do outro**.

---

## 📄 Modelo de Relacionamento (ER)

![Modelo ER](./docs/er-diagram.svg)

> Estrutura completa entre `users`, `enrollment`, `course_price`, e `instalment`.

---

## 📬 Entregáveis

O desafio pode ser entregue de **duas formas**, sendo a **primeira a mais recomendada**:

1. ✅ **Implementação do desafio**
   Entregar o código com o job implementado, testável dentro da estrutura existente do projeto.

   > **Esta é a forma recomendada de entrega.**

2. 📝 **Especificação técnica detalhada**
   Caso não seja possível implementar, é aceitável entregar um documento contendo:

   * Análise completa do sistema atual e suas configurações;
   * Lista de tarefas técnicas necessárias para a implementação do desafio;
   * Justificativas técnicas para cada decisão proposta.

> Ambas as opções serão avaliadas com base no **entendimento do problema**, **clareza da solução** e **adequação às premissas técnicas**.

---

> ### ⚠️ Em caso de dúvidas
> Para qualquer dúvida relacionada ao desafio, entre em contato pelo e-mail:  
> 📧 **jornadaaprendizagemdigitaldocencia-tls@kroton.onmicrosoft.com**

---

## 📝 Queremos ouvir você!

Sua opinião é muito importante para nós.  
Por favor, se puder, responda nosso formulário anônimo de feedback sobre este teste técnico.

💡 [**Clique aqui para acessar o formulário**](https://forms.office.com/r/RJweUKwtbS)

---

Boa sorte! 🚀