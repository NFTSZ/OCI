# Sistema de Gerenciamento de OCI 

## 📌 Visão Geral 

Sistema desenvolvido para gerenciar Ofertas de Cuidados Especiais (OCI), permitindo o cadastro e acompanhamento de pacientes com prazos diferenciados para casos oncológicos (30 dias) e demais especialidades (60 dias). 

---  
## 🛠️ Estrutura do Código

### 1. **Classe `Paciente`**  
Armazena os dados básicos do paciente e sua OCI associada.  

**Atributos:**  
- `id` (int): Identificador único  
- `nome`, `cpf`, `telefone` (String): Dados pessoais  
- `agenteSaude`, `nomeUBS` (String): Informações da unidade de saúde  
- `dataCadastro` (LocalDate): Data automática do cadastro  
- `oci` (OCI): Oferta de Cuidados associada  

**Métodos Principais:**  
- `mostrarDados()`: Exibe todas as informações do paciente e sua OCI  

---
### 2. Classe `OCI`
Gerencia os prazos e procedimentos de cada atendimento.  

**Atributos:**  
- `codigo`, `descricao`, `tipo` (String): Identificação da OCI.
- `dataInicio`, `dataLimite` (LocalDate): Prazos calculados automaticamente.
- `procedimentos` (List-String-): Lista de procedimentos obrigatórios.

```java
public void setDataInicio(LocalDate data) {
    this.dataInicio = data;
    int dias = tipo.equalsIgnoreCase("Oncologia") ? 30 : 60; // Oncologia: 30 dias, outros: 60 dias
    this.dataLimite = data.plusDays(dias); // Calcula a data final
}
```

---
### 3. **Classe `SistemaOCI`**  
Núcleo do sistema, gerencia a lista de pacientes.  

**Funcionalidades Implementadas:**  
✅ `cadastrarPaciente()`: Adiciona novo paciente  
✅ `listarPacientes()`: Retorna lista completa  
✅ `buscarPaciente(int id)`: Localiza por ID  

**Funcionalidades Futuras (v2.0):**  
✏️ `editarPaciente()`  
✏️ `atualizarProcedimentos()`  

---  

### 4. **Classe `Main`**  
Interface textual do sistema.  

**Fluxo Principal:**  
1. Menu interativo com 5 opções  
2. Entrada de dados via `Scanner`  
3. Chamadas aos métodos do `SistemaOCI`  

---  
## ⚙️ Como Executar  

1. **Compilação:**  
```bash
javac *.java
```  

2. **Execução:**  
```bash
java Main
```  

---
## 📋 Regras de Negócio  

| Tipo de OCI  | Prazo Máximo | Exemplo de Procedimentos |
| ------------ | ------------ | ------------------------ |
| Oncologia    | 30 dias      | Consulta, Quimioterapia  |
| Demais casos | 60 dias      | EKG, Radiografia         |

---  
## 📊 Estrutura do Projeto

```
OCI/
├── src/          
│   ├── OCI.java              
│   ├── Paciente.java              
│   ├── SistemaOCI.java
│   ├── TratadorExcecoes.java
│   ├── TratadorExcecoesTest.java     
│   └── Main.java
├── OCI.iml
├── Diagrama de Classe - OCI.pdf
└── README.md
```
---  

## 📝 Notas para Expansão  

1. **Persistência de Dados:**  
   - Adicionar gravação em arquivo (ex: `pacientes.csv`)  

2. **Validações:**  
   - Verificar formato do CPF  
   - Validar datas futuras  

3. **Interface Gráfica:**  
   - Interface completa com Swing na próxima versão  

---  

**Desenvolvido para a disciplina de POO** 
