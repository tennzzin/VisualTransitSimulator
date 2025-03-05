# Visual Transit Simulator (VTS)

![GUI of the VTS Software](/images/vts_iteration_3.png)

## Overview

The **Visual Transit Simulator (VTS)** is a Java-based simulation that models public transit operations around the **University of Minnesota** campus. It simulates real-time movement of buses and trains, allowing users to visualize how vehicles and passengers interact over time. The project consists of two main components:

- **Simulation module (Java)** – Manages vehicle movement, passenger generation, and route management.
- **Visualization module (JavaScript & HTML)** – Displays transit activity in a browser using WebSockets.

## Features

- **Real-time simulation** – Dynamically updates vehicle positions and passenger interactions.
- **Passenger system** – Generates passengers probabilistically at stops.
- **Vehicle deployment strategies** – Implements time-based train scheduling.
- **Configurable simulation** – Supports custom routes, stops, and vehicle storage via a configuration file.
- **Interactive web visualization** – Enables users to start, pause, and monitor the simulation via a browser.

## Technology Stack

- **Languages**: Java (Simulator), JavaScript & HTML (Visualization)
- **Core Concepts**: Object-oriented programming (OOP), design patterns (Factory, Strategy, Observer, and Decorator), WebSockets for real-time communication, and file I/O for reading transit configurations.

## How to Run

1. Clone the repository:
   ```sh
   git clone https://github.com/tennzzin/VisualTransitSimulator.git
   cd VisualTransitSimulator
2. Start the simulation module:
   ./gradlew appRun
3. Open a web browser and go to:
   http://localhost:7777/project/web_graphics/project.html
4. Use the web interface to control the simulation (start, pause, etc.).

## Skills Demonstrated

- **Software Design**: Leveraged multiple design patterns to enhance modularity and maintainability.
- **Concurrency & Synchronization**: Managed simultaneous vehicle operations and real-time updates.
- **Web Technologies**: Integrated WebSockets for smooth frontend-backend interaction.
- **Testing & Debugging**: Achieved 90%+ branch coverage using JUnit and Mockito.

## Authors
- **Tenzin Chonyi**
- **Syed Hashimi**
- **Cindy Su**
