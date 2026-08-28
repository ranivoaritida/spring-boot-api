# 🛒 E-Commerce REST API — Spring Boot

API REST développée avec **Java et Spring Boot** pour gérer le backend d'une application e-commerce complète.

L'API fournit les fonctionnalités nécessaires à la gestion des **utilisateurs, produits, panier, commandes et paiements**, tout en assurant la sécurisation des ressources avec **Spring Security et JWT**.

Le backend est conçu selon une architecture en couches et utilise **Spring Data JPA / Hibernate** pour la persistance des données dans une base de données **MySQL**.

## 📋 Description

Ce projet constitue le backend d'une application e-commerce permettant de gérer l'ensemble du processus d'achat :

* Gestion des utilisateurs et de l'authentification
* Gestion du catalogue de produits
* Gestion du panier
* Gestion des commandes
* Gestion des paiements
* Sécurisation des API REST
* Persistance des données avec MySQL

L'API est consommée par une application frontend développée avec **React.js**.

---

## ✨ Fonctionnalités

### 👤 Gestion des utilisateurs

* Inscription d'un utilisateur
* Authentification
* Gestion des informations utilisateur
* Gestion des rôles et des autorisations
* Protection des ressources selon les permissions

### 🔐 Authentification & sécurité

L'API utilise **Spring Security** et **JWT (JSON Web Token)** pour sécuriser les endpoints.

Le système d'authentification repose sur :

* Access Token JWT
* Refresh Token
* Spring Security
* HTTP-only Cookie pour le Refresh Token
* Contrôle d'accès aux endpoints
* Gestion des rôles

Le Refresh Token permet notamment d'obtenir un nouvel Access Token lorsque celui-ci arrive à expiration, sans demander à l'utilisateur de se reconnecter.

### 📦 Gestion des produits

* Création d'un produit
* Consultation des produits
* Consultation d'un produit par son identifiant
* Modification d'un produit
* Suppression d'un produit
* Gestion des informations produit
* Gestion du stock

### 🛒 Gestion du panier

L'API permet de gérer le panier d'un utilisateur :

* Création d'un panier
* Ajout d'un produit
* Modification de la quantité
* Suppression d'un produit
* Consultation du panier
* Calcul du contenu du panier

### 📋 Gestion des commandes

Le backend permet de gérer le cycle de vie d'une commande :

* Création d'une commande
* Consultation des commandes
* Consultation du détail d'une commande
* Association des produits à une commande
* Gestion des informations de commande

### 💳 Paiement

Intégration de **Stripe** pour la gestion du paiement des commandes.

Le backend assure la communication avec le service de paiement et permet d'intégrer le processus de paiement dans le parcours d'achat.

---

## 🏗️ Architecture

Le projet suit une architecture en couches basée sur les responsabilités classiques d'une application Spring Boot :

```text
                    ┌─────────────────────┐
                    │   React Frontend    │
                    └──────────┬──────────┘
                               │
                         HTTP / REST
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Controller     │
                    │    REST Endpoints   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       Service       │
                    │ Business Logic      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Repository      │
                    │   Spring Data JPA   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    │      Database       │
                    └─────────────────────┘
```

La sécurité intervient au niveau des requêtes entrantes :

```text
Client
  │
  │ JWT
  ▼
Spring Security
  │
  ├── Vérification du token
  ├── Authentification
  ├── Vérification des autorisations
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
MySQL
```

---

## 🛠️ Technologies utilisées

### Backend

* **Java**
* **Spring Boot**
* **Spring Security**
* **Spring Data JPA**
* **Hibernate**
* **Maven**

### Sécurité

* **JWT**
* Spring Security
* Access Token
* Refresh Token
* HTTP-only Cookie

### Base de données

* **MySQL**

### Paiement

* **Stripe**

### API

* Architecture REST
* JSON
* HTTP

### Frontend associé

Le backend est consommé par une application développée avec :

* **React.js**
* **TanStack Query**
* **Axios**

### Outils

* **Git**
* GitHub

---

## 📁 Structure du projet

L'application est organisée autour des différentes couches de l'architecture Spring :

```text
src/
└── main/
    ├── java/
    │   └── .../
    │       ├── config/
    │       ├── controller/
    │       ├── dto/
    │       ├── entities/
    │       ├── exception/
    │       ├── repository/
    │       ├── security/
    │       ├── service/
    │       └── ...
    │
    └── resources/
        ├── application.properties
        └── ...
```

### Principales responsabilités

| Couche       | Responsabilité                               |
| ------------ | -------------------------------------------- |
| `Controller` | Expose les endpoints REST                    |
| `Service`    | Contient la logique métier                   |
| `Repository` | Accès aux données avec Spring Data JPA       |
| `Entities`     | Représentation des tables de la base         |
| `DTO`        | Objets utilisés pour les échanges de données |
| `Security`   | Authentification et autorisation             |
| `Config`     | Configuration de l'application               |
| `Exception`  | Gestion des erreurs                          |

---

## 🔌 API REST

L'API expose plusieurs ressources principales.

### 🔐 Authentification

```text
POST /auth/login
POST /auth/refresh
```

Le endpoint `/auth/login` permet d'authentifier un utilisateur et de générer les tokens nécessaires à l'accès aux ressources protégées.

Le endpoint `/auth/refresh` permet de générer un nouvel Access Token à partir du Refresh Token.

### 📦 Produits

```text
GET    /products
GET    /products/{id}
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

### 🛒 Panier

```text
POST /carts
POST /carts/{cartId}/items/{productId}
```

Le panier permet notamment d'ajouter des produits et de modifier leurs quantités.

### 📋 Commandes

```text
POST /orders
GET  /orders
GET  /orders/{id}
```

> Les endpoints présentés ci-dessus constituent un aperçu de l'API. Consulter les controllers du projet pour connaître l'ensemble des routes disponibles.

---

## 🔐 Gestion des tokens

Le système d'authentification utilise deux types de tokens.

```text
                    Login
                      │
                      ▼
               Spring Security
                      │
              ┌───────┴────────┐
              ▼                ▼
        Access Token       Refresh Token
              │                │
              │                ▼
              │          HTTP-only Cookie
              │
              ▼
        Requêtes API
              │
              ▼
       Spring Security
              │
              ▼
       Ressource protégée
```

L'**Access Token** est utilisé pour authentifier les requêtes vers les ressources protégées.

Lorsque l'Access Token expire, le **Refresh Token** peut être utilisé via `/auth/refresh` afin d'obtenir un nouveau token d'accès.

Cette approche permet de maintenir la session de l'utilisateur tout en limitant la durée de vie de l'Access Token.

---

## 🗄️ Base de données

La persistance est assurée par :

* MySQL
* Spring Data JPA
* Hibernate

Les entités Java sont associées aux tables de la base de données grâce à JPA/Hibernate.

Les principales données métier concernent notamment :

```text
User
  │
  ├── Cart
  │     └── CartItem
  │             └── Product
  │
  └── Order
        └── OrderItem
                └── Product
```

---

## 💳 Intégration Stripe

Le backend intègre **Stripe** afin de gérer le paiement lors du processus de commande.

Le principe général est :

```text
Utilisateur
     │
     ▼
Panier
     │
     ▼
Création de la commande
     │
     ▼
Backend Spring Boot
     │
     ▼
Stripe
     │
     ▼
Paiement
     │
     ▼
Confirmation
```

---

## ⚙️ Installation

### Prérequis

Avant de lancer le projet, installer :

* **Java 17+**
* **Maven**
* **MySQL**
* Un compte Stripe pour les fonctionnalités de paiement

### 1. Cloner le repository

```bash

git clone https://https://github.com/ranivoaritida/spring-boot-api
cd spring-api
```

### 2. Configurer MySQL

Créer une base de données MySQL :

```sql
CREATE DATABASE ecommerce;
```

Puis configurer les informations de connexion dans :

```text
src/main/resources/application.properties
```

Exemple :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Configurer JWT

Configurer les paramètres nécessaires à la génération et à la validation des tokens dans les fichiers de configuration du projet.

### 4. Configurer Stripe

Ajouter les clés Stripe nécessaires dans les variables de configuration du projet.

> Les clés secrètes ne doivent jamais être commitées dans Git.

### 5. Lancer l'application

Avec Maven :

```bash
./mvnw spring-boot:run
```

Ou :

```bash
mvn spring-boot:run
```

L'API sera disponible par défaut sur :

```text
http://localhost:8080
```

---

## 🧪 Tests

Les tests peuvent être exécutés avec Maven :

```bash
./mvnw test
```

---

## 🔄 Communication avec le frontend

Le backend expose une API REST consommée par le frontend React.

```text
React.js
    │
    │ Axios
    ▼
Spring Boot REST API
    │
    │ JWT
    ▼
Spring Security
    │
    ▼
Services métier
    │
    ▼
Spring Data JPA
    │
    ▼
MySQL
```

Le frontend utilise notamment **TanStack Query** pour gérer les requêtes, le cache et les mutations côté client.

---

## 🚀 Améliorations possibles

* Ajouter davantage de tests unitaires et d'intégration.
* Ajouter une documentation interactive avec OpenAPI / Swagger.
* Améliorer la gestion des erreurs.
* Ajouter des fonctionnalités avancées de recherche et de filtrage des produits.
* Ajouter une gestion plus complète des statuts de commande.
* Améliorer la gestion des événements liés aux paiements Stripe.
* Déployer l'API sur un environnement cloud.

---

## 👨‍💻 Auteur

**Sandy**

### Stack principale

```text
Java • Spring Boot • Spring Security • Spring Data JPA
Hibernate • MySQL • JWT • Stripe • REST API
```

