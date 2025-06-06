 # 📡 Application Temps Réel : Appel API, Kafka, Elasticsearch, Kibana

Ce projet illustre une architecture Big Data en temps réel avec **Spring Boot**, **Kafka**, **Elasticsearch**, et **Kibana**. Il récupère les taux de change depuis une API, les envoie dans Kafka, les consomme et les indexe dans Elasticsearch, puis les visualise dans Kibana.

---

## 📁 Nom du dépôt

**appel_api_spring_boot_application_temps_reel**

---

## 🔧 Stack technique

- **Java / Spring Boot**
- **Apache Kafka**
- **Elasticsearch 8**
- **Kibana**
- **Docker Compose**

---

## 🧩 Fonctionnalités

- Appel d'une API de taux de change en temps réel
- Envoi du document JSON dans un topic Kafka
- Lecture depuis Kafka par un consumer Spring Boot
- Indexation du document dans Elasticsearch
- Visualisation des données dans Kibana

---

## 🚀 Démarrage du projet

### 1. Cloner le projet

```bash
git clone https://github.com/stshauke/appel_api_spring_boot_application_temps_reel.git
cd appel_api_spring_boot_application_temps_reel
```

### 2. Lancer les services Big Data (Kafka, Elasticsearch, Kibana)

```powershell
docker-compose up -d

```

### 3. Lancer l’application Spring Boot

Depuis IntelliJ ou terminal :

```cmd
mvnw.cmd spring-boot:run

```

---

## 📬 Appeler l'API

```http
GET http://localhost:8080/exchange-rate?base=USD
```

✅ Cela déclenche :
- un appel à l’API externe
- un envoi dans Kafka
- une indexation dans Elasticsearch
- des logs dans le terminal :

```log
📥 Message received in consumer: {...}
📤 Document saved to Elasticsearch
```

---

## 🔎 Visualiser dans Kibana

1. Ouvrir Kibana : http://localhost:5601  
2. Aller dans **Discover**
3. Ajouter un data view : `exchange-rates`
4. Visualiser les documents indexés

---

## 📁 Organisation du code

| Dossier / Classe                  | Description |
|----------------------------------|-------------|
| `ExchangeRateController`         | Expose l'endpoint `/exchange-rate` |
| `KafkaProducerService`           | Envoie les messages dans Kafka |
| `KafkaConsumerService`           | Consomme les messages Kafka |
| `ExchangeRateDocument`           | Modèle des données échangées |
| `ExchangeRateElasticRepository`  | Repository Spring vers Elasticsearch |

---



## 👨‍💻 Auteur

- Prénom : *Salomon*
- Nom : *TSHAUKE-MULUMBA*
- Contact : *tshaukemulumba@yahoo.com*

---



