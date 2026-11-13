# Calorias — AP3 (Aplicação Web Individual)

Aplicação web para controle de calorias: cadastro de alimentos, registro do consumo diário,
meta calórica e calculadora de TMB.

**Stack:** Java 17, Spring Boot 3.2.5, Spring Data JPA, banco H2 (arquivo), Maven.

## Como executar

```bash
mvn spring-boot:run
```

- Aplicação: http://localhost:8080
- Console do banco H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:file:./data/calorias`, usuário `sa`, senha vazia)

## Requisitos da AP3

| Requisito | Onde está |
|---|---|
| Maven / pom.xml | `pom.xml` |
| Banco de Dados | H2 + JPA (`model/`, `repository/`) |
| API | REST em `controller/` |
| 5 padrões de projeto | pacote `pattern/` |

## Padrões de projeto

1. **Strategy** — `pattern/strategy`: `MifflinStJeorStrategy` e `HarrisBenedictStrategy` implementam `TmbStrategy`.
2. **Factory Method** — `pattern/factory/TmbStrategyFactory`: escolhe a Strategy pelo nome da fórmula.
3. **Builder** — `pattern/builder/RegistroBuilder`: monta o `Registro` e calcula as calorias.
4. **Singleton** — `pattern/singleton/AuditoriaLog`: log de auditoria com instância única.
5. **Observer** — `pattern/observer`: `RegistroSubject` notifica `AuditoriaObserver` e `AlertaMetaObserver`.

## Endpoints

| Método | URL | Descrição |
|---|---|---|
| GET/POST | `/api/alimentos` | Lista / cadastra alimentos |
| GET/PUT/DELETE | `/api/alimentos/{id}` | Busca / atualiza / remove |
| GET | `/api/registros?data=AAAA-MM-DD` | Consumo do dia |
| POST | `/api/registros` | `{"alimentoId":1,"gramas":150,"tipo":"ALMOCO","data":"2026-11-13"}` |
| DELETE | `/api/registros/{id}` | Remove registro |
| GET | `/api/resumo?data=...` | Total, meta e restante |
| GET/PUT | `/api/meta` | Meta diária `{"caloriasDiarias":2000}` |
| GET | `/api/tmb?peso=70&altura=170&idade=25&sexo=MASCULINO&formula=MIFFLIN&atividade=LEVE` | TMB e gasto diário |
| GET | `/api/auditoria` | Log de eventos |

## Estrutura

```
src/main/java/br/com/calorias/
├── CaloriasApplication.java
├── config/       (dados iniciais)
├── controller/   (API REST + tratamento de erros)
├── dto/
├── model/
├── pattern/      (strategy, factory, builder, singleton, observer)
├── repository/
└── service/
src/main/resources/
├── application.properties
└── static/index.html   (interface web)
```
