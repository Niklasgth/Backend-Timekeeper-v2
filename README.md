# Tidsrapport-API

Detta är backend-delen av appen **Timekeeper**, en tidrapporteringsapp där användaren kan mäta tid per arbetsuppgift. Backend är byggd i **Spring Boot** och använder **MongoDB** som databas.
detta ären del av min skoluppgift under utbildningen 2024-2025

## Funktionalitet

- 📋 Hantera arbetsuppgifter (`Task`) med start- och stopptid samt koppling till kategori
- 🗂️ Stöd för arbetskategorier (t.ex. *Läsa*, *Programmera*, *Lunch*, *Paus*)
- 🧠 Statistik-API för att summera tid per kategori
- ✅ REST API med CRUD-stöd
- 🌍 CORS-stöd för frontend-kommunikation
- 🐳 Docker-kompatibel för deployment

## Teknikstack

- Java 21
- Spring Boot 
- MongoDB
- Maven
- Docker

## Modellstruktur

### `Task`

```json
{
  "_id": "ObjectId",
  "categoryId": "string",
  "categoryName": "string", // redundans för frontendvisning
  "startTime": "ISO-8601 timestamp",
  "endTime": "ISO-8601 timestamp",
  "duration": "number (sekunder)"
}
```

### `TaskCategory`

```json
{
  "_id": "ObjectId",
  "name": "string"
}
```

## API Endpoints

### `/api/tasks`

| Metod       | Beskrivning                               | Payload (JSON)                      |
|-------------|--------------------------------------------|-------------------------------------|
| GET         | Hämta alla uppgifter                       | -                                   |
| POST        | Skapa ny uppgift                           | `categoryId`, `startTime`, `endTime`|
| GET /{id}   | Hämta enskild uppgift                      | -                                   |
| PUT /{id}   | Uppdatera uppgift                          | Delmängd av `Task`                  |
| DELETE /{id}| Radera uppgift (finns men används ej i UI) | -                                   |

### `/api/categories`

| Metod       | Beskrivning                 |
|-------------|-----------------------------|
| GET         | Hämta alla kategorier        |
| POST        | Lägg till ny kategori        |
| PUT /{id}   | Uppdatera namn på kategori   |

> ⚠️ Kategorier kan **inte raderas**, men kan redigeras.

## Miljövariabler

| Variabel       | Exempelvärde                              | Beskrivning                      |
|----------------|-------------------------------------------|----------------------------------|
| `MONGODB_URI`  | `mongodb://localhost:27017/tidsrapport`   | MongoDB-anslutning               |
| `PORT`         | `8080`                                    | Port för Spring Boot-server      |

## Deployment

Stöder Docker och kan enkelt sjösättas exempelvis på **DigitalOcean** med GitHub-integration. Vilket gjorts  under utbildningen.

### Exempel på Docker-kommando

```bash
docker build -t timekeeper .
docker run -p 8080:8080 --env MONGODB_URI=mongodb://host.docker.internal:27017/tidsrapport timekeeper
```

## Kontakt

Byggd av [Niklas Torstensson](https://github.com/Niklasgth)
