Base URL (variável Insomnia)
- {{ _.fintech_url }} → exemplo: `http://localhost:8080/api/v1`

╔══════════════════════════════════════════════════════════╗
║                       LEGENDA                            ║
║ 🔹 GET   | 🔸 POST/PUT | ❌ DELETE | 📝 Body | 🔁 Response ║
╚══════════════════════════════════════════════════════════╝

(Um mapa rápido)
[CLIENTE] -> {{ _.fintech_url }}
  ├─ /usuario
  ├─ /usuario/{usuarioId}/gastos
  └─ /usuario/{usuarioId}/contas

────────────────────────────────────────────────────────────
USUÁRIO
────────────────────────────────────────────────────────────

1) Criar Usuário
- Método / Rota: 🔸 POST /usuario  
- Headers: `Content-Type: application/json`  
- Body (padrão) 📝:
  {
    "nome": "string",
    "email": "string (email)",
    "senha": "string",
    "dataNascimento": "YYYY-MM-DD"
  }
- Exemplo:
  {
    "nome": "Diego 123",
    "email": "diego123@gmail.com",
    "senha": "12345678",
    "dataNascimento": "2000-03-27"
  }
- Modelo de retorno 🔁 (sugestão):
  201 Created
  {
    "id": 5,
    "nome": "Diego 123",
    "email": "diego123@gmail.com",
    "dataNascimento": "2000-03-27",
    "createdAt": "2025-11-04T00:00:00Z"
  }
- Observações: nunca retornar senha em texto. Armazenar apenas hashes.

2) Login
- Método / Rota: 🔸 POST /usuario/login  
- Headers: `Content-Type: application/json`  
- Body:
  {
    "email": "string",
    "senha": "string"
  }
- Exemplo:
  {
    "email": "diego@gmail.com",
    "senha": "12345678"
  }
- Modelo de retorno 🔁 (sem token):
  200 OK
  {
    "usuario": {
      "id": 5,
      "nome": "Diego",
      "email": "diego@gmail.com"
    }
  }
- Observação: a API NÃO utiliza JWT. Se houver mecanismo de sessão/cookie, documentar separadamente.

3) Excluir Usuário
- Método / Rota: ❌ DELETE /usuario/{usuarioId}/excluir  
- Exemplo: `DELETE /usuario/5/excluir`  
- Headers: (se aplicável, p.ex. cookie de sessão)  
- Modelo de retorno 🔁:
  204 No Content  (ou)
  200 OK
  { "message": "Usuário removido" }

────────────────────────────────────────────────────────────
GASTOS
────────────────────────────────────────────────────────────

1) Adicionar Gasto
- Método / Rota: 🔸 POST /usuario/{usuarioId}/gastos  
- Headers: `Content-Type: application/json`  
- Body (padrão) 📝:
  {
    "nome": "string",
    "tipo": "string (Eventual|Fixo|...)",
    "valor": number,
    "dataGasto": "YYYY-MM-DD"
  }
- Exemplo:
  {
    "nome": "Viagem para a França",
    "tipo": "Eventual",
    "valor": 2000,
    "dataGasto": "2026-01-20"
  }
- Modelo de retorno 🔁:
  201 Created
  {
    "id": 11,
    "nome": "Viagem para a França",
    "tipo": "Eventual",
    "valor": 2000,
    "dataGasto": "2026-01-20",
    "usuarioId": 6,
    "createdAt": "2025-11-04T00:00:00Z"
  }

2) Excluir Gasto
- Método / Rota: ❌ DELETE /usuario/{usuarioId}/gastos/{gastoId}/excluir  
- Ex.: `DELETE /usuario/6/gastos/11/excluir`  
- Modelo de retorno 🔁:
  204 No Content  (ou) { "message": "Gasto removido" }

3) Buscar Todos os Gastos
- Método / Rota: 🔹 GET /usuario/{usuarioId}/gastos/todos  
- Ex.: `GET /usuario/6/gastos/todos`  
- Modelo de retorno 🔁:
  200
