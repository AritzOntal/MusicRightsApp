# 🎵 MusicRightsApp

**MusicRightsApp** es una aplicación nativa para Android desarrollada en **Java** que permite la gestión integral de músicos y conciertos.

La aplicación implementa una arquitectura híbrida que combina el consumo de una API REST remota con persistencia de datos local, permitiendo a los usuarios trabajar con datos en la nube y guardar sus conciertos favoritos en el dispositivo.

---

## Características Principales

* **Gestión de Conciertos:** Listado, registro, modificación y detalle de conciertos consumiendo una API externa.
* **Gestión de Músicos:** Listado, registro y modificación de músicos sincronizado con la API externa.
* **Sistema de Favoritos:** Almacenamiento persistente en el dispositivo de conciertos favoritos mediante base de datos interna (**Room**).
* **Geolocalización:** Integración con mapas interactivos (**Mapbox**) para visualizar y seleccionar la ubicación exacta de los eventos.
* **Soporte Multi-idioma:** Interfaz totalmente adaptable a **Español** e **Inglés**.

---

## 🛠️ Stack Tecnológico

### Arquitectura
* **Patrón MVP (Model-View-Presenter):** Desacoplamiento total entre la lógica de negocio (*Presenter*), la interfaz de usuario (*View*) y el manejo de datos (*Model*).

### Librerías y Herramientas
* **Retrofit 2 & GSON:** Para la comunicación HTTP con la API REST (Spring Boot) y conversión a JSON.
* **Room Database:** SQLite para la persistencia local de datos (Favoritos).
* **Mapbox:** Para la visualización de mapas y gestión de marcadores.
---


## ⚙️ Configuración e Instalación

### Requisitos Previos
* Android Studio.
* JDK 17.
* Dispositivo Android o Emulador.
* Backend Spring Boot desplegado y accesible.

### 1. Configuración de Red
La aplicación está configurada para conectarse a un servidor local.

### 2. Configuración de Mapas
El proyecto utiliza Mapbox. Para que los mapas carguen correctamente, debes añadir tu Token de Acceso Público.



### Autor:
Aritz Ontalvilla



