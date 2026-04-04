# 🌐 FlowNet - Network Packet Routing Simulator

## 🚀 Overview
FlowNet is a Java-based network packet routing simulator that models how data packets travel across a network using routers and hosts. The system uses graph-based topology and Dijkstra’s shortest path algorithm to simulate real-world routing behavior.

The project also includes a web-based user interface to visualize simulation logs and interact with the backend.

---

## 🎯 Key Features

### 🔹 Core Features
- 📦 Packet transmission between hosts
- 🌐 Graph-based network topology
- 🧠 Shortest path routing using Dijkstra’s algorithm
- 🔁 Dynamic routing table generation
- ⏳ TTL (Time-To-Live) handling to prevent infinite loops

### 🔹 Advanced Features
- ❌ Router failure simulation
- 🔄 Network recovery handling
- 📊 Multi-packet transmission
- 🧾 Structured logging system
- 💻 Web-based UI integration

---

## 🏗️ System Architecture
Frontend (HTML/CSS/JS)
↓
Java Server (HTTP)
↓
Simulation Engine
↓
Network Graph (Nodes + Links)
↓
Router / Host / Packet


---

## 🧠 How It Works

1. Network is created using routers and hosts connected as a graph
2. Routing tables are generated automatically using Dijkstra’s algorithm
3. A packet is created at the source host
4. The packet travels through routers based on routing decisions
5. TTL is reduced at each hop to prevent infinite loops
6. If a router is down → packet is dropped
7. If network is restored → packet reaches destination
8. Logs are generated and displayed in the UI

---

## 📂 Project Structure
Network-Simulator/
│
├── backend/
│ └── DaaPbl/
│ ├── Node.java
│ ├── router.java
│ ├── host.java
│ ├── paket.java
│ ├── Network.java
│ ├── Link.java
│ ├── Dijkstra.java
│ ├── RoutingProtocoll.java
│ ├── Server.java
│ ├── login.java
│ ├── Main.java
│
├── frontend/
│ ├── index.html
│ ├── style.css
│ ├── script.js
│
├── README.md
└── .gitignore


---

## ⚙️ Technologies Used

- ☕ Java (Core Logic & Backend)
- 🌐 HTML, CSS, JavaScript (Frontend UI)
- 🔗 HTTP Server (Java)
- 📊 Data Structures (Graph, Queue, Map, Priority Queue)

---
