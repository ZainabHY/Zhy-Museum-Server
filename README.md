# Zhy Museum Server

## Description
Zhy Museum is a web application designed for viewing artworks with several types - Charcoal and Pencil. 
Implemented using **SpringBoot** for backend, and **Angular** for frontend, using Monolithic approach.


## Diagrams
**Class Diagram**
![Class Diagram*](https://github.com/ZainabHY/Zhy-Museum-Server/blob/main/Museum-%20class%20UML.png) 

**Use Case Diagram**
![Use Case Diagram](https://github.com/ZainabHY/Zhy-Museum-Server/blob/main/Museum%20-%20Use%20Case.png) 


## Setup
* Installing Java
* Installiing Spring Boot
* Adding dependencies
   * Spring Web
   * Spring Boot DevTools
   * Spring Data JPA
   * MySQL Driver
   * Lombok
* Clone repository
   * **In Terminal:**
     git clone https://github.com/ZainabHY/Zhy-Museum-Server.git

## Technologies Used
   * IntelliJ
   * Postman

## Controllers and Routes structure
   * **Sign up - Login** 
     * Sign up (**GET**) --> localhost:8001/zhyMuseum/auth/signup
     * Login (**GET**) --> localhost:8001/zhyMuseum/auth/login
    
   * **Artist**
     * Add Artwork (**POST**) --> localhost:8001/artist/addArtwork
     * Update Artwork (**PUT**) --> localhost:8001/artist/updateArtwork/{artworkId}
     * Partial Update Artwork (**PATCH**) --> localhost:8001/artist/partialUpdateArtwork/{artworkId}
     * Delete Artwork (**DELETE**) --> localhost:8001/artist/deleteArtwork/{artworkId}

   * **Artist - Artlover**
     * Get All Artworks (**GET**) --> localhost:8001/both/getAllArtworks
     * Get Artwork By ID (**GET**) --> localhost:8001/both/getArtworkById/{artworkId}
     * Get Artwork By Artwork Type (**GET**) --> localhost:8001/both/getArtworksByType/{artworkType}

## Project Structure
### Model
* Define entities that represent the core concepts in the system (User, Artwork)

### Repository
* Create repositories to interact with the database

### Controller
* Implement controllers to handle incoming HTTP requests

### DTO (Data Transfer Object)
* Use DTOs to transfer data between layers and avoid exposing the entire entity

### Service
* Implement services to encapsulate business logic

### Security
* Implementing security configurations for authentication and authorization
* Implement filters for custom processing of HTTP requests


## Extra links
  * **Zhy Museum Presentation:**
   [https://docs.google.com/presentation/d/1EOxj1KYtBlxg51Q9ASDxpcCG-WVED83NNQhm80lb_xk/edit?usp=sharing](https://docs.google.com/presentation/d/1Fp5How-pl1y4gaCYepy50mKChKT-z4-ufdp4QRpEjhA/edit?usp=sharing)
   * **Trello Managemnt**
   [Trello Managemnt](https://trello.com/invite/b/peNuIYsY/ATTI5b2ca21cf14c0b7cdbdc7df5cd45be435529E6AD/zhy-museum-project)https://trello.com/invite/b/peNuIYsY/ATTI5b2ca21cf14c0b7cdbdc7df5cd45be435529E6AD/zhy-museum-project
