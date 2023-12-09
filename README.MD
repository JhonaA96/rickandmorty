# Prueba Double V Partners
    
    Prueba tecnica para aplicar al cargo de desarrollador full stack en Double V Partners

## Requerimientos
- Java 11
- Postman
- Postgresql 14.6

## Instalación
- Clonar el repositorio en su equipo local con el comando
    ```bash
    git clone https://github.com/JhonaA96/rickandmorty.git
    ```
- Abrir el proyecto en su IDE de preferencia
- Ejecutar el script de la base de datos que se encuentra en la ruta
    ```bash
    src/main/resources/CreateDatabse.sql
    ```
- Para poder conectarse de manera correcta a la base de datos esta debe tener los siguientes parametros
    ```bash
    host: localhost
    port: 5432
    database: prueba
    username: postgres
    password: root
    ```  
- Ejecutar el proyecto

## Uso
### Endpoints
#### Obtener todos los personajes
- Metodo: GET
- URL: http://localhost:8080/characters/getCharacters
- Curl para ejecutar en postman:
  ```bash
  curl --location 'localhost:8080/characters/getCharacters'
  ```
#### Crear Personaje
- Metodo: POST
- URL: http://localhost:8080/characters/getCharacters
  - Curl para ejecutar en postman:
    ```bash
    curl --location 'localhost:8080/characters/saveCharacter' \
    --header 'Content-Type: application/json' \
    --data '{
      "name": "Jhonatan Acuña",
      "status": "Alive",
      "gender": "Male",
      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    }'
    ```