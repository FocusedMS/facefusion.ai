# FaceFusion.ai

**Driven Clarity in Every Click**

A SaaS platform for AI-powered face-based image sorting and organization.

---

## 🚀 Project Overview
FaceFusion.ai automatically sorts images containing faces into folders by individual person, using state-of-the-art AI for face detection, recognition, and clustering. It is designed as a scalable SaaS with a modern UI, robust backend, and a Python AI microservice.

---

## 🏗️ Monorepo Structure

```
FaceFusion.ai/
├── backend/      # Spring Boot (Java) backend
├── frontend/     # Angular frontend
├── ai-service/   # Python (FastAPI) AI microservice
├── docker/       # Docker Compose, Nginx config, etc.
└── README.md     # Project documentation
```

---

## 🧩 Architecture & Workflow

1. **Frontend (Angular)**
    - Upload ZIP file of images (supports chunked upload)
    - Show progress/status, download link, and (optionally) face cluster preview & manual editing
2. **Backend (Spring Boot, Java)**
    - Accepts uploads, manages jobs, extracts images, validates files
    - Calls Python AI service for face detection, embedding, and clustering
    - Handles manual corrections, sorts images, compresses output ZIP
    - Stores job/face/cluster/correction data in MySQL
3. **AI Service (Python, FastAPI)**
    - Detects and aligns faces
    - Generates face embeddings (DeepFace, ArcFace, etc.)
    - Clusters faces (DBSCAN)
    - Returns cluster metadata to backend
4. **Docker & Orchestration**
    - Docker Compose for local/dev deployment
    - Nginx for reverse proxy (optional)

---

## 🛠️ Tech Stack

| Layer      | Tech Used                                      |
|------------|------------------------------------------------|
| Frontend   | Angular, Angular Material/Tailwind, RxJS       |
| Backend    | Spring Boot (Java 17+), Apache Commons, Tika, JPA, MySQL |
| Face AI    | Python, DeepFace, scikit-learn, FastAPI        |
| Clustering | DBSCAN                                         |
| Storage    | ZIP, MySQL                                     |
| Deployment | Docker, Nginx                                  |

---

## 🧑‍💻 Feature Implementation Approaches

### 1. Face Detection & Embedding
- **Approach:** Both face detection and embedding are performed in the Python AI service using DeepFace (with models like ArcFace or FaceNet for high accuracy). No detection in Java to avoid code duplication and maximize accuracy.

### 2. Clustering
- **Approach:** DBSCAN clustering is performed in the Python AI service on the face embeddings. This allows for unsupervised grouping of faces into clusters (Person_1, Person_2, ...).

### 3. Manual Review & Corrections
- **Approach:**
  - Users can manually edit clusters via the Angular frontend (drag-and-drop UI).
  - Corrections are sent to the backend and stored in MySQL.
  - Corrections are reapplied for the same job; future learning from corrections is planned as an enhancement.

### 4. Large File Handling
- **Approach:**
  - Chunked uploads are supported in the frontend (using a library like ngx-file-drop or ng2-file-upload).
  - Backend assembles and validates ZIP files from chunks before processing.
  - Jobs are processed asynchronously, with status polling via a REST endpoint.

### 5. Security
- **Approach:**
  - Only ZIP files are accepted; images are validated using Apache Tika.
  - File names are sanitized and files are stored in non-public temp directories.
  - Extraction limits are enforced to prevent zip bombs.
  - (Optional) Virus scanning with ClamAV for production.
  - (Optional) JWT authentication for user login and job isolation.

### 6. Database
- **Approach:**
  - MySQL is used for all environments (scalable, production-ready).
  - Spring Data JPA manages entities for jobs, faces, clusters, and corrections.

### 7. Python Service Integration
- **Approach:**
  - The Java backend communicates with the Python AI service via a REST API (FastAPI), not subprocess, for scalability and maintainability.

### 8. Deployment
- **Approach:**
  - Docker Compose is used for local and production orchestration.
  - Nginx is used as a reverse proxy for frontend and API routing.

---

## 📝 Setup Instructions

### 1. Backend (Spring Boot)
- Java 17+
- Spring Boot, MySQL, Apache Commons Compress, Apache Tika
- See `/backend` for details

### 2. Frontend (Angular)
- Angular v14+
- Angular Material or Tailwind CSS
- See `/frontend` for details

### 3. AI Service (Python)
- Python 3.9+
- FastAPI, DeepFace, scikit-learn, numpy, pandas
- See `/ai-service` for details

### 4. Docker
- Docker Compose for orchestration
- See `/docker` for details

---

## 🔗 Key Features
- AI-powered face detection, recognition, and clustering
- Chunked uploads for large ZIP files
- Manual cluster editing (drag-and-drop UI)
- Progress polling/status updates
- Secure file validation and background processing
- Scalable, modular, and ready for production

---

## 👥 For Developers
- **Stick to the planned architecture and tech stack.**
- **Document any major changes in this README.**
- **Keep code modular and well-commented.**
- **Update setup instructions if you add dependencies or change build steps.**

---

## 📅 Roadmap
- [ ] Backend API scaffolding
- [ ] Frontend UI scaffolding
- [ ] AI service endpoint scaffolding
- [ ] Docker Compose integration
- [ ] End-to-end MVP
- [ ] Advanced features (manual review, user auth, etc.)

---

*This README is the single source of truth for the project plan. Please keep it up to date as the project evolves.* 