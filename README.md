# Demo

Este es un servicio que se comunica al API de [Rick and Morty](https://rickandmortyapi.com/), se comunica a 2 servicios y genera una respuesta. El fin de este proyecto es ejecutar pruebas de intgración usando [Wiremock](https://wiremock.org/) y hacer el contraste con las pruebas unitarias. 

## Microservicio

El microservicio posee solo 1 controlador **/rick-and-morty/v1/challenge/{id}** y llama a  [Character](https://rickandmortyapi.com/documentation/#character-schema) y [Location](https://rickandmortyapi.com/documentation/#location-schema). La respuesta debe ser como el siguiente schema: 

```json
{
  "$schema": "http://json-schema.org/draft-07/schema",
  "$id": "http://example.com/example.json",
  "type": "object",
  "required": [
    "id",
    "name",
    "status",
    "species",
    "type",
    "episode_count",
    "origin"
  ],
  "properties": {
    "id": {
      "$id": "#/properties/id",
      "type": "int"
    },
    "name": {
      "$id": "#/properties/name",
      "type": "string"
    },
    "status": {
      "$id": "#/properties/status",
      "type": "string"
    },
    "species": {
      "$id": "#/properties/species",
      "type": "string"
    },
    "type": {
      "$id": "#/properties/type",
      "type": "string"
    },
    "episode_count": {
      "$id": "#/properties/episode_count",
      "type": "int"
    },
    "origin": {
      "$id": "#/properties/origin",
      "type": "object",
      "required": [
        "name",
        "url",
        "dimension",
        "residents"
      ],
      "properties": {
        "name": {
          "$id": "#/properties/origin/properties/name",
          "type": "string"
        },
        "url": {
          "$id": "#/properties/origin/properties/url",
          "type": "string"
        },
        "dimension": {
          "$id": "#/properties/origin/properties/dimension",
          "type": "string"
        },
        "residents": {
          "$id": "#/properties/origin/properties/residents",
          "type": "array",
          "items": {
            "$id": "#/properties/origin/properties/residents/items",
            "type": "string"
          }
        }
      }
    }
  }
}
```

## Swagger 

La URL es la siguiente [Swagger](http://localhost:8080/swagger-ui/index.html).

## Estructura del proyecto 

La estructura del proyecto es la siguiente manera: 

```
├── build.gradle        //configuración del proyecto, jacoco, test and integration tests
├── gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── src
    ├── intTest         //pruebas de integración
    │   ├── java
    │   └── resources
    ├── main            //ejecutable
    │   ├── java
    │   └── resources
    └── test            //pruebas unitarias
        ├── java
        └── resources
```

