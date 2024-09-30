# AHMIIAS: Assured Human-Machine Interface for Increasingly Autonomous Systems

Open repository for the NASA **Assured Human-Machine Interface for Increasingly Autonomous Systems (AHMIIAS)** project.

Welcome to the AHMIIAS project repository. This repository is part of NASA's AHMIIAS initiative, which focuses on creating a framework to ensure the safety and correctness of learning-enabled autonomous systems through human-machine interaction and formal verification.

## Project Overview

The increasing autonomy of systems like aircraft introduces new challenges in ensuring safe operations, especially in dynamic environments. Our project addresses these challenges by:

- **Modeling critical human-machine interactions.**
- **Employing the Soar cognitive architecture** for decision-making, reinforced by learning mechanisms.
- **Translating the agent into the nuXmv** formal verification tool to ensure system safety.

## Repository Structure

Here's an overview of the key directories and their contents:

- **Verification/**:  
  This folder includes verification models and scripts used to ensure the safety and correctness of the system through formal verification techniques.

- **agents/**:  
  Updated Soar agents designed to interact with three sensors, including logic for reinforcement learning and decision-making processes.

- **architecture/**:  
  Contains AADL models representing communication channels between the IAS agent and the pilot, structured for enhanced system autonomy and safety.

- **examples/counter/**:  
  Sample code to demonstrate restructuring for the final delivery of the IAS verification system, providing counterexamples for testing and verification.

- **java/**:  
  This folder houses the **Context Communication Awareness Tool (CCAT)**, which connects the Soar agent to the XPlane flight simulation environment, enabling real-time interaction and communication awareness in simulated scenarios.

- **legacy-examples/**:  
  Contains older or deprecated examples from previous stages of the project. These examples are kept for historical reference and may still be useful for certain cases.

- **tools/**:  
  Scripts and utilities supporting agent updates and verification tasks as well as the Soar to nuXmv translator.
